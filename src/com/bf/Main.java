package com.bf;

import java.sql.*;

/**
 * @author bofei
 * @date 2018/9/4 17:12
 */
public class Main {

    /**
     * Connect to a sample database
     *
     * @param fileName the database file name
     */
    public static void createNewDatabase(String fileName) {

        String url = "jdbc:sqlite:C:/sqlite/db/" + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Create a new table in the test database
     *
     */
    public static void createNewTable(int i) {
        // SQLite connection string
//        String url = "jdbc:sqlite:C://sqlite/db/file" + i +".db";
        String url = "jdbc:sqlite:F:\\splitespace\\fileinfo" + i + ".db";
        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS FileInfo (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	path VARCHAR(255) NOT NULL,\n"
                + "	scantime BIGINT\n"
                + ");";

        String sql2 = "CREATE TABLE IF NOT EXISTS FileInfo (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	pid integer,\n"
                + "	path VARCHAR(255) NOT NULL,\n"
                + "	type integer\n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql2);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        createNewDatabase("test2.db");
        for (int i = 1; i < 31; i++) {
            createNewTable(i);

        }
    }
}
