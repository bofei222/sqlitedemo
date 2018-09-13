package com.bf;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.List;
import java.util.UUID;
import java.util.stream.Stream;

/**
 * @author bofei
 * @date 2018/9/7 13:52
 */
public class DecoratedPathTest {
    public static void main(String[] args) throws IOException {
        DecoratedPath path = new DecoratedPath(1, Paths.get("F:/Food"));
        Path path1 = Paths.get("F:/Food");
        DecoratedPath path2 = new DecoratedPath("F:/Food");

        Files.walkFileTree(path2, new FileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                return null;
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                return null;
            }

            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return null;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                return null;
            }
        });
        path1.getParent();
        File file = path.toFile();
        file.getParent();
        file.getParentFile();


//        file.tp

//        UserDefinedFileAttributeView udfa = Files.getFileAttributeView(path1, UserDefinedFileAttributeView.class);
//        udfa.write("id",  Charset.forName("UTF-8").encode(UUID.randomUUID().toString()));

//        udfa.delete("版权人");
//        List<String> attrNames = udfa.list(); // 读出所有属性
//        System.out.println(udfa.name());
//        String name = attrNames.get(0);
//        for (String name: attrNames) {
//            System.out.println("开始******************: " + name);
//            ByteBuffer bb = ByteBuffer.allocate(udfa.size(name)); // 准备一块儿内存块读取
//            udfa.read(name, bb);
//            bb.flip();
//            String value = Charset.defaultCharset().decode(bb).toString();
//            System.out.println(name + " : " + value);
//        }


        FileSystem aDefault = FileSystems.getDefault();

        aDefault.getPath("F:/Food");
        System.out.println(aDefault);

        System.out.println(path.toAbsolutePath());
        System.out.println(path.getId());
        System.out.println(path.getParent());
        System.out.println(path.getFileSystem());

        DecoratedPathTest d = new DecoratedPathTest();
        String name = d.getClass().getName();
        System.out.println(name);
//        Stream<Path> walk = Files.walk(path);
//        walk.forEach(x -> System.out.println(x));
    }

}
