package ru.market.yandex.tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import pages.MarketBeforeSearch;

import static com.codeborne.selenide.Selenide.*;

public class SelenideTests {
    @BeforeEach
    public void setConfig(){
        Configuration.timeout=60000;
        Configuration.browser="chrome";
        Configuration.startMaximized=true;

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-extensions");

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        Configuration.browserCapabilities = capabilities;
    }

    @Test
    public void test(){
        open("https://market.yandex.ru/", MarketBeforeSearch.class)
                .goToVideoCards("Компьютеры", "Видеокарты")
                .comparePrices("RTX 3060", "GTX 1660");
    }
}
