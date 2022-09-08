/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.hakim.helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ACT
 */
public class Helper {

    public static boolean deleteFile(String path) {
        boolean deleted;
        System.out.println(path);
        File file = new File(path);
        file.delete();
        deleted = true;

        return deleted;
    }
    
    public static boolean saveFile(InputStream is,String path){
        boolean saved=false;
        
        
        try (OutputStream os=new FileOutputStream(new File(path));){
            byte[] bytes=new byte[is.available()];
            is.read(bytes);
            
            os.write(bytes);
            os.flush();
            
            saved=true;
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Helper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        return saved;
    }
}
