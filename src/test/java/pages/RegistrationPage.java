package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;

public class RegistrationPage extends BasePage {
    private final SelenideElement registrationLoginInput = el("register-login-input");
    private final SelenideElement registrationPasswordInput = el("register-password-input");
    private final SelenideElement confirmPasswordInput = el("confirm-password-input");
    private final SelenideElement registrationSubmitButton = el("register-submit-button");
    private final SelenideElement registrationErrorMessage = el("register-error-message");

    @Step("Открыть страницу /register")
    public void openPage() {
        open("/register");
    }

    @Step("Ввести логин для регистрации: {userName}")
    public void setUserName(String userName) {
        registrationLoginInput.setValue(userName);
    }

    @Step("Ввести пароль для регистрации")
    public void setPassword(String password) {
        registrationPasswordInput.setValue(password);
    }

    @Step("Подтвердить пароль")
    public void confirmPassword(String password) {
        confirmPasswordInput.setValue(password);
    }

    @Step("Отправить форму регистрации")
    public void submit() {
        registrationSubmitButton.click();
    }

    @Step("Получить сообщение об ошибке регистрации")
    public SelenideElement errorMessage() {
        return registrationErrorMessage;
    }
}
