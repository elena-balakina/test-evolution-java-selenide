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
        Configuration.headless = true;  // без видимого окна
        Configuration.timeout = 5_000;  // таймаут ожиданий
        SelenideLogger.addListener("allure", new AllureSelenide());  // скриншот и page source на падении
    }
}
