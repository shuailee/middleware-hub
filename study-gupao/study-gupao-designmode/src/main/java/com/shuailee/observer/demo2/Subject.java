package com.shuailee.observer.demo2;


public interface Subject {
    void attach(IObesever observer);
    void detach(IObesever observer);
    void notifyObesever();
}
