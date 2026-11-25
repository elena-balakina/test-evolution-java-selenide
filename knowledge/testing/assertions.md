# Assertions

Canonical: checks stay inline in test bodies against the elements/state the code exposes.

```java
    @Test
    void successfulLoginTest() {
        loginPage.openPage();
        loginPage.setUserName("user1");
        loginPage.setPassword("password1");
        loginPage.submit();
        homePage.welcomeMessage().shouldHave(text("Welcome, user1!"));
    }
```

## Do
- Assert in the test; reach page state through the exposed elements/methods, not new locators.
- Reuse the framework's retrying conditions; the expected texts come from the test data source.
- Mirror the canonical test verbatim: same chain form and check count — one scenario asserts only its expected outcome.

## Don't
- No weakened assertions, arbitrary sleeps or expected-text edits to obtain a green run.
- No redundant checks: a retrying assertion already waits for the element state; never prepend visibility checks to a text/state assertion.
- No assertion padding: assert exactly the scenario's expected outcome — no extra visibility checks, no re-verifying page or form state after the terminal assertion.
