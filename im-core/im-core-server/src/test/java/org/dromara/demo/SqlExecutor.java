package org.dromara.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 遍历D:\Downloads\ruoyi目录下的所有SQL文件，并将这些文件中的SQL语句执行到指定的数据库ry-cloud中
 */
public class SqlExecutor {

    private static final String URL = "jdbc:mysql://mysql.myimkit.com:3306/ry-cloud?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "sa23Fke2zk";

    public static void main(String[] args) {
        File directory = new File("D:\\Downloads\\ruoyi");
        if (!directory.exists() || !directory.isDirectory()) {
            System.out.println("Directory does not exist or is not a directory: " + directory.getAbsolutePath());
            return;
        }

        File[] sqlFiles = directory.listFiles((dir, name) -> name.toLowerCase().endsWith(".sql"));
        if (sqlFiles == null || sqlFiles.length == 0) {
            System.out.println("No SQL files found in the directory.");
            return;
        }

        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            for (File sqlFile : sqlFiles) {
                executeSqlFile(statement, sqlFile);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void executeSqlFile(Statement statement, File sqlFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(sqlFile))) {
            StringBuilder sql = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("--")) {
                    continue;
                }
                sql.append(line).append(" ");
                if (line.endsWith(";")) {
                    statement.execute(sql.toString());
                    sql.setLength(0);
                }
            }
            if (sql.length() > 0) {
                statement.execute(sql.toString());
            }
            System.out.println("Executed SQL file: " + sqlFile.getName());
        } catch (IOException | SQLException e) {
            System.err.println("Error executing SQL file: " + sqlFile.getName());
            e.printStackTrace();
        }
    }
}
