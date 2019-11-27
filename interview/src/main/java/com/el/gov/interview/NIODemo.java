package com.el.gov.interview;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

/**
 * @author Jiangkui
 * @since 2019/11/21 14:36
 */
public class NIODemo {

    public static void main(String[] args) {
        ByteBuffer buffer = ByteBuffer.allocate(128);
        buffer.put(new byte[]{-26, -120, -111, -25, -120, -79, -28, -67, -96});
        buffer.flip();//转换读模式

        Charset charset = Charset.forName("UTF-8");
        CharBuffer charBuffer = charset.decode(buffer);
        char[] charArr = Arrays.copyOf(charBuffer.array(), charBuffer.limit());
        System.out.println(charArr);



        CharBuffer charBuffer1 = CharBuffer.allocate(128);
        charBuffer1.append("我爱你");
        charBuffer1.flip();

        Charset utf8 = Charset.forName("UTF-8");
        ByteBuffer byteBuffer = utf8.encode(charBuffer1);
        byte[] bytes = Arrays.copyOf(byteBuffer.array(), byteBuffer.limit());
        System.out.println(Arrays.toString(bytes));

    }
}
