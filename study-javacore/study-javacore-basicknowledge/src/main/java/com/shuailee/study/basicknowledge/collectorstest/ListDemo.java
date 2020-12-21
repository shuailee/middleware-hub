package com.shuailee.study.basicknowledge.collectorstest;


import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ListDemo {

    public static void main(String [] args) {

        List<Product> products=new ArrayList<Product>(){{
            add(new Product("pu1",1));
            add(new Product("pu2",2));
            add(new Product("pu3",3));
        }};

        List<Order> orders=new ArrayList<Order>(){{
            add(new Order("o1",1));
            add(new Order("o2",2));
        }};

        List<Product> noQtePolicyList= products.stream().filter(p->orders.stream().noneMatch(q->q.getPuindex().equals(p.getPuindex()))).collect(Collectors.toList());

        System.out.println(noQtePolicyList.size());
    }


    private void sorttest()
    {
        List<String> ss= Arrays.asList("a","b","c");
        List<String>  bb=new ArrayList<String>();
        bb.add("d");
        Collections.sort(ss,Collator.getInstance());
    }





}


 class Product{
    private String name;
    private Integer puindex;

    Product(String name,Integer puindex){
        this.name=name;
        this.puindex=puindex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPuindex() {
        return puindex;
    }

    public void setPuindex(Integer puindex) {
        this.puindex = puindex;
    }
}

class Order{

    private String orderNo;
    private Integer puindex;
    Order(String orderNo,Integer puindex){
        this.orderNo=orderNo;
        this.puindex=puindex;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Integer getPuindex() {
        return puindex;
    }

    public void setPuindex(Integer puindex) {
        this.puindex = puindex;
    }
}