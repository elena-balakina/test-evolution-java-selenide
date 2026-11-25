package tests;

import io.qameta.allure.Feature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pages.*;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
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

    // Логин с неверным паролем
    @Test
    void unsuccessfulLoginWithInvalidPasswordTest() {
        loginPage.openPage();
        loginPage.setUserName("user1");
        loginPage.setPassword("invalid-password");
        loginPage.submit();
        loginPage.errorMessage()
                .shouldBe(visible)
                .shouldHave(text("Wrong login or password"));
    }

    // Логин с пустым паролем
    @Test
    void unsuccessfulLoginWithEmptyPasswordTest() {
        loginPage.openPage();
        loginPage.setUserName("user1");
        loginPage.setPassword("");
        loginPage.submit();
        loginPage.errorMessage()
                .shouldBe(visible)
                .shouldHave(text("Password is required (minimum 6 characters)"));
    }

    // Логин с несуществующим логином
    @Test
    void unsuccessfulLoginWithNonexistentUserNameTest() {
        loginPage.openPage();
        loginPage.setUserName("nonexistent-user");
        loginPage.setPassword("password1");
        loginPage.submit();
        loginPage.errorMessage().shouldHave(text("Wrong login or password"));
    }

    // Логин с пустым логином
    @Test
    void unsuccessfulLoginWithEmptyUserNameTest() {
        loginPage.openPage();
        loginPage.setUserName("");
        loginPage.setPassword("password1");
        loginPage.submit();
        loginPage.errorMessage()
                .shouldBe(visible)
                .shouldHave(text("Login is required (minimum 3 characters)"));
    }

    // Логин с пустыми логином и паролем
    @Test
    void unsuccessfulLoginWithEmptyCredentialsTest() {
        loginPage.openPage();
        loginPage.setUserName("");
        loginPage.setPassword("");
        loginPage.submit();
        loginPage.errorMessage()
                .shouldBe(visible)
                .shouldHave(text("Login and password are required (minimum 3 and 6 characters)"));
    }

    // Логин короче минимальной длины
    @Test
    void unsuccessfulLoginWithShortUserNameTest() {
        loginPage.openPage();
        loginPage.setUserName("ab");
        loginPage.setPassword("password1");
        loginPage.submit();
        loginPage.errorMessage()
                .shouldBe(visible)
                .shouldHave(text("Login must be at least 3 characters"));
    }

    // Логин с паролем короче минимальной длины
    @Test
    void unsuccessfulLoginWithShortPasswordTest() {
        loginPage.openPage();
        loginPage.setUserName("user1");
        loginPage.setPassword("abcde");
        loginPage.submit();
        loginPage.errorMessage()
                .shouldBe(visible)
                .shouldHave(text("Password must be at least 6 characters"));
    }
}
