package com.el.gov.interview.socket;

import java.nio.ByteBuffer;

/**
 * @author Jiangkui
 * @since 2019/11/22 13:53
 */
public class Buffers {

    ByteBuffer readBuffer;
    ByteBuffer writeBuffer;

    public Buffers(int readCapacity, int writeCapacity){
        readBuffer = ByteBuffer.allocate(readCapacity);
        writeBuffer = ByteBuffer.allocate(writeCapacity);
    }

    public ByteBuffer getReadBuffer(){
        return readBuffer;
    }

    public ByteBuffer gerWriteBuffer(){
        return writeBuffer;
    }
}
