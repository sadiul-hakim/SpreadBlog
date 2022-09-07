package com.hakim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ACT
 */
public class NotificationsDao {
    private Connection con;

    public NotificationsDao(Connection con) {
        this.con = con;
    }
    
    public boolean addNotification(int pid,String message,int uid){
        String query="insert into notifications(message,pid,uid) value(?,?,?)";
        boolean added=false;
        try(PreparedStatement statement=con.prepareStatement(query)){
            statement.setString(1, message);
            statement.setInt(2, pid);
            statement.setInt(3, uid);
            
            statement.execute();
            added=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        return added;
    }
    
    public boolean removeNotification(int nid,int uid){
        String query="delete from notifications where nid=? & uid";
        boolean removed=false;
        try(PreparedStatement statement=con.prepareStatement(query)){
            statement.setInt(1, nid);
            statement.setInt(2, uid);
            
            
            statement.executeUpdate();
            removed=true;
        }catch(Exception e){
            e.printStackTrace();
        }
        
        
        return removed;
    }
    
    public int count(int uid){
        int count=0;
        
        String query="select count(*) from notifications where uid = ?";
        try(PreparedStatement statement=con.prepareStatement(query)){
            statement.setInt(1, uid);
           
            
            ResultSet set=statement.executeQuery();
            if(set.next()){
                count=set.getInt(1);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return count;
    }
}
