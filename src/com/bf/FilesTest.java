package com.bf;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.List;


/**
 * @author bofei
 * @date 2018/9/7 9:58
 */
public class FilesTest {
    static int id = 0;
    public static void main(String[] args) throws IOException {
        long l = System.currentTimeMillis();
        Path path = Paths.get("F:/path0");

//        FileSystems.getDefault().getPath("F")

//        File file = new File("F:/Food");
//
////        DecoratedPath d = new DecoratedPath(id, path);
////        System.out.println(path.getParent().getFileName());
//
//        Stream<Path> walk = Files.walk(path);
//        walk.forEach(x -> System.out.println(x));
//
//        System.out.println("*****************************");
//        Stream<Path> list = Files.list(path);
//        list.forEach(x -> System.out.println(x));


        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

//                System.out.println(dir.getFileName() + " id: "  + dir.hashCode() + " , pid: " + dir.getParent().hashCode());
//                System.out.println(dir.toAbsolutePath() + " " + dir.getParent().toAbsolutePath() );

//                String id = UUID.randomUUID().toString();
//                UserDefinedFileAttributeView udfa = Files.getFileAttributeView(dir, UserDefinedFileAttributeView.class);
//
//                udfa.write("id",  Charset.forName("UTF-8").encode(id));
//                String pid = getId(dir.getParent());
//                System.out.println(id + "  " + pid);
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//                System.out.println(file.getFileName() + " id: " + file.hashCode() + " ,pid: " + file.getParent().hashCode());
//                System.out.println(file.getFileName() + " " + file.getParent().getFileName());
//                String id = UUID.randomUUID().toString();
//                UserDefinedFileAttributeView udfa = Files.getFileAttributeView(file, UserDefinedFileAttributeView.class);
//                udfa.write("id",  Charset.forName("UTF-8").encode(id));
//                String pid = getId(file.getParent());
//                System.out.println(id + "  " + pid);
                return super.visitFile(file, attrs);
            }
        });


        System.out.println(System.currentTimeMillis() - l);


    }

    public static String getId(Path path1) throws IOException {
        UserDefinedFileAttributeView udfa = Files.getFileAttributeView(path1, UserDefinedFileAttributeView.class);
        String value = "";
        List<String> attrNames = udfa.list(); // 读出所有属性
        if (attrNames.size() != 0) {
            String name = attrNames.get(0);
//            System.out.println("开始******************: " + name);
            ByteBuffer bb = ByteBuffer.allocate(udfa.size(name)); // 准备一块儿内存块读取
            udfa.read(name, bb);
            bb.flip();
             value = Charset.defaultCharset().decode(bb).toString();
//            System.out.println(name + " : " + value);
        }

        return value;
    }
}
