package com.gui.listener;

public interface ScreenEvent {
    public void registerScreenObserver(ScreenObserver screenObserver);
    public void unregisterScreenObserver(ScreenObserver screenObserver);
    public void notifyScreenObservers();
    public Object getUpdate(ScreenObserver screenObserver);
    public void trigger();
}
