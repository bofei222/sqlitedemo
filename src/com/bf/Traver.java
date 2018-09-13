package com.bf;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

/**
 * @author bofei
 * @date 2018/9/5 10:57
 */
public class Traver {



    static Connection conn = null;
    static PreparedStatement ps = null;
    static ResultSet rs = null;
    public static void main(String[] args) throws SQLException, IOException {
//        scanAndwrite2();
    }

    public static void scanAndwrite(int i) throws IOException, SQLException {
        long l = System.currentTimeMillis();

        Path path = Paths.get("F:/path0");
        try {
            System.out.println("正在写入库file" + i);
            String a = "jdbc:sqlite:C:/sqlite/db/file" + i + ".db";
            conn = DriverManager.getConnection(a);
            conn.setAutoCommit(false);
            String sql = "insert into FileInfo(path, scantime) values(?, ?)";
            ps = conn.prepareStatement(sql);

            Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path path1, BasicFileAttributes attrs) {
                    try {
                        ps.setString(1, path1.toString());
                        ps.setLong(2, System.currentTimeMillis());
                        ps.addBatch();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    return FileVisitResult.CONTINUE;
                }
            });
            int[] is = ps.executeBatch();
            ps.close();
            conn.commit();

        } finally {
            conn.close();
            System.out.println("写入file" + i + "用时: " + (System.currentTimeMillis() - l));
        }
    }
    static int id = 1;
    public static void scanAndwrite2(int i) throws SQLException {
        try {
            String url = "jdbc:sqlite:F:\\splitespace\\fileinfo" + i + ".db";
            conn = DriverManager.getConnection(url);
            conn.setAutoCommit(false);
            String sql = "insert into FileInfo(id, pid, path, type) values(?, ?, ?, ?)";
//            String sql = "insert into Food(id, pid, path, type) values(?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            MyFile myFile = new MyFile("F:/path0");
            myFile.setId(id);
            ps.setInt(1, myFile.getId());
            ps.setInt(2, 0);
            ps.setString(3, myFile.getName());
            ps.setInt(4, 0);
            ps.addBatch();
            ps = traverseFolder2(myFile, ps);
            int[] ints = ps.executeBatch();
            ps.close();
            conn.commit();
        } finally {
            conn.close();
        }
    }

    public static PreparedStatement traverseFolder2(MyFile myFile, PreparedStatement ps) throws SQLException {
        MyFile[] myFiles = myFile.listFiles();

        for (MyFile myFile2 : myFiles) {
            myFile2.setId(++id);
            ps.setInt(1, myFile2.getId());
            ps.setInt(2, myFile.getId());
            ps.setString(3, myFile2.getName());
            ps.setInt(4, myFile2.isDirectory() ? 0 : 1);
            ps.addBatch();

//            System.out.println(myFile2.getId() + " " + myFile2.getName() + " " + myFile.getId());
            if (myFile2.isDirectory()) {
//                        System.out.println("文件夹:" + MyFile2.getAbsolutePath());
                traverseFolder2(myFile2, ps);
            } else {
//                        System.out.println("文件:" + MyFile2.getAbsolutePath());
            }
        }
        return ps;
    }
}
