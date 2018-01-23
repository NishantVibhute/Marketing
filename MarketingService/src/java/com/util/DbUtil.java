/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Nishant
 */
public class DbUtil {

    Connection con;
    String url;

    public Connection getConnection() {
        try {
            url = ("jdbc:mysql://marketing.cogc0bcdvlsa.ap-south-1.rds.amazonaws.com:3306/marketing");
            Class.forName("com.mysql.jdbc.Driver");
            this.con = DriverManager.getConnection(url, "root", "marketingroot");
            return con;

//           url = ("jdbc:mysql://localhost:3306/my_society");
//            Class.forName("com.mysql.jdbc.Driver");
//            this.con = DriverManager.getConnection(url, "root", "root");
//            return con;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;

    }

    public void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
