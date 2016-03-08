package ru.jdbc_first.examples;
// http://alfalavista.ru/index.php/2013-06-18-22-25-47/307-java-jdbc-java-jdbc

import java.sql.*;

public class ExampleTwo {
        public static void main(String[] argv) {
            System.out.println("Checking if Driver is registered with DriverManager.");
            try {
                Class.forName("org.postgresql.Driver");
            } catch (ClassNotFoundException e) {
                System.out.println("Couldn't find the driver!");
                System.out.println("Let's print a stack trace, and exit.");
                e.printStackTrace( ) ;
                return; }
            System. out.println("Registered the driver ok. so let's make a connection.");
            Connection c = null ;
            try {
                c = DriverManager.getConnection("jdbc:postgresql://localhost/test", "postgres", "23322332");
            } catch (SQLException se) {
                System.out.println("Couldn't connect: print out a stack trace and exit.");
                se.printStackTrace() ;
                return; }
            if (c != null)
                System.out.println("Hooray! We connected to the database!!");

//обращения к бд
            try {
                Statement st = c.createStatement();
                ResultSet rs = st.executeQuery("select * from authors");

                while(rs.next()){
                    //обработка результатов
                    int id = rs.getInt("id"); //Вернет строку, находящуюся во столбце с названием "id"
                    String name = rs.getString(2); //Вернет строку, находящуюся во втором столбце текущей строки
                    System.out.println("id = " + id + " name = " + name);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                //Обязательно необходимо закрыть соединение
                try {
                    if (c != null) {
                        c.close();
                        System.out.println("Closed!!!");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

//==========================

            Connection connection = null;
            Statement statement = null;

            try {
                connection = DriverManager.getConnection("jdbc:postgresql://localhost/test", "postgres", "23322332");
                statement = connection.createStatement();
                ResultSet resultSetSet = statement.executeQuery("select * from books");

                while(resultSetSet.next()){
                    //обработка результатов
                    int id = resultSetSet.getInt("id"); //Вернет строку, находящуюся во столбце с названием "id"
                    String tittle = resultSetSet.getString(2); //Вернет строку, находящуюся во втором столбце текущей строки
                    System.out.println("id = " + id + " tittle = " + tittle);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (statement != null)
                        statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                try {
                    if (connection != null)
                        connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }




        }
    }

