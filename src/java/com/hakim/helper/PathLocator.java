package com.hakim.helper;

import jakarta.servlet.http.HttpServletRequest;
import java.io.File;

/**
 *
 * @author ACT
 */
public class PathLocator {
    public static String getProfilePicPath(HttpServletRequest request,String name){
        return getRoot(request)+name;
    }
    public static String getPostPicPath(HttpServletRequest request,String userName,int userId,String name){
                
        return getRoot(request)+postPicFullName(userName,userId,name);
    }
    
    private static String getRoot(HttpServletRequest request){
        return request.getRealPath("/")+"profile_pics"+File.separator;
    }
    
    public static String postPicFullName(String userName,int userId,String name){
        return userName+"_"+userId+"_"+name;
    }
}
