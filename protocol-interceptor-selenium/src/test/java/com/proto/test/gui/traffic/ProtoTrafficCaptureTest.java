package com.proto.test.gui.traffic;

import com.proto.gui.selenium.chrome.ChromeDriverFactory;
import com.proto.intercept.ProtocolProxyFactory;
import com.proto.intercept.browsermobproxyimpl.BrowserMobProxyProtocolProxyImpl;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class ProtoTrafficCaptureTest {
    private static final ChromeDriverFactory chromeDriverFactory = new ChromeDriverFactory();

    private WebDriver driver;

    @BeforeClass
    public void setupBeforeClass(ITestContext context) {
        this.driver = chromeDriverFactory.getChromeDriver();
        //ProtocolProxyFactory.getLittleProxyProtocolProxy().start();
        ProtocolProxyFactory.getBrowserMobProxyProtocolProxy().start();
        context.setAttribute("driver", this.driver);
    }

    @Test
    public void clickSearchTest(ITestContext context) {
        this.driver = (WebDriver) context.getAttribute("driver");
        this.driver.get("https://www.google.co.in/");
        this.driver.findElement(By.name("q")).sendKeys("mutual auth");
        this.driver.findElement(By.name("btnK")).click();
    }

    @AfterClass
    private void setupAfterClass(ITestContext context) throws IOException {
        this.driver = (WebDriver) context.getAttribute("driver");
        this.driver.quit();
        //ProtocolProxyFactory.getLittleProxyProtocolProxy().stop();
        ProtocolProxyFactory.getBrowserMobProxyProtocolProxy().stop();
        ((BrowserMobProxyProtocolProxyImpl)ProtocolProxyFactory.getBrowserMobProxyProtocolProxy()).saveHAR();
    }
}
