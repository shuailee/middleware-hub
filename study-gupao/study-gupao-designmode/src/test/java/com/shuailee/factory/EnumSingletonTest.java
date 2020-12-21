package com.shuailee.factory;

import com.shuailee.singleton.EnumSingleton;
import com.shuailee.singleton.SeriableSingleton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-04-17 13:57
 **/
public class EnumSingletonTest {
    public static void main(String[] args) {
        EnumSingleton s1=null;
        EnumSingleton s2=EnumSingleton.getInstance();
        s2.setData(new Object());

        FileOutputStream fos=null;
        try
        {
            fos = new FileOutputStream("EnumSingleton.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s2);
            oos.flush();
            oos.close();

            FileInputStream fis=new FileInputStream("EnumSingleton.obj");
            ObjectInputStream ois=new ObjectInputStream(fis);
            s1 = (EnumSingleton)ois.readObject();
            ois.close();

            System.out.println(s1.getData());
            System.out.println(s2.getData());
            System.out.println(s1.getData()==s2.getData());
            System.out.println(s1.getHungry()==s2.getHungry());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    //在JDK 枚举的语法特殊性，以及反射也为枚举保驾护航，让枚举式单例成为一种比较优雅的实现

    //不能用反射来创建枚举类型，在newInstance()方法中做了强制性的判断，如果修饰符是Modifier.ENUM 枚举类型，直接抛出异常

    //枚举类型其实通过类名和Class 对象类找到一个唯一的枚举对象。因此，枚举对象不可能被类加载器加载多次.反序列化也不会创建多个枚举对象
}
