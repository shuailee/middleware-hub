package com.shuailee.observer.demo2;

import java.util.ArrayList;
import java.util.List;

public class RegisterSubject implements Subject {
    List<IObesever> obeseverList = new ArrayList<>();
    String email;

    public RegisterSubject(String email) {
        this.email = email;
    }

    @Override
    public void attach(IObesever observer) {
        obeseverList.add(observer);
    }

    @Override
    public void detach(IObesever observer) {
        obeseverList.remove(observer);
    }

    @Override
    public void notifyObesever() {
        for (IObesever iObesever : obeseverList) {
            iObesever.update("注册事件发生了,邮箱"+ this.email);
        }
    }
}
