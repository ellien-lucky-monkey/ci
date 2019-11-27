package com.el.gov.interview.nio;

import java.nio.channels.SocketChannel;

/**
 * @author Jiangkui
 * @since 2019/11/22 16:34
 */
public class ClientReadHandler extends ReadHandler{

    private NIOClient nioClient;

    public ClientReadHandler(NIOClient nioClient, SocketChannel socketChannel) {
        super(socketChannel);
        this.nioClient = nioClient;
    }

    @Override
    protected void response(MsgPacket msgPacket) {
        String content = new String(msgPacket.getData());
        System.out.println(System.currentTimeMillis() + nioClient.getName() + " receive content -> "
                + content);
    }

}
