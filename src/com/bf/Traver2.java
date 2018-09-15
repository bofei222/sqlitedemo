package com.bf;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * @author bofei
 * @date 2018/9/4 14:54
 */
public class Traver2 {
    public static void main(String[] args) {
        long l = System.currentTimeMillis();

        traver();
        System.out.println(System.currentTimeMillis() - l);
    }
    public static void traver() {
        Path path = Paths.get("F:/path0");

        try {
            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
//                    System.out.println(dir.toString());
                    return super.preVisitDirectory(dir, attrs);
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
//                    System.out.println("Traver.postVisitDirectory");
                    return super.postVisitDirectory(dir, exc);

                }

                @Override
                public FileVisitResult visitFile(Path path1, BasicFileAttributes attrs) throws IOException {
//                    System.out.println(path1.toString());
                    return FileVisitResult.CONTINUE;
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
