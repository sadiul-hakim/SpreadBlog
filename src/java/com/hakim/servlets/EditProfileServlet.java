package com.hakim.servlets;

import com.hakim.dao.UserDao;
import com.hakim.entities.User;
import com.hakim.helper.ConnectionProvider;
import com.hakim.helper.Helper;
import com.hakim.helper.PathLocator;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

/**
 *
 * @author ACT
 */

@MultipartConfig(location="", fileSizeThreshold=1024*1024,
    maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class EditProfileServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            RequestDispatcher dispatcher=request.getRequestDispatcher("EditProfile.jsp");
            
            HttpSession session=request.getSession();
            User user=(User)session.getAttribute("user");
            
            String name=request.getParameter("name").equals("") ? user.getName() : request.getParameter("name");
            String email=request.getParameter("email").equals("") ? user.getEmail() : request.getParameter("email");
            String bio=request.getParameter("bio").equals("") ? user.getBio() : request.getParameter("bio");
            Part part=request.getPart("profile_pic");
            String profile_pic=part==null ? user.getProfile_pic() : part.getSubmittedFileName();
            
            String uploading_path=PathLocator.getProfilePicPath(request, profile_pic);
            String deletingPath=PathLocator.getProfilePicPath(request, user.getProfile_pic());
            
            
            if(!user.getProfile_pic().equals("default.jpg")){
                Helper.deleteFile(deletingPath);
            }
            
            Helper.saveFile(part.getInputStream(), uploading_path);
            
            Connection connection=ConnectionProvider.getConnection();
            UserDao dao=new UserDao(connection);
            boolean updated=dao.updateUser(name, email, bio, profile_pic);
            if(!updated){
                out.print("<div class=\"alert alert-primary mb-0\" role=\"alert\">Could not update profile.</div>");
                dispatcher.include(request, response);
            }else{
                User new_user=dao.getAUser(connection, email);
                session.setAttribute("user", new_user);
                response.sendRedirect("profile.jsp");
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
