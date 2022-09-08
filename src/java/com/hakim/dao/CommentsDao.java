package com.hakim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ACT
 */
public class CommentsDao {
    private Connection con;

    public CommentsDao(Connection con) {
        this.con = con;
    }
    
    public boolean addComment(int pid,int uid,String text,String name,String pic){
        boolean done=false;
        String query="insert into comments(commenttext,uid,pid,username,userpic) values(?,?,?,?,?)";
        try(PreparedStatement statement=con.prepareStatement(query)){
            statement.setObject(1, text);
            statement.setObject(2, uid);
            statement.setObject(3, pid);
            statement.setObject(4, name);
            statement.setObject(5, pic);
            
            statement.execute();
            done=true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return done;
    }
    
    public boolean removeComment(int pid,int comid){
        boolean done=false;
        
        String query="delete from comments where pid=? and comid=?";
        try(PreparedStatement statement=con.prepareStatement(query)){
           statement.setObject(1, pid);
           statement.setObject(2, comid);
           
            statement.executeUpdate();
            done=true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return done;
    }
    
    public int count(int pid){
        int count=0;
        
        String query="select count(*) from comments where pid = ?";
        try(PreparedStatement statement=con.prepareStatement(query)){
            statement.setInt(1, pid);
           
            
            ResultSet set=statement.executeQuery();
            if(set.next()){
                count=set.getInt(1);
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return count;
    }
    
    public boolean deleteCommentsRelatedToPost(int pid){
        boolean done=false;
        
        try(PreparedStatement statement=con.prepareStatement("delete from comments where pid = ?")){
            statement.setInt(1, pid);
                      
            statement.executeUpdate();
            
            done=true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return done;
    }
}
