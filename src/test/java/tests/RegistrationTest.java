package tests;

import io.qameta.allure.Feature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import pages.HomePage;
import pages.RegistrationPage;

import java.util.UUID;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.closeWebDriver;

@Feature("Registration")
public class RegistrationTest extends BaseTest {
    private final RegistrationPage registrationPage = new RegistrationPage();
    private final HomePage homePage = new HomePage();

    @AfterEach
    void afterEach() {
        closeWebDriver();
    }

    @Test
    void successfulRegistrationTest() {
        String userName = "reg" + UUID.randomUUID().toString().substring(0, 8);
        registrationPage.openPage();
        registrationPage.setUserName(userName);
        registrationPage.setPassword("password1");
        registrationPage.confirmPassword("password1");
        registrationPage.submit();
        homePage.welcomeMessage().shouldHave(text("Welcome, " + userName + "!"));
    }

    @Test
    void unsuccessfulRegistrationWithExistingUserNameTest() {
        String userName = "reg" + UUID.randomUUID().toString().substring(0, 8);
        registrationPage.openPage();
        registrationPage.setUserName(userName);
        registrationPage.setPassword("password1");
        registrationPage.confirmPassword("password1");
        registrationPage.submit();
        homePage.welcomeMessage().shouldHave(text("Welcome, " + userName + "!"));
        homePage.logout();
        registrationPage.openPage();
        registrationPage.setUserName(userName);
        registrationPage.setPassword("password1");
        registrationPage.confirmPassword("password1");
        registrationPage.submit();
        registrationPage.errorMessage().shouldHave(text("Username already taken"));
    }

    @Test
    void unsuccessfulRegistrationWithDifferentPasswordsTest() {
        String userName = "reg" + UUID.randomUUID().toString().substring(0, 8);
        registrationPage.openPage();
        registrationPage.setUserName(userName);
        registrationPage.setPassword("password1");
        registrationPage.confirmPassword("password2");
        registrationPage.submit();
        registrationPage.errorMessage().shouldHave(text("Passwords do not match"));
    }

    @Test
    void unsuccessfulRegistrationWithEmptyPasswordConfirmationTest() {
        String userName = "reg" + UUID.randomUUID().toString().substring(0, 8);
        registrationPage.openPage();
        registrationPage.setUserName(userName);
        registrationPage.setPassword("password1");
        registrationPage.submit();
        registrationPage.errorMessage().shouldHave(text("Passwords do not match"));
    }

    @Test
    void unsuccessfulRegistrationWithEmptyUserNameTest() {
        registrationPage.openPage();
        registrationPage.setPassword("password1");
        registrationPage.confirmPassword("password1");
        registrationPage.submit();
        registrationPage.errorMessage().shouldHave(text("Login is required (minimum 3 characters)"));
    }

    @Test
    void unsuccessfulRegistrationWithShortUserNameTest() {
        registrationPage.openPage();
        registrationPage.setUserName("ab");
        registrationPage.setPassword("password1");
        registrationPage.confirmPassword("password1");
        registrationPage.submit();
        registrationPage.errorMessage().shouldHave(text("Login must be at least 3 characters"));
    }

    @Test
    void unsuccessfulRegistrationWithShortPasswordTest() {
        String userName = "reg" + UUID.randomUUID().toString().substring(0, 8);
        registrationPage.openPage();
        registrationPage.setUserName(userName);
        registrationPage.setPassword("abcde");
        registrationPage.confirmPassword("abcde");
        registrationPage.submit();
        registrationPage.errorMessage().shouldHave(text("Password must be at least 6 characters"));
    }

    @Test
    void unsuccessfulRegistrationWithEmptyCredentialsTest() {
        registrationPage.openPage();
        registrationPage.submit();
        registrationPage.errorMessage().shouldHave(text("Login and password are required (minimum 3 and 6 characters)"));
    }
}
