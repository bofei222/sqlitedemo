package com.bf.filesystem;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.Date;
import java.util.List;

/**
 * @author bofei
 * @date 2018/9/10 10:12
 */
public class Test {

    public static void main(String[] args) throws IOException, InterruptedException {
        Path path = Paths.get("F:/Food");

        BasicFileAttributeView basicFileAttributeView = Files.getFileAttributeView(path, BasicFileAttributeView.class);
        BasicFileAttributes basicFileAttributes = basicFileAttributeView.readAttributes();
        System.out.println("创建时间 : " + new Date(basicFileAttributes.creationTime().toMillis()));

        DosFileAttributeView dosFileAttributeView = Files.getFileAttributeView(path, DosFileAttributeView.class);
        dosFileAttributeView.setHidden(true);

        // 写入一个属性
        UserDefinedFileAttributeView userDefinedFileAttributeView = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
        userDefinedFileAttributeView.write("版权人", Charset.defaultCharset().encode("啦啦啦"));

        List<String> attrNames = userDefinedFileAttributeView.list(); // 读出所有属性


        for (String name: attrNames) {
            System.out.println("开始******************");
            ByteBuffer bb = ByteBuffer.allocate(userDefinedFileAttributeView.size(name)); // 准备一块儿内存块读取
            userDefinedFileAttributeView.read(name, bb);
            bb.flip();
            String value = Charset.defaultCharset().decode(bb).toString();
            System.out.println(name + " : " + value);
        }
    }
}
