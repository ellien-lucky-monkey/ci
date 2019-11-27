package com.el.gov.interview.nio;

/**
 * @author Jiangkui
 * @since 2019/11/22 16:39
 */
public class NIOPowerTest {

    public static final int CLIENT_SIZE = 300;

    public static void main(String[] args) {
        NIOClient[] client = new NIOClient[CLIENT_SIZE];
        for(int i = 0; i < client.length; i++) {
            client[i] = new NIOClient("Client" + i, "127.0.0.1", 9000);
            client[i].startup();
            System.out.println(System.currentTimeMillis() + " CLIENT" + i + " startup");
        }
    }
}
