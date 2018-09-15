package com.bf;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @author bofei
 * @date 2018/9/4 13:06
 */
public class CreateFile {
    public static void main(String[] args) throws IOException {
//        mkdir();
        long l = System.currentTimeMillis();
//        test3(); //  7995 9217 9537 7076
//        test2(); // 9573 8009 8894
        test4();
        System.out.println(System.currentTimeMillis() - l);

    }

    public static void test2() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
            for (int k = 0; k < 100; k++) {
//                File file = new File("F:/path0/path" + i  + "_2/" + k + ".txt");
                    File file = new File("F:/path0/path" + i + "_1/path" + j + "_2/" + k + ".txt");
                if (!file.getParentFile().exists()) {
                    boolean mkdirs = file.getParentFile().mkdirs();
                    if (!mkdirs) {
                        System.out.println("父路径创建失败");
                    }
                }
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            }
        }
    }
    public static void test4() throws IOException {
        for (int i = 10000; i < 20000; i++) {
//            long l = System.currentTimeMillis();
            Path file = Paths.get("F:\\path0\\path0_1\\path0_2\\" + i + ".txt");
            Files.createFile(file);
//            System.out.println(System.currentTimeMillis() - l);
        }
    }
    public static void test3() throws IOException {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
            for (int k = 0; k < 100; k++) {
                Path filePath = Paths.get("F:/path0/path" + i + "_1/path" + j + "_2/" + k + ".txt");
//                    File file = new File("F:/path0/path" + i + "_1/path" + j + "_2/" + k + ".txt");
                Path parent = filePath.getParent();
                boolean exists = Files.exists(parent, new LinkOption[]{LinkOption.NOFOLLOW_LINKS});
                if (!exists) {
                    Files.createDirectories(parent);
                }
                Files.createFile(filePath);
            }
            }
        }
    }
}
