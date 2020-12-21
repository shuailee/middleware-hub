package com.shuailee.PrototypePattern;

public class ResumeMain {
    public static void main(String [] args){

        ResumeContent.loadResumeData();
        System.out.println("-------------中文简历副本1------------------");
        Resume resume= (Resume)ResumeContent.getResume("1");
        System.out.println("Resume : " + resume.getName());

        resume.display();

        System.out.println("-------------中文简历副本2------------------");
        Resume resume2= (Resume)ResumeContent.getResume("1");
        System.out.println("Resume : " + resume2.getName());

        resume2.display();

        System.out.println("---------------浅拷贝验证1----------------");
        System.out.println("---------------修改resume2的姓名为李四，应该只有resume2改变----------------");
        resume2.setName("李四");
        resume2.display();
        resume.display();

        System.out.println("---------------浅拷贝验证2----------------");
        System.out.println("------修改resume中引用类型work的姓名为 房地产，应该是两个都输出房地产，因为浅拷贝不会只是对对象中引用类型变量的引用地址的拷贝------");
        resume2.getCompany().setName("房地产");
        resume2.display();
        resume.display();

    }
}
