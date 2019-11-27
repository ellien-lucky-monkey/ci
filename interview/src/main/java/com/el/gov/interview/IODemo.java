package com.el.gov.interview;

import java.io.*;
import java.nio.charset.Charset;

/**
 * @author Jiangkui
 * @since 2019/11/21 11:24
 */
public class IODemo {

    public static void main(String[] args) {

    }

    public static void pipedIO(){
        String name = "ellien";
        PipedOutputStream out = new PipedOutputStream();


        PipedInputStream in = new PipedInputStream();
        byte[] buf = new byte[1024];
        try {
            in.connect(out);

            try {
                out.write(name.getBytes());
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


            while (in.read(buf) == -1) {
                return;
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("args = [" + new String(buf, Charset.defaultCharset()).trim() + "]");
    }


    public static void stanardIO(){
        byte[] bytes = new byte[1024];
        ByteArrayInputStream in = new ByteArrayInputStream("ellien".getBytes(Charset.defaultCharset()));
        while (true){
            try {
                if (!(in.read(bytes) == -1)) {
                    break;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            return;
        }
    }
}
