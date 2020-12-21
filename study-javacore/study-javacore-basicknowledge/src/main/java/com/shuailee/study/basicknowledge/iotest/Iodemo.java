package com.shuailee.study.basicknowledge.iotest;

import com.shuailee.study.model.User;
import org.junit.Test;

import java.io.*;

public class Iodemo {

    /**
     * 输入字节流 InputStream
     *      InputStream 是所有的输入字节流的父类，它是一个抽象类。
     *      ByteArrayInputStream、StringBufferInputStream、FileInputStream 是三种基本的介质流，它们分别从Byte 数组、StringBuffer、和本地文件中读取数据。
     *      PipedInputStream 是从与其它线程共用的管道中读取数据，与Piped 相关的知识后续单独介绍。
     *      ObjectInputStream 和所有FilterInputStream 的子类都是装饰流（装饰器模式的主角）。
     *
     * 输出字节流 OutputStream
     *      OutputStream 是所有的输出字节流的父类，它是一个抽象类。
     *      ByteArrayOutputStream、FileOutputStream 是两种基本的介质流，它们分别向Byte 数组、和本地文件中写入数据。
     *      PipedOutputStream 是向与其它线程共用的管道中写入数据。
     *      ObjectOutputStream 和所有FilterOutputStream 的子类都是装饰流。
     *
     * 总结：
     *      输入流：InputStream或者Reader：从文件中读到程序中；
     *      输出流：OutputStream或者Writer：从程序中输出到文件中；
     * */
    public static void main(String [] args) {
        Iodemo d=new Iodemo();

        String str="这是io的第一个学习例子\n";
        d.writeFile("/Users/wangerqiao/test.txt",str);

        String result=d.readFile("/Users/wangerqiao/test.txt");
        System.out.print(result);

        d.copyFile("/Users/wangerqiao/test.txt","/Users/wangerqiao/test1.txt");
        System.out.print("复制文件结果："+d.readFile("/Users/wangerqiao/test1.txt"));

    }

    /**
     * 使用输入字节流读取文件内容
     * */
    public String readFile (String path) {
        FileInputStream fis=null;
        String result="";
        try {
            // 根据path路径实例化一个输入流的对象，如果该路径不存在该文件，则会报错
            fis=new FileInputStream(path);
            // 返回这个输入流中可以被读的剩下的字节的估计值
            int size = fis.available();
            // 根据字节数据的估计值创建byte数组
            byte[] array =new byte[size];
            // 把流中的数据读取到定义的数组中
            fis.read(array);
            // 将byte数组转为字符串形式
            result=new String(array);

        }catch (FileNotFoundException e) {
            //读取的文件不存在时报的异常
            e.printStackTrace();
        }
        catch (IOException ex){
            ex.printStackTrace();
        }finally {
            if( fis != null){
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  result;
    }

    public void writeFile(String path,String content) {
        //输出流，将内存中的数据输出到文件，如果路径不存在文件不会报错
        FileOutputStream fos=null;
        try {
            // 根据文件路径创建输出流
            fos=new FileOutputStream(path);
            // 将string字符串转为byte数组
            byte [] args=content.getBytes();
            // 将字节数组写入到输出流, 会保存到文件，并覆盖原来的内容
            fos.write(args);

        }catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if( fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }



    /**
     * 文件复制
     * 读取第一个文件的输入流，并赋值给第二个文件的输出流，完成赋值
     * @param filePath_old : 需要复制文件的路径
     * @param filePath_new : 复制文件存放的路径
     */
    public void copyFile( String filePath_old  , String filePath_new){
        FileInputStream fis=null ;
        FileOutputStream fout = null ;
        try {
            // 根据path路径实例化一个输入流的对象
            fis  = new FileInputStream( filePath_old );

            //2. 返回这个输入流中可以被读的剩下的bytes字节的估计值；
            int size =  fis.available() ;
            //3. 根据输入流中的字节数创建byte数组；
            byte[] array = new byte[size];
            //4.把数据读取到数组中；
            fis.read( array ) ;

            //5、根据文件路径创建输出流
            fout = new FileOutputStream( filePath_new ) ;

            //5、把byte数组输出；
            fout.write( array );

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }finally{
            if ( fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if ( fout != null ) {
                try {
                    fout.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Test
    public void test2() throws IOException, ClassNotFoundException {
        //对象输出流
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(new File("E://obj.txt")));
        objectOutputStream.writeObject(new User( 100,"zhangsan","2222222222"));
        objectOutputStream.close();
        //对象输入流
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(new File("E://obj.txt")));
        User user = (User)objectInputStream.readObject();
        System.out.println(user);
        objectInputStream.close();
    }

}
