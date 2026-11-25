package tests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.LoginPage;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.closeWebDriver;

@Feature("Home")
public class HomeTest extends BaseTest {
    private final LoginPage loginPage = new LoginPage();
    private final HomePage homePage = new HomePage();

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }

    @Test
    void homePageIsDisplayedAfterLoginTest() {
        loginPage.openPage();
        loginPage.setUserName("user1");
        loginPage.setPassword("password1");
        loginPage.submit();
        homePage.layout().shouldBe(visible);
        homePage.welcomeMessage().shouldHave(text("Welcome, user1!"));
    }

    @Test
    void homePageIsDisplayedWithoutLoginTest() {
        homePage.openPage();
        homePage.layout().shouldBe(visible);
        homePage.welcomeMessage().shouldNotBe(visible);
    }
}
