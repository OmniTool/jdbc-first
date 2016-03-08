package ru.jdbc_first.examples;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;

public class ExampleOne {
    public static void main(String[] argv) {

        System.out.println("Checking if Driver is registered with DriverManager.");

        try {

            Class.forName("org.postgresql.Driver");

        } catch (ClassNotFoundException cnfe) {

            System.out.println("Couldn't find the driver!");

            System.out.println("Let's print a stack trace, and exit.");

            cnfe.printStackTrace( ) ;

            System.exit(1) ; }

        System. out.println("Registered the driver ok. so let's make a connection.");

        Connection c = null ;

        try {

// Во втором и третьем аргументах передаются соответственно

// имя пользователя и пароль. Замените данными пользователя

// в своей системе.

            c = DriverManager.getConnection("jdbc:postgresql://localhost/test",

                    "postgres","23322332");

        } catch (SQLException se) {

            System.out.println("Couldn't connect: print out a stack trace and exit.");

            se.printStackTrace() ;

            System.exit(1); }

        if (c != null)

            System.out.println("Hooray! We connected to the database!");

        else

        System.out.println("We should never get here.");

    }

}
