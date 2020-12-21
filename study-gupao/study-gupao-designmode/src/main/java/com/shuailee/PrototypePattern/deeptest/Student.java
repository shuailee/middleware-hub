package com.shuailee.PrototypePattern.deeptest;

import java.io.*;

public class Student implements Serializable,Cloneable {
   private String name;
    private int age;
    private School p;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public School getP() {
        return p;
    }

    public void setP(School p) {
        this.p = p;
    }

    Student(String name, int age, School p) {
        this.name = name;
        this.age = age;
        this.p = p;
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

    /**
     *  需要所有被序列化的类都实现Serializable接口，假设一个可序列化的对象包括对某个不可序列化的对象的引用，那么整个序列化操作将会失败，而且会抛出一个NotSerializableException.
     *
     * */
    public Object deepClone() throws IOException, OptionalDataException,
            ClassNotFoundException {
        // 将对象写到流里
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        ObjectOutputStream oo = new ObjectOutputStream(bo);
        oo.writeObject(this);
        // 从流里读出来
        ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
        ObjectInputStream oi = new ObjectInputStream(bi);
        return (oi.readObject());
    }
}
