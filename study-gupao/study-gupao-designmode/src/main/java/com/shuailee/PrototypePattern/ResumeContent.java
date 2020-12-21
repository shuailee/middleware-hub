package com.shuailee.PrototypePattern;

import java.util.Hashtable;
/**
 * 使用者角色：维护一个注册表，并提供一个找出正确实例原型的方法。最后，提供一个获取新实例的方法，用来委托复制实例的方法生成新实例。
 *
 * 原型管理器： *
 * 将多个原型对象存储在一个集合中供客户端使用，它是一个专门负责克隆对象的工厂，其中定义了一个集合用于存储原型对象，
 * 如果需要某个原型对象的一个克隆，可以通过复制集合中对应的原型对象来获得。
 * */
public class ResumeContent {

    private static Hashtable<String, Resume> resumeMap = new Hashtable<String, Resume>();

    public static Resume getResume(String id) {
        Resume resume = resumeMap.get(id);
        //每次获取到的都是对象的浅拷贝
        return (Resume) resume.deepClone();
    }

    /**
     * 模拟多语言简历数据,设置原型信息
     * */
    public static void loadResumeData(){
        //中文简历
        Work work=new Work();
        work.setAdress("上海市东方路11号");
        work.setName("it");
        ChineseResume resume=new ChineseResume();
        resume.setId("1");
        resume.setName("张三");
        resume.setBirthday("2001-01-01");
        resume.setSex("男");
        resume.setCompany(work);
        if(!resumeMap.contains(resume)) {
            resumeMap.put(resume.getId(), resume);
        }

        //english
        Work work1=new Work();
        work1.setAdress("shanghai dongfang orad 11 no");
        work1.setName("it eniger");
        ChineseResume resume1=new ChineseResume();
        resume1.setId("2");
        resume1.setName("san zhang");
        resume1.setBirthday("2001-01-01");
        resume1.setSex("gender");
        resume1.setCompany(work1);
        if(!resumeMap.contains(resume1)) {
            resumeMap.put(resume1.getId(), resume1);
        }
    }

}
