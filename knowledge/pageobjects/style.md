# Page Objects

Style: plain.
Canonical: [src/test/java/pages/BasePage.java](../../src/test/java/pages/BasePage.java), [src/test/java/pages/LoginPage.java](../../src/test/java/pages/LoginPage.java), [src/test/java/pages/HomePage.java](../../src/test/java/pages/HomePage.java).
Usage: [src/test/java/tests/LoginTest.java](../../src/test/java/tests/LoginTest.java).

Shape of a page action — verbatim from this project:

```java
    @Step("Ввести логин: {userName}")
    public void setUserName(String userName) {
        loginInput.setValue(userName);
    }

    @Step("Отправить форму логина")
    public void submit() {
        submitButton.click();
    }
```

## Do
- Keep locators and page actions in these existing classes/modules; reuse an existing object before adding one.
- Plain actions are independent calls. Do not assume they return another page for chaining.
- Follow the synchronous calls and return types demonstrated by the existing test.
- Assertions belong in tests — see [../testing/assertions.md](../testing/assertions.md).
- Preserve the configured application URL prefix and existing relative navigation conventions.

## Don't
- Do not move locators into test bodies when extending this architecture.
- Transitions are implemented by the existing form submission and logout methods, not by a separate routing framework.
- There are no generated ComponentObjects. Add one only for a real reused component, not for this documentation.
