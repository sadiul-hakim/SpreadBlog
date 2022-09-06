package com.hakim.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author ACT
 */
public class ConnectionProvider {
    private static Connection connection;
    
    public static Connection getConnection(){
        
        try {
            if(connection==null){
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection=DriverManager.getConnection("jdbc:mysql://localhost/blog","Hakim","hakim@123");
            }
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(ConnectionProvider.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return connection;
    }
}
