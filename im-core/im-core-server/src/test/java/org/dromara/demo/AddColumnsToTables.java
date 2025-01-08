package org.dromara.demo;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AddColumnsToTables {

    private static final String URL = "jdbc:mysql://mysql.myimkit.com:3306/im_chat_ai?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8&rewriteBatchedStatements=true&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "sa23Fke2zk";

    public static void main(String[] args) {
        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {

            DatabaseMetaData metaData = connection.getMetaData();
            ResultSet tables = metaData.getTables(null, null, "%", new String[]{"TABLE"});

            while (tables.next()) {
                String tableName = tables.getString("TABLE_NAME");
                addColumnsIfNotExists(statement, tableName);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void addColumnsIfNotExists(Statement statement, String tableName) throws SQLException {
        String[] columnsToAdd = {
            "create_dept BIGINT",
            "create_by BIGINT",
            "create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP",
            "update_by BIGINT",
            "update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP"
        };

        if (!tableExists(statement, tableName)) {
            System.out.println("Table " + tableName + " does not exist. Skipping...");
            return;
        }

        for (String column : columnsToAdd) {
            String columnName = column.split(" ")[0];
            if (!columnExists(statement, tableName, columnName)) {
                String alterTableSQL = "ALTER TABLE " + tableName + " ADD COLUMN " + column;
                statement.executeUpdate(alterTableSQL);
                System.out.println("Added column " + columnName + " to table " + tableName);
            } else {
                System.out.println("Column " + columnName + " already exists in table " + tableName);
            }
        }
    }

    private static boolean columnExists(Statement statement, String tableName, String columnName) throws SQLException {
        ResultSet columns = statement.executeQuery("SHOW COLUMNS FROM " + tableName + " LIKE '" + columnName + "'");
        return columns.next();
    }

    private static boolean tableExists(Statement statement, String tableName) throws SQLException {
        ResultSet tables = statement.executeQuery("SHOW TABLES LIKE '" + tableName + "'");
        return tables.next();
    }
}
