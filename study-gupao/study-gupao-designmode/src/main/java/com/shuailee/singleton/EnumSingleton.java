package com.shuailee.singleton;


public enum  EnumSingleton {

    INSTANCE;
    private Object data;

    private Hungry hungry;

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Hungry getHungry() {
        return hungry;
    }

    public void setHungry(Hungry hungry) {
        this.hungry = hungry;
    }

    public static EnumSingleton getInstance(){
        return INSTANCE;
    }
}
