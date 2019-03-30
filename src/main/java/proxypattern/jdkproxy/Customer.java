package proxypattern.jdkproxy;

import proxypattern.Person;
/**
 * @Program: proxy-pattern-20190327
 * @Description: 客户需求
 * @Author: whx
 * @Create: 2019-03-28 22:35
 **/
public class Customer implements Person {
    public void findLove() {
        System.out.println("高富帅");
        System.out.println("身高 180cm");
        System.out.println("胸大， 6 块腹肌");
    }
}