package com.shuailee.decorator.gundec;


import com.shuailee.decorator.gundec.decorator.Magazine;

/**
 * @program: study-gupao
 * @description:
 * @author: shuai.li
 * @create: 2019-06-05 21:19
 **/
public class GunDemo {
    public static void main(String[] args) {
        Gun gun=new Kar98K();
        gun.fire();
        //装上弹夹
        gun=new Magazine(gun);
        gun.fire();

    }
}
