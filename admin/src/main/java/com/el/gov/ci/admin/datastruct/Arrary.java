package com.el.gov.ci.admin.datastruct;

import io.netty.util.internal.MathUtil;

import java.util.Stack;

/**
 * @author Jiangkui
 * @since 2019/10/30 18:10
 */
public class Arrary {
    public static void main(String[] args) {
//        String[] s = new String[]{"1", "2", "3"};
//        s[1] = null;
//        System.out.println("args = [" + s.toString() + "]");
//        String str0 = "{[()]}";
//        String str1 = "{[(]]}";
//        String str2 = "{[())}";
//        String str3 = "{[(}))";
//        System.out.println(bracketMatch(str0));
//        System.out.println(bracketMatch(str1));
//        System.out.println(bracketMatch(str2));
//        System.out.println(bracketMatch(str3));


        System.out.println("args = [" + power(10, 3) + "]");
        System.out.println("args2 = [" + power2(10, 3) + "]");
        System.out.println(2/2);
        System.out.println(1/2);
        System.out.println(3/2);
        System.out.println(4/2);
        System.out.println(8/2);



        System.out.println(2%2);
        System.out.println("1%2="+1%2);
        System.out.println(3%2);
        System.out.println(4%2);
        System.out.println(8%2);

    }


    public static int bracketMatch(String str) {
        return 1;
    }

    public static int power(int x, int n) {
        int y = 0;
        if (n == 0) {
            y = 1;
        } else if (n % 2 == 1) {
            return x * power(x, n - 1);
        } else {
            return power(x, n / 2) * power(x, n / 2);
        }
        return y;
    }


    public static int power2(int x, int n) {
        int y = 0;
        if (n == 0) {
            y = 1;
        } else{
            y = power(x, n / 2);
            y = y*y;
            if (n%2 == 1) y = y*x;
        }
        return y;
    }
}
