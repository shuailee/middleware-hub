package com.shuailee.PrototypePattern;

import java.io.*;

/**
 * 原型角色：定义用于复制现有实例来生成新实例的方法；
 */
public abstract class Resume implements Cloneable, Serializable {
    private String id;
    private String name;
    private String birthday;
    private String sex;
    private Work company;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Work getCompany() {
        return company;
    }

    public void setCompany(Work company) {
        this.company = company;
    }



    /**
     * 浅拷贝
     */
    public Object clone() {
        Object clone = null;
        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return clone;
    }

    public Object deepClone() {
        try {

            // 将对象写到流里
            ByteArrayOutputStream bo = new ByteArrayOutputStream();
            ObjectOutputStream oo = new ObjectOutputStream(bo);
            oo.writeObject(this);
            // 从流里读出来
            ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
            ObjectInputStream oi = new ObjectInputStream(bi);
            return (oi.readObject());

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public abstract void generate();


    public void display() {
        System.out.println("姓名：" + name);
        System.out.println("生日:" + birthday + ",性别:" + sex);
        System.out.println("公司:" + company.getName());
    }

}

class Work implements  Serializable{
    private String name;
    private String adress;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }
}

