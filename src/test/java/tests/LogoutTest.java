package tests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;

@Feature("Logout")
public class LogoutTest extends BaseTest {
    private final LoginPage loginPage = new LoginPage();
    private final HomePage homePage = new HomePage();

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }

    @Test
    void successfulLogoutTest() {
        loginPage.openPage();
        loginPage.setUserName("user1");
        loginPage.setPassword("password1");
        loginPage.submit();
        homePage.welcomeMessage().shouldHave(text("Welcome, user1!"));
        homePage.logout();
        loginPage.loginForm().shouldBe(visible);
    }
}
