package com.hakim.dao;

import com.hakim.entities.Post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACT
 */
public class PostDao {
    private final Connection con;

    public PostDao(Connection con) {
        this.con = con;
    }
    
    /**
     *
     * @param post
     * @return 
     */
    public boolean addPost(Post post){
        boolean executed=false;
        String sql="insert into Posts(cid,id,title,content,post_code,pic) values(?,?,?,?,?,?)";
        try(PreparedStatement statatment=con.prepareStatement(sql)){
            statatment.setObject(1, post.getCid());
            statatment.setObject(2, post.getId());
            statatment.setObject(3, post.getTitle());
            statatment.setObject(4, post.getContent());
            statatment.setObject(5, post.getCode());
            statatment.setObject(6, post.getPic());
            
            statatment.execute();
            
            executed=true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return executed;
    }
    
    
    
    public boolean updatePost(String title,String content,String code,int id){
        String sql="UPDATE Posts set title=?,content=?,post_code=? where pid=?";
        
        boolean updated=false;
        try(PreparedStatement statement1=con.prepareStatement(sql);){
            
            statement1.setObject(1, title);
            statement1.setObject(2, content);
            statement1.setObject(3, code);
            statement1.setObject(4, id);
            
            statement1.executeUpdate();
            
            updated =true;
                        
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return updated;
    }
    
    public Post getAPost(int postId){
        Post post=new Post();
        try(PreparedStatement statement=con.prepareStatement("select * from posts where pid = ?");){
            statement.setObject(1, postId);
            ResultSet set=statement.executeQuery();
            
            if(set.next()){
            
                post.setPid(set.getInt("pid"));
                post.setCid(set.getInt("cid"));
                post.setId(set.getInt("id"));
                post.setTitle(set.getString("title"));
                post.setContent(set.getString("content"));
                post.setCode(set.getString("post_code"));
                post.setPic(set.getString("pic"));
                post.setPost_date(set.getTimestamp("post_date"));
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return post;
    }
    
    public boolean deletePost(int postId){
        boolean deleted=false;
        try(PreparedStatement statement=con.prepareStatement("delete from posts where pid = ?");){
            statement.setObject(1, postId);
            statement.execute();
            deleted=true;
                        
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return deleted;
    }
    
}
