package com.gui.listener.impl;

import com.gui.listener.ScreenEvent;
import com.gui.listener.ScreenObserver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class SeleniumListener implements ScreenObserver {
    private WebDriver driver;
    private By identifier;
    private ScreenEvent screenEvent;

    public SeleniumListener(final WebDriver driver, final By identifier) {
        this.driver = driver;
        this.identifier = identifier;
    }
    @Override
    public boolean update() {
        final Duration timeout = Duration.ofSeconds(1);
        final WebDriverWait webDriverWait = new WebDriverWait(this.driver, timeout);
        final WebElement element = webDriverWait.until(ExpectedConditions.elementToBeClickable(this.identifier));
        if (null != element) {
            return action();
        }
        return false;
    }

    @Override
    public void setScreenEvent(ScreenEvent screenEvent) {
        this.screenEvent = screenEvent;
    }

    @Override
    public ScreenEvent getScreenEvent() {
        return this.screenEvent;
    }

    public abstract boolean action();

    protected WebDriver getDriver() {
        return this.driver;
    }

    protected By getIdentifier() {
        return this.identifier;
    }
}
