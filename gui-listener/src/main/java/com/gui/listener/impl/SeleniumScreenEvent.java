package com.gui.listener.impl;

import com.gui.listener.ScreenEvent;
import com.gui.listener.ScreenObserver;

import java.util.ArrayList;
import java.util.List;

public class SeleniumScreenEvent implements ScreenEvent {
    private static volatile boolean isTriggered = false;

    private List<ScreenObserver> observers;
    private final Object MUTEX= new Object();

    public SeleniumScreenEvent() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerScreenObserver(ScreenObserver screenObserver) {
        if(screenObserver == null) {
            throw new NullPointerException("Null Observer");
        }
        synchronized (MUTEX) {
            this.observers.add(screenObserver);
        }
    }

    @Override
    public void unregisterScreenObserver(ScreenObserver screenObserver) {
        synchronized (MUTEX) {
            this.observers.remove(screenObserver);
        }
    }

    @Override
    public void notifyScreenObservers() {
        if (!isTriggered) {
            return;
        }
        List<ScreenObserver> observersLocalCopy = null;
        synchronized (MUTEX) {
            observersLocalCopy = new ArrayList<>(this.observers);
        }
        isTriggered = false;
        for (ScreenObserver screenObserver : observersLocalCopy) {
            screenObserver.update();
        }
    }

    @Override
    public Object getUpdate(ScreenObserver screenObserver) {
        return screenObserver.getScreenEvent();
    }

    @Override
    public void trigger() {
        isTriggered = true;
        this.notifyScreenObservers();
    }
}
