package com.el.gov.interview.memory;

/**
 * @author Jiangkui
 * @since 2019/12/05 17:03
 */
public class Student extends People {
    private String s_name;
    private static Birthday birthday = new Birthday();

    public Student() {
        System.out.println("Student construct no args");

    }

    public Student(String name) {
        this.s_name = name;
        System.out.println("Student construct name = [" + name + "]");
    }

    public static void main(String[] args) {
        Student s = new Student("zhangsan");
        int age = 10;

        Student student  = new Student();
        System.out.println(age);
    }
}

class Birthday {
    private int year = 2010;
    private int month = 10;
    private int day = 1;
}
