package com.shuailee.study.basicknowledge;

/**
 * @program: study-javacore
 * @description:
 * @author: shuai.li
 * @create: 2019-06-28 12:20
 **/
public class MethodChainingDemo {
    public static void main(String[] args) {
        /**
         * StringBuilder类是典型的链式编程
         * */
        StringBuilder builder = new StringBuilder();
        builder.append("链").append("式").append("编").append("程");
        System.out.println(builder.toString());

        /**
         * 自定义链式
         * */
        Person  person=new Person();
        person.setHeight(18)
                .setWeight(Double.valueOf("100"))
                .setAge(18)
                .setSex("男人");
        System.out.println(person.toString());
    }

}

/**
 * 定义一个链式编程的类，主要功能在于：在方法体内返回对象自身
 * */
class Person{
    private Integer height;
    private Integer age;
    private Double weight;
    private String sex;

    public Person(){}

    public Integer getHeight() {
        return height;
    }
    /**
     * set后返回当前对象
     * */
    public Person setHeight(Integer height) {
        this.height = height;
        return this;
    }

    public Integer getAge() {
        return age;
    }
    /**
     * set后返回当前对象
     * */
    public Person setAge(Integer age) {
        this.age = age;
        return this;
    }

    public Double getWeight() {
        return weight;
    }
    /**
     * set后返回当前对象
     * */
    public Person setWeight(Double weight) {
        this.weight = weight;
        return this;
    }

    public String getSex() {
        return sex;
    }
    /**
     * set后返回当前对象
     * */
    public Person setSex(String sex) {
        this.sex = sex;
        return this;
    }


    @Override
    public String toString() {
        return "Person{" +
                "height=" + height +
                ", age=" + age +
                ", weight=" + weight +
                ", sex='" + sex + '\'' +
                '}';
    }
}