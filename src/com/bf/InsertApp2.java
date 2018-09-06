package com.bf;

import java.sql.*;
import java.util.Date;

/**
 * @author bofei
 * @date 2018/9/5 9:27
 */
public class InsertApp2 {
    /**
     * 对比“批处理”与“非批处理”的执行效率
     */
    public static void main(String[] args) throws SQLException {
        /*
        //非批处理，插入100条数据所花费的时间
        long start = System.currentTimeMillis();
        for (int i = 0; i < 100; i++)
            create(i);
        long end = System.currentTimeMillis();
        System.out.println("create:" + (end - start));
        //批处理，插入100条数据所花费的时间
        start = System.currentTimeMillis();
        createBatch();
        end = System.currentTimeMillis();
        System.out.println("createBatch:" + (end - start));
        */
        long l = System.currentTimeMillis();
        createBatch();
        System.out.println(System.currentTimeMillis() - l);
    }
    /**
     * 非批处理-插入1条数据
     */
    static void create(int i) throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            //JdbcUtils为自定义的操作类，这里不多介绍
            String a = "jdbc:sqlite:C:/sqlite/db/test2.db";
            conn = DriverManager.getConnection(a);
            String sql = "insert into person values("+i+", 'leo')";
            ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            //执行插入
            ps.executeUpdate();
        } finally {
            //释放资源
            conn.close();

        }
    }
    /**
     * 批处理-插入100条数据
     */
    static void createBatch() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            String a = "jdbc:sqlite:C:/sqlite/db/test2.db";
            conn = DriverManager.getConnection(a);
            conn.setAutoCommit(false);
            String sql = "insert into person values(?, ?)";
            ps = conn.prepareStatement(sql);
            //注意批处理与“非批处理”循环放的位置
            for (int i = 0; i < 1000000; i++) {

                ps.setInt(1, i);
                ps.setString(2, "meimei");
                //关键方法1：打包
                ps.addBatch();
            }
            int[] is = ps.executeBatch();
            ps.close();
            conn.commit();
            //关键方法2：执行

        } finally {
            conn.close();
        }
    }

    static void createBatch2() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            String a = "jdbc:sqlite:C:/sqlite/db/test2.db";
            conn = DriverManager.getConnection(a);
            conn.setAutoCommit(false);
            String prefix = "insert into person values";
            StringBuffer suffix = new StringBuffer();


            for (int i = 0; i < 100; i++) {

                for (int j = 0; j < 100; j++) {
                    suffix.append("(" + j + ",'meimei2'" + "),");
                }
                // 构建完整SQL
                String sql = prefix + suffix.substring(0, suffix.length() - 1);
                ps = conn.prepareStatement(sql);
                // 添加执行SQL
                ps.addBatch();
                // 执行操作

                ps.executeBatch();

                ps.close();
                // 提交事务
                conn.commit();
                // 清空上一次添加的数据
                suffix = new StringBuffer();

            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
