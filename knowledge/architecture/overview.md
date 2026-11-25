# Architecture

Generated stack: Java / Gradle / JUnit 6 / Selenide.
The project contains browser tests against the configured application; no API client, mock server or separate API test layer is generated.

## System under test

- SUT — java-spring + typescript-react.
- URL: `https://autotests.ai/stack/backend-java-spring/frontend-typescript-react`.
- Testing model — blackbox: External surface only: checks go through UI flows, product internals are out of reach.
- Test layers:
  - ui — browser UI scenarios — generated in this project

## Source map
- Build/dependencies: [build.gradle](../../build.gradle).
- Test modules: [src/test/java/tests/LoginTest.java](../../src/test/java/tests/LoginTest.java).
- Shared setup/teardown: [src/test/java/tests/BaseTest.java](../../src/test/java/tests/BaseTest.java).
- Page Object conventions: [../pageobjects/style.md](../pageobjects/style.md).
- Lifecycle contract: [lifecycle.md](lifecycle.md).
- Data/environment: [test-data.md](test-data.md).
- Test commands: [../testing/run-tests.md](../testing/run-tests.md).
- Checks placement: [../testing/assertions.md](../testing/assertions.md).
- No remote-farm contract is generated for `embedded`.

## Existing scenarios
- Successful login.

Canonical test shape — verbatim from this project:

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

## Checks and synchronization
Assertions stay in tests, using the elements exposed by Page Objects.
Reuse Selenide conditions and their retrying shouldHave/shouldBe checks.
Each check is a single terminal retrying assertion on the expected outcome — never prepend weaker precondition checks (e.g. visibility) or append state re-verification after it.
No component-object layer is generated; do not document nonexistent components.

## Configuration and CI
No CI configuration is generated.
Integration files, if present, are templates, not evidence of live TestOps/Jira/Confluence integration.
Do not import the course pack's Gradle wrappers, tags, mock environments or testE2e task: they are not configured in this project.
