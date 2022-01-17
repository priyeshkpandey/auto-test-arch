package com.test.gui.listener;

import com.gui.listener.ScreenEvent;
import com.gui.listener.impl.SeleniumListener;
import com.gui.listener.impl.SeleniumScreenEvent;
import com.gui.selenium.chrome.ChromeDriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ListenersTest {
    private static final ScreenEvent screenEvent = new SeleniumScreenEvent();
    private static final ChromeDriverFactory chromeDriverFactory = new ChromeDriverFactory();

    private WebDriver driver;

    @BeforeClass
    public void setupBeforeClass(ITestContext context) {
        this.driver = chromeDriverFactory.getChromeDriver();
        screenEvent.registerScreenObserver(new SeleniumListener(this.driver, By.name("q")) {
            @Override
            public boolean action() {
                this.getDriver().findElement(this.getIdentifier()).sendKeys("observer pattern");
                return true;
            }
        });
        screenEvent.registerScreenObserver(new SeleniumListener(this.driver, By.name("btnK")) {
            @Override
            public boolean action() {
                this.getDriver().findElement(this.getIdentifier()).click();
                return true;
            }
        });
        context.setAttribute("driver", this.driver);
    }

    @Test
    public void clickSearchTest(ITestContext context) {
        this.driver = (WebDriver) context.getAttribute("driver");
        this.driver.get("https://www.google.co.in/");
        screenEvent.trigger();
        this.driver.quit();
    }
}
