package tests;

import io.qameta.allure.Feature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pages.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;  // освободить сессию

@Feature("Login")
public class LoginTest extends BaseTest {  // тестовый класс
    private static final Logger log = LogManager.getLogger(LoginTest.class);

    private final LoginPage loginPage = new LoginPage();  // page object'ы теста
    private final HomePage homePage = new HomePage();  // page object'ы теста

    @AfterEach  // закрытие сессии после теста
    void afterEach() {
        closeWebDriver();  // освободить сессию
        log.info("afterEach: browser closed");
    }

    // Логин валидной парой
    @Test
    void successfulLoginTest() {
        loginPage.openPage();
        loginPage.setUserName("user1");
        loginPage.setPassword("password1");
        loginPage.submit();
        homePage.welcomeMessage().shouldHave(text("Welcome, user1!"));
    }
}
