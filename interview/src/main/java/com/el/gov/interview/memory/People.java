package com.el.gov.interview.memory;

/**
 * @author Jiangkui
 * @since 2019/12/05 17:37
 */
public class People {
    private String name;
    private int age;

    public People() {
    }

    public People(String name, int age) {
        this.name = name;
        this.age = age;
        System.out.println("People construct name = [" + name + "], age = [" + age + "]");
    }
}
