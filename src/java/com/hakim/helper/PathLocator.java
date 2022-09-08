package com.hakim.helper;

import jakarta.servlet.http.HttpServletRequest;
import java.io.File;

/**
 *
 * @author ACT
 */
public class PathLocator {
    public static String getProfilePicUploadPath(HttpServletRequest request,String name,int uid){
        return getRoot(request)+"user"+"_"+uid+"_"+name;
    }
    public static String getProfilePicDeletePath(HttpServletRequest request,String name){
        return getRoot(request)+name;
    }
    public static String getPostPicUploadPath(HttpServletRequest request,String userName,int userId,String name){
                
        return getRoot(request)+postPicFullName(userName,userId,name);
    }
    
     public static String getPostPicDeletePath(HttpServletRequest request,String name){
        return getRoot(request)+name;
     }
    
    private static String getRoot(HttpServletRequest request){
        return request.getRealPath("/")+"profile_pics"+File.separator;
    }
    
    public static String postPicFullName(String userName,int userId,String name){
        return userName+"_"+userId+"_"+"postpic"+"_"+name;
    }
    
    public static String newProfilePicFullName(String name,int uid){
        return "user"+"_"+uid+"_"+name;
    }
}
