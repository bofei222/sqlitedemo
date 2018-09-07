package com.bf;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.Stream;

/**
 * @author bofei
 * @date 2018/9/7 9:58
 */
public class FilesTest {
    static int id = 0;
    public static void main(String[] args) throws IOException {
        Path path = Paths.get("F:/Food");


        File file = new File("F:/Food");

//        DecoratedPath d = new DecoratedPath(id, path);
//        System.out.println(path.getParent().getFileName());

        Stream<Path> walk = Files.walk(path);
        walk.forEach(x -> System.out.println(x));

        System.out.println("*****************************");
        Stream<Path> list = Files.list(path);
        list.forEach(x -> System.out.println(x));

        System.out.println("****************************");

        Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
//                System.out.println(dir.getFileName() + " id: "  + dir.hashCode() + " , pid: " + dir.getParent().hashCode());
                System.out.println(dir.toAbsolutePath() + " " + dir.getParent().toAbsolutePath() );
                return super.preVisitDirectory(dir, attrs);
            }

            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//                System.out.println(file.getFileName() + " id: " + file.hashCode() + " ,pid: " + file.getParent().hashCode());
                System.out.println(file.getFileName() + " " + file.getParent().getFileName());
                return super.visitFile(file, attrs);
            }
        });




    }
}
