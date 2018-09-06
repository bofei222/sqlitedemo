package com.bf;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.*;

/**
 * @author bofei
 * @date 2018/9/6 14:30
 */
public class FoodDemo {
    static Connection conn = null;
    static PreparedStatement ps = null;
    static ResultSet rs = null;

    static int depth = 0;
    static long id = 1;


    public static void main(String[] args) throws IOException, SQLException {
//        long l = System.currentTimeMillis();
        Path path = Paths.get("F:/Food2");

//        try {
//            String a = "jdbc:sqlite:F:/sqlitespace/db/food.db";
//            conn = DriverManager.getConnection(a);
//            conn.setAutoCommit(false);
//            String sql = "insert into Food(id, pid, path, type) values(?, ?, ?, ?)";
//            ps = conn.prepareStatement(sql);

            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    dir.getParent();

                    System.out.println(dir.getFileName() + " " + "depth: " + depth++ + ", id: " + id++);

                    return super.preVisitDirectory(dir, attrs);
                }

                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//                    System.out.println(file.getFileName() + " " + "depth: " + depth + ", id: " + id++);
                    System.out.println(file.getParent().getFileName());
                    return super.visitFile(file, attrs);
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                    depth--;
                    return super.postVisitDirectory(dir, exc);
                }
            });
//            int[] is = ps.executeBatch();
//            ps.close();
//            conn.commit();
//        } finally {
//           conn.close();
//            System.out.println("用时: " + (System.currentTimeMillis() - l));
//        }
    }
}
