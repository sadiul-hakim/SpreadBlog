package com.hakim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author ACT
 */
public class NotificationsDao {
    private Connection con;

    public NotificationsDao(Connection con) {
        this.con = con;
    }
    
    public boolean addNotification(int pid,String message){
        String query="insert into notifications(message,pid) value(?,?)";
        boolean added=false;
        try(PreparedStatement statement=con.prepareStatement(query)){
            statement.setString(1, message);
            statement.setInt(2, pid);
            
            statement.execute();
            added=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        return added;
    }
    
    public boolean removeNotification(int nid){
        String query="delete from notifications where nid=?";
        boolean removed=false;
        try(PreparedStatement statement=con.prepareStatement(query)){
            statement.setInt(1, nid);
            
            
            statement.executeUpdate();
            removed=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        return removed;
    }
}
