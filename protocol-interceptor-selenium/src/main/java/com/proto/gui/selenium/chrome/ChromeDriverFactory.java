package com.proto.gui.selenium.chrome;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class ChromeDriverFactory {
    private static final String OS = System.getProperty("os.name").toLowerCase();
    private static final String FILE_SEPARATOR = System.getProperty("file.separator");
    private static final String CHROME_DRIVERS_DIR = "exec" + FILE_SEPARATOR + "chrome_drivers" + FILE_SEPARATOR;
    private static final String WINDOWS_DRIVER_NAME = "chromedriver.exe";
    private static final String MAC_DRIVER_NAME = "chromedriver_mac";
    private static final String LINUX_DRIVER_NAME = "chromedriver";

    public WebDriver getChromeDriver() {
        initChromeDriverLocation();
        return new ChromeDriver(getChromeOptions());
    }

    private void initChromeDriverLocation() {
        if (OS.contains("win")) {
            System.setProperty("webdriver.chrome.driver", CHROME_DRIVERS_DIR + WINDOWS_DRIVER_NAME);
        } else if (OS.contains("mac")) {
            System.setProperty("webdriver.chrome.driver", CHROME_DRIVERS_DIR + MAC_DRIVER_NAME);
        } else {
            System.setProperty("webdriver.chrome.driver", CHROME_DRIVERS_DIR + LINUX_DRIVER_NAME);
        }
    }

    private ChromeOptions getChromeOptions() {
        final Proxy proxy = new Proxy();
        proxy.setHttpProxy("127.0.0.1:11000");
        proxy.setSslProxy("127.0.0.1:11000");
        proxy.setNoProxy("<-loopback>");
        final ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.addArguments("--enable-javascript");
        chromeOptions.setProxy(proxy);
        chromeOptions.setHeadless(true);
        return chromeOptions;
    }
}
