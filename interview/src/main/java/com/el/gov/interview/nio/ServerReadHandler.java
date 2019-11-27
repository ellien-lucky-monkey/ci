package com.el.gov.interview.nio;

import java.nio.channels.SocketChannel;

/**
 * @author Jiangkui
 * @since 2019/11/22 16:33
 */
public class ServerReadHandler extends ReadHandler{
    private NIOServer nioServer;

    public ServerReadHandler(SocketChannel socketChannel, NIOServer nioServer) {
        super(socketChannel);
        this.nioServer = nioServer;
    }

    @Override
    protected synchronized void response(MsgPacket msgPacket) {
        final String content = new String(msgPacket.getData());
        System.out.println(System.currentTimeMillis() + "receive content -> "
                + content);

        // 模拟耗时操作
        try {
            Thread.sleep(100);
            // 模拟一下回复客户端
            nioServer.respone(socketChannel, System.currentTimeMillis() + "Server reply -> " + content);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }


}
