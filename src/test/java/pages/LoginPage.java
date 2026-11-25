package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;

public class LoginPage extends BasePage {  // Page Object — элементы и действия страницы
    private final SelenideElement loginForm = el("login-form");  // элемент по data-testid
    private final SelenideElement loginInput = el("login-input");  // элемент по data-testid
    private final SelenideElement passwordInput = el("password-input");  // элемент по data-testid
    private final SelenideElement submitButton = el("submit-button");  // элемент по data-testid
    private final SelenideElement errorMessage = el("error-message");  // элемент по data-testid

    @Step("Открыть страницу /login")
    public void openPage() {
        open("/login");
    }

    @Step("Ввести логин: {userName}")
    public void setUserName(String userName) {
        loginInput.setValue(userName);
    }

    @Step("Ввести пароль")
    public void setPassword(String password) {
        passwordInput.setValue(password);
    }

    @Step("Отправить форму логина")
    public void submit() {
        submitButton.click();
    }

    @Step("Получить сообщение об ошибке")
    public SelenideElement errorMessage() {
        return errorMessage;
    }

    @Step("Получить форму логина")
    public SelenideElement loginForm() {
        return loginForm;
    }
}
