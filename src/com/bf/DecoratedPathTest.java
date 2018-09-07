package com.bf;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * @author bofei
 * @date 2018/9/7 13:52
 */
public class DecoratedPathTest {
    public static void main(String[] args) throws IOException {
        DecoratedPath path = new DecoratedPath(1, Paths.get("F:/Food"));
        System.out.println(path.toAbsolutePath());
        System.out.println(path.getId());
        System.out.println(path.getParent());
        System.out.println(path.getFileSystem());


//        Stream<Path> walk = Files.walk(path);
//        walk.forEach(x -> System.out.println(x));
    }
}
