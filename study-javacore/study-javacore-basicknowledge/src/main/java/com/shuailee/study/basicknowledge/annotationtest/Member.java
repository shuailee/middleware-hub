package com.shuailee.study.basicknowledge.annotationtest;

/**
 * @program: study-javacore
 * @description: 数据库Meber表对应的实例Bean
 * @author: shuai.li
 * @create: 2018-06-29 17:17
 **/
@DBTable(name = "Member")
public class Member {

    @SQLString(name = "ID", value = 30)
    @Constraints(primarykey = true) //主键
    private String id;

    @SQLString(name = "Name", value = 30)
    private String name;

    @SQLInteger(name = "Age")
    private String age;

    @SQLString(name = "Description", value = 150, constraint = @Constraints( allowNull = true))
    private String desc;

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

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
