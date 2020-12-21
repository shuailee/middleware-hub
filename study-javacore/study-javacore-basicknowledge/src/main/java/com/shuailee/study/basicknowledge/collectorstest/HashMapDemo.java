package com.shuailee.study.basicknowledge.collectorstest;


import java.text.MessageFormat;
import java.util.*;

/**
 * @program: study-javacore
 * @description: hashmap学习
 * @author: shuai.li
 * @create: 2018-09-11 16:54
 **/
public class HashMapDemo {
    /**
     * 1 HashMap 继承于AbstractMap，实现了Map、Cloneable、java.io.Serializable接口
     * 2 HashMap 的实现不是同步的，这意味着它不是线程安全的。它的key、value都可以为null。此外，HashMap中的映射不是有序的
     * 3 HashMap 是一个散列表，它存储的内容是键值对(key-value)映射,HashMap 的实例有两个参数影响其性能：“初始容量” 和 “加载因子”。容量 是哈希表中桶的数量，初始容量
     *   只是哈希表在创建时的容量。加载因子 是哈希表在其容量自动增加之前可以达到多满的一种尺度。当哈希表中的条目数超出了加载因子与当前容量的乘积时，则要对该哈希表进行 rehash 操作
     *   （即重建内部数据结构），从而哈希表将具有大约两倍的桶数。通常，默认加载因子是 0.75, 这是在时间和空间成本上寻求一种折衷。加载因子过高虽然减少了空间开销，但同时也增加了查询成本
     *   （在大多数 HashMap 类的操作中，包括 get 和 put 操作，都反映了这一点）。在设置初始容量时应该考虑到映射中所需的条目数及其加载因子，以便最大限度地减少 rehash 操作次数。
     *    如果初始容量大于最大条目数除以加载因子，则不会发生 rehash 操作。
     * 4 HashMap是通过"拉链法"实现的哈希表,通过“拉链法”解决哈希冲突的。它包括几个重要的成员变量：table, size, threshold, loadFactor, modCount。
     *      table是一个Entry[]数组类型，而Entry实际上就是一个单向链表。哈希表的"key-value键值对"都是存储在Entry数组中的。
         　　size是HashMap的大小，它是HashMap保存的键值对的数量。
         　　threshold是HashMap的阈值，用于判断是否需要调整HashMap的容量。threshold的值="容量*加载因子"，当HashMap中存储数据的数量达到threshold时，就需要将HashMap的容量加倍。
         　　loadFactor就是加载因子。
         　　modCount是用来实现fail-fast机制的。
    * */

    public static void main (String [] args)
    {
       /* Map<String,String> user=new HashMap();
        user.put("柳柴","柳柴");
        user.put("柴柕","柴柕");
        user.put("Aa","Aa");
        user.put("BB","BB");*/

        String key="孫悟空";
        System.out.println(key.hashCode());
        int h = key.hashCode();
        System.out.println(h ^ (h >>> 16));



        //hashmapfor();

       /* HashMap<Integer, User> users = new HashMap<>(2);
        users.put(1, new User("张三", 25));
        users.put(3, new User("李四", 22));
        users.put(2, new User("王五", 28));
        System.out.println(users);
        HashMap<Integer,User> sortHashMap = sortHashMap(users);
        System.out.println(sortHashMap);*/
        /**
         * 控制台输出内容
         * {1=User [name=张三, age=25], 2=User [name=王五, age=28], 3=User [name=李四, age=22]}
         {2=User [name=王五, age=28], 1=User [name=张三, age=25], 3=User [name=李四, age=22]}
         */
    }


    /**
     *  HashMap排序题，
     * */
    public static HashMap<Integer, User> sortHashMap(HashMap<Integer, User> map) {
        // 首先拿到 map 的键值对集合
        Set<Map.Entry<Integer, User>> entrySet = map.entrySet();

        // 将 set 集合转为 List 集合，为什么，为了使用工具类的排序方法
        List<Map.Entry<Integer, User>> list = new ArrayList<Map.Entry<Integer, User>>(entrySet);
        // 使用 Collections 集合工具类对 list 进行排序，排序规则使用匿名内部类来实现
        Collections.sort(list, new Comparator<Map.Entry<Integer, User>>() {

            @Override
            public int compare(Map.Entry<Integer, User> o1,Map.Entry<Integer, User> o2) {
                //按照要求根据 User 的 age 的倒序进行排
                return o2.getValue().getAge()-o1.getValue().getAge();
            }
        });
        //创建一个新的有序的 HashMap 子类的集合
        LinkedHashMap<Integer, User> linkedHashMap = new LinkedHashMap<Integer, User>();
        //将 List 中的数据存储在 LinkedHashMap 中
        for(Map.Entry<Integer, User> entry : list){
            linkedHashMap.put(entry.getKey(), entry.getValue());
        }
        //返回结果 39.
        return linkedHashMap;
    }


    public static  void  hashmapfor()
    {
        HashMap map1=new HashMap();
        map1.put("a","a");
        map1.put("b","b");
        map1.put("c","c");
        System.out.println("map1 entrySet的遍历方法:");
        //根据entrySet()获取HashMap的“键值对”的Set集合。
        //获取Iterator迭代器,通过Iterator迭代器遍历得到的集合。
        Iterator iter=map1.entrySet().iterator();
        while (iter.hasNext()){
            Map.Entry entry=(Map.Entry)iter.next();
            // 获取key
            String key = (String)entry.getKey();
            // 获取value
            String value = (String)entry.getValue();
            System.out.println(MessageFormat.format("key:{0}, val:{1}",key,value));

        }



        HashMap map=new HashMap();
        map.put("1",1);
        map.put("2",2);
        map.put("3",3);
        System.out.println("map keyset的遍历方法:");
        //根据keySet()获取HashMap的“键”的Set集合
        //获取Iterator迭代器,通过Iterator迭代器遍历得到的集合。
        Iterator iter_k=map.keySet().iterator();
        while (iter_k.hasNext()){
            String key= (String) iter_k.next();
            //根据遍历的当前key从map中获取value
            Integer value=(Integer) map.get(key);
            System.out.println(MessageFormat.format("key:{0}, val:{1}",key,value));
        }

    }



    public void test()
    {

        List aa= Arrays.asList(new String []{"a", "b"});
        //将数组转成list后，是一个定长list，不能再往list添加元素进去了。如果在添加元素 aa.add("s")；会报UnsupportedOperationException异常
        //aa.add("c");
        //可以使用com.google.common.collect.Lists报的Lists.newArrayList("a","c");方法转换
        //List bb=Lists.newArrayList("a","c");

        System.out.println("hello world");
    }
}

class User{
    String name;
    Integer age;

    public User(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
