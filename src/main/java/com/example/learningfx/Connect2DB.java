package com.example.learningfx;

import java.sql.Connection;
import java.sql.DriverManager;

public class Connect2DB {

    public static Connection connectDb(){
        Connection connect;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect= DriverManager.getConnection("jdbc:mysql://localhost/pi_dev", "root", "");
            return  connect;
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}


//Class.forName("com.mysql.cj.jdbc.Driver");
//        // Set database connection parameters.
////            String url = ("jdbc:mysql://localhost:3306/nameOfDatabase");
////            String url = ("jdbc:mysql://185.27.134.10:3306/if0_35185411_sf_db");
////            String username = ("if0_35185411");
////            String password = ("86Nm5CspWaTClIw");
//        String url = ("jdbc:mysql://databases.000webhost.com:3306/if0_35185411_sf_db");
//        String username = ("id21356483_sf_db");
//        String password = ("SYM29624921sym>");
//
//        // Create the database connection.
//        connection = DriverManager.getConnection(url, username, password);
