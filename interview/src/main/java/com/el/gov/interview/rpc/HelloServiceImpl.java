package com.el.gov.interview.rpc;

/**
 * @author Jiangkui
 * @since 2019/12/03 14:48
 */
public class HelloServiceImpl implements HelloService{
    @Override
    public String hello(String name) {
        return "Hello " + name;
    }

    @Override
    public String hi(String msg) {
        return "Hi, " + msg;
    }
}
