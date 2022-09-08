package com.hakim.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ACT
 */
public class LikesDao {
    private Connection con;

    public LikesDao(Connection con) {
        this.con = con;
    }
    
    public boolean insertLike(int pid,int uid){
        String query="insert into likes(pid,uid) values(?,?)";
        boolean liked=false;
        try(PreparedStatement statement=con.prepareStatement(query)){
            statement.setInt(1, pid);
            statement.setInt(2, uid);
            
            statement.executeUpdate();
            liked=true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return liked;
    }
    
    public boolean removeLike(int pid,int uid){
        boolean disliked=false;
        
        try(PreparedStatement statement=con.prepareStatement("delete from likes where pid = ? and uid = ?")){
            statement.setInt(1, pid);
            statement.setInt(2, uid);
           
            statement.executeUpdate();
            
            disliked=true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return disliked;
    }
    
    public int likeCount(int pid){
        int count=0;
        
        String query="select count(*) from likes where pid = ?";
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
    
    public boolean liked(int uid,int pid){
        boolean liked=false;
        
        try(PreparedStatement statement=con.prepareStatement("select * from likes where pid = ? and uid = ?")){
            statement.setInt(1, pid);
            statement.setInt(2, uid);
           
            ResultSet set=statement.executeQuery();
            
            if(set.next()){
                liked=true;
            }
            
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return liked;
    }
    
    public boolean deleteLikesRelatedToPost(int pid){
        boolean done=false;
        
        try(PreparedStatement statement=con.prepareStatement("delete from likes where pid = ?")){
            statement.setInt(1, pid);
                      
            statement.executeUpdate();
            
            done=true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return done;
    }
}
