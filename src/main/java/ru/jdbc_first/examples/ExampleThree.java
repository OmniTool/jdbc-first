package ru.jdbc_first.examples;

import java.sql.*;
import java.text.DateFormat;

public class ExampleThree {
    private static String DB_DRIVER = "org.postgresql.Driver";
    private static String DB_CONNECTION = "jdbc:postgresql://localhost/test";
    private static String DB_USER = "postgres";
    private static String DB_PASSWORD = "23322332";

    public static void main(String[] argv) {
        try {
            createDbUserTable();
        } catch (SQLException e) {
            System.out.println("createDbUserTable ex");
            System.out.println(e.getMessage());
        }

        try {
            insertDbUserTable();
        } catch (SQLException e) {
            System.out.println("insertDbUserTable ex");
            System.out.println(e.getMessage());
        }

        try {
            selectDbUserTable();
        } catch (SQLException e) {
            System.out.println("selectDbUserTable 1 ex");
            System.out.println(e.getMessage());
        }

        try {
            updateDbUserTable();
        } catch (SQLException e) {
            System.out.println("updateDbUserTable ex");
            System.out.println(e.getMessage());
        }

        try {
            selectDbUserTable();
        } catch (SQLException e) {
            System.out.println("selectDbUserTable 2 ex");
            System.out.println(e.getMessage());
        }

        try {
            deleteDbUserTable();
        } catch (SQLException e) {
            System.out.println("deleteDbUserTable ex");
            System.out.println(e.getMessage());
        }

        try {
            selectDbUserTable();
        } catch (SQLException e) {
            System.out.println("selectDbUserTable 3 ex");
            System.out.println(e.getMessage());
        }




    }

    private static Connection getDBConnection() {
        Connection dbConnection = null;
        try {
            Class.forName(DB_DRIVER);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        try {
            dbConnection = DriverManager.getConnection(DB_CONNECTION, DB_USER, DB_PASSWORD);
            return dbConnection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return dbConnection;
    }



    private static void createDbUserTable() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;

        String createTableSQL = "CREATE TABLE DBUSER("
                + "USER_ID INTEGER NOT NULL, "
                + "USERNAME VARCHAR(20) NOT NULL, "
                + "CREATED_BY VARCHAR(20) NOT NULL, "
                + "CREATED_DATE DATE NOT NULL, " + "PRIMARY KEY (USER_ID) "
                + ")"; //-

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            // выполнить SQL запрос
            statement.execute(createTableSQL);
            System.out.println("Table \"dbuser\" is created!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    private static void insertDbUserTable() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;

        String insertTableSQL = "INSERT INTO DBUSER"
                + "(USER_ID, USERNAME, CREATED_BY, CREATED_DATE) " + "VALUES"
                + "(1,'mkyong','system', " + "to_date('"
                + getCurrentTimeStamp() + "', 'yyyy/mm/dd hh24:mi:ss'))";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();
            // выполнить SQL запрос
            statement.executeUpdate(insertTableSQL);
            System.out.println("User \"mkyong\" is added!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (statement != null) {
                statement.close();
            }
            if (dbConnection != null) {
                dbConnection.close();
            }
        }
    }

    private static void selectDbUserTable() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;

        String selectTableSQL = "SELECT USER_ID, USERNAME from DBUSER";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            // выбираем данные с БД
            ResultSet rs = statement.executeQuery(selectTableSQL);

            // И если что то было получено то цикл while сработает
            while (rs.next()) {
                String userid = rs.getString("USER_ID");
                String username = rs.getString("USERNAME");

                System.out.println("userid : " + userid);
                System.out.println("username : " + username);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void deleteDbUserTable() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;

        String deleteTableSQL = "DELETE FROM DBUSER WHERE USER_ID = 1";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            // выполняем запрос delete SQL
            statement.execute(deleteTableSQL);
            System.out.println("Record is deleted from DBUSER table!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void updateDbUserTable() throws SQLException {
        Connection dbConnection = null;
        Statement statement = null;

        String updateTableSQL = "UPDATE DBUSER SET USERNAME = 'mkyong_new' WHERE USER_ID = 1";

        try {
            dbConnection = getDBConnection();
            statement = dbConnection.createStatement();

            // выполняем запрос update SQL
            statement.execute(updateTableSQL);

            System.out.println("Record is updated to DBUSER table!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }



    private static String getCurrentTimeStamp() {
        java.util.Date today = new java.util.Date();
        DateFormat dateFormat = DateFormat.getDateInstance();
        return dateFormat.format(today.getTime()); }





}


