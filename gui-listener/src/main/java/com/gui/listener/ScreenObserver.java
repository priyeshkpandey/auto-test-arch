package com.gui.listener;

public interface ScreenObserver {
    boolean update();
    void setScreenEvent(ScreenEvent screenEvent);
    public ScreenEvent getScreenEvent();
}
