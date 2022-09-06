package com.hakim.dao;

import com.hakim.entities.User;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACT
 */
public class UserDao {
    private final Connection con;

    public UserDao(Connection con) {
        this.con = con;
    }
    
    /**
     *
     * @param user
     * @return
     */
    public boolean insertUser(User user){
        boolean executed=false;
        String sql="insert into User(user_name,email,user_password,profile_pic) values(?,?,?,?)";
        try(PreparedStatement statatment=con.prepareStatement(sql)){
            statatment.setObject(1, user.getName());
            statatment.setObject(2, user.getEmail());
            statatment.setObject(3, user.getPassword());
            statatment.setObject(4, user.getProfile_pic());
            
            statatment.execute();
            
            executed=true;
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return executed;
    }
    
    public User getUserByEmail(String email,String password){
        String sql="select * from user where email = ? and user_password = ?";
        User user=null;
        
        try(PreparedStatement statement=con.prepareStatement(sql)){
            statement.setString(1, email);
            statement.setString(2, password);
            
            ResultSet set=statement.executeQuery();
            
            if(set.next()){
                user=new User();
                user.setId(set.getInt("id"));
                user.setName(set.getString("user_name"));
                user.setEmail(set.getString("email"));
                user.setPassword(set.getString("user_password"));
                user.setProfile_pic(set.getString("profile_pic"));
                user.setBio(set.getString("bio"));
                user.setDateTime(set.getTimestamp("rdate"));
            }
            
            set.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return user;
    }
    
    public User getUserById(int id){
        String sql="select * from user where id = ?";
        User user=null;
        
        try(PreparedStatement statement=con.prepareStatement(sql)){
            statement.setObject(1, id);
            
            ResultSet set=statement.executeQuery();
            
            if(set.next()){
                user=new User();
                user.setId(set.getInt("id"));
                user.setName(set.getString("user_name"));
                user.setEmail(set.getString("email"));
                user.setPassword(set.getString("user_password"));
                user.setProfile_pic(set.getString("profile_pic"));
                user.setBio(set.getString("bio"));
                user.setDateTime(set.getTimestamp("rdate"));
            }
            
            set.close();
            
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return user;
    }
    
    public boolean updateUser(String name,String email,String bio,String profile_pic){
        String sql="UPDATE User set user_name=?,bio=?,profile_pic=? where email=?";
        
        boolean updated=false;
        try(PreparedStatement statement1=con.prepareStatement(sql);){
            
            statement1.setObject(1, name);
            statement1.setObject(2, bio);
            statement1.setObject(3, profile_pic);
            statement1.setObject(4, email);
            
            statement1.executeUpdate();
            
            updated =true;
                        
        }catch(SQLException e){
            e.printStackTrace();
        }
        
        return updated;
    }
    
    public User getAUser(Connection con,String email){
        User new_user=new User();
        try(PreparedStatement statement=con.prepareStatement("select * from user where email =?");){
            statement.setObject(1, email);
            ResultSet set=statement.executeQuery();
            
            if(set.next()){
                    
                    new_user.setId(set.getInt("id"));
                    new_user.setName(set.getString("user_name"));
                    new_user.setEmail(set.getString("email"));
                    new_user.setPassword(set.getString("user_password"));
                    new_user.setProfile_pic(set.getString("profile_pic"));
                    new_user.setBio(set.getString("bio"));
                    new_user.setDateTime(set.getTimestamp("rdate"));
                }
            
        } catch (SQLException ex) {
            Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return new_user;
    }
    
}
