package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;

public class HomePage extends BasePage {  // Page Object — элементы и действия страницы
    private final SelenideElement layout = el("multistack-layout");  // элемент по data-testid
    private final SelenideElement welcomeMessage = el("welcome-message");  // элемент по data-testid
    private final SelenideElement logoutButton = el("logout-button");  // элемент по data-testid

    @Step("Открыть страницу /")
    public void openPage() {
        open("/");
    }

    public SelenideElement welcomeMessage() {
        return welcomeMessage;
    }
}
