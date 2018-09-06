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

                    //关键方法1：打包
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
}
