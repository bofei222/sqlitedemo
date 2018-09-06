package com.bf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;
import static java.nio.file.StandardCopyOption.COPY_ATTRIBUTES;
import static java.nio.file.LinkOption.NOFOLLOW_LINKS;

/**
 * @author bofei
 * @date 2018/9/5 16:12
 */
public class NIODemo {
    public static void main(String[] args) throws IOException {
        long l = System.currentTimeMillis();
        test(); //350
//        test2();  280
        System.out.println(System.currentTimeMillis() - l);
    }

    public static void test() {
        Path copy_from = Paths.get("C:\\sqlite\\test\\file1.db");
        Path copy_to = Paths.get("C:\\sqlite2\\test", copy_from.getFileName().toString());

        try {

            Files.copy(copy_from, copy_to, REPLACE_EXISTING, COPY_ATTRIBUTES, NOFOLLOW_LINKS);

        } catch (IOException e) {
            System.err.println(e);
        }
    }

    public static void test2() throws IOException {
        RandomAccessFile fromFile = new RandomAccessFile("C:\\sqlite\\test\\file1.db", "rw");
        FileChannel fromChannel = fromFile.getChannel();

        RandomAccessFile toFile = new RandomAccessFile("C:\\sqlite2\\test\\file1.db", "rw");
        FileChannel toChannel = toFile.getChannel();

        long position = 0;
        long count = fromChannel.size();

        fromChannel.transferTo(position, count, toChannel);
    }
}
