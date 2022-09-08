package com.hakim.dao;

import com.hakim.entities.Category;
import com.hakim.entities.Comment;
import com.hakim.entities.Post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACT
 */
public class ServerCall {
    
        
    
    public static List<Category> getCategories(Connection con){
        String query="select * from categories;";
        List<Category> list=new ArrayList<>();
        try(Statement statement=con.createStatement()){
            ResultSet set=statement.executeQuery(query);
            
            while(set.next()){
                list.add(new Category(set.getInt("cid"),set.getString("name"),set.getString("description")));
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServerCall.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
    public static List<Post> getAllPosts(Connection con){
        String query="select * from posts;";
        List<Post> list=new ArrayList<>();
        try(Statement statement=con.createStatement()){
            ResultSet set=statement.executeQuery(query);
            
            while(set.next()){
                list.add(
                        new Post(
                                set.getInt("pid"),
                                set.getInt("cid"),
                                set.getInt("id"),
                                set.getString("title"),
                                set.getString("content"),
                                set.getString("post_code"),
                                set.getString("pic"),
                                set.getTimestamp("post_date")
                        )
                );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServerCall.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }
    
    public static List<Post> getPostByCategory(Connection con,int category){
        String query="select * from posts where cid=?;";
        List<Post> list=new ArrayList<>();
       
        try(PreparedStatement statement=con.prepareStatement(query)){
            statement.setObject(1, category);
            ResultSet set=statement.executeQuery();
            
            while(set.next()){
                list.add(
                        new Post(
                                set.getInt("pid"),
                                set.getInt("cid"),
                                set.getInt("id"),
                                set.getString("title"),
                                set.getString("content"),
                                set.getString("post_code"),
                                set.getString("pic"),
                                set.getTimestamp("post_date")
                        )
                );
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ServerCall.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(list+"=> from back");
        return list;
    }
    
    public static List<Comment> getAllCommentsOfAPost(int pid,Connection con){
        String query="select * from comments where pid=?;";
        List<Comment> list=new ArrayList<>();
        
        try(PreparedStatement statement=con.prepareStatement(query)){
            statement.setObject(1, pid);
            ResultSet set=statement.executeQuery();
            
            while(set.next()){
                list.add(
                        new Comment(
                                set.getInt("comid"),
                                set.getInt("pid"),
                                set.getInt("uid"),
                                set.getString("commenttext"),
                                set.getString("username"),
                                set.getString("userpic"),
                                set.getTimestamp("comtime")
                        )
                );
                
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return list;
    }
}
