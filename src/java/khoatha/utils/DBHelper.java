/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoatha.utils;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author tahoa
 */
public class DBHelper implements Serializable {
    
    public static Connection createConnection() throws /*ClassNotFoundException*/ NamingException, SQLException, ClassNotFoundException {
//        
//        //1. Get current context
//        Context currentContext = new InitialContext();
//        
//        //2.Look up tomcat context
//        Context tomcatContext = (Context) currentContext.lookup("java:comp/env");
//        
//
//        //3. Look up DataSource
//        DataSource ds = (DataSource) tomcatContext.lookup("SE1708DS");
//        //4. Open connection from DataSource
//        Connection con = ds.getConnection();
//
//        return con;
        //1. Load Driver
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //2.Create connection String url
        String url  = "jdbc:sqlserver://localhost:1433;databaseName=SE1708";
        //3.Open connection
        Connection con = DriverManager.getConnection(url, "sa", "123456aA@$");
        
        return con;

    }

}
