# Lifecycle

Source: [src/test/java/tests/BaseTest.java](../../src/test/java/tests/BaseTest.java) — shared lifecycle code for this snapshot.
Selected configuration: browserLevel=full, fixturePlace=class, hooks=beforeAll+afterEach (teardown=afterEach).

```java
package tests;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class BaseTest {  // общий lifecycle: setup, ресурсы, teardown

    protected static final Logger log = LogManager.getLogger(BaseTest.class);

    static {
        Configuration.baseUrl = "https://autotests.ai/stack/backend-java-spring/frontend-typescript-react";  // адрес тестируемого приложения
        Configuration.browser = "chrome";  // браузер прогона
        Configuration.browserSize = "1920x1080";  // размер окна
        // ...
```

## Do
- Reuse the shared setup actually present here; a teardown-only file does not configure a browser.

## Don't
- Do not move setup or cleanup across scopes without checking the selected lifecycle configuration and resource ownership.
