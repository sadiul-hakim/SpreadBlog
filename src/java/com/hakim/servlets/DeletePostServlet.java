/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.hakim.servlets;

import com.hakim.dao.CommentsDao;
import com.hakim.dao.LikesDao;
import com.hakim.dao.PostDao;
import com.hakim.entities.Post;
import com.hakim.helper.ConnectionProvider;
import com.hakim.helper.Helper;
import com.hakim.helper.PathLocator;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author ACT
 */
public class DeletePostServlet extends HttpServlet {

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
            RequestDispatcher dispatcher=request.getRequestDispatcher("profile.jsp");
            int id=Integer.parseInt(request.getParameter("id"));
            PostDao dao=new PostDao(ConnectionProvider.getConnection());
            LikesDao like_dao=new LikesDao(ConnectionProvider.getConnection());
            CommentsDao comment_dao=new CommentsDao(ConnectionProvider.getConnection());
            Post post=dao.getAPost(id);
            boolean deleted=dao.deletePost(id);
            
            if(!deleted){
                out.print("<div class=\"alert alert-primary mb-0\" role=\"alert\">Could not delete Post.</div>");
                dispatcher.include(request, response);
            }else{
                Helper.deleteFile(PathLocator.getPostPicDeletePath(request,post.getPic()));
                like_dao.deleteLikesRelatedToPost(post.getPid());
                comment_dao.deleteCommentsRelatedToPost(post.getPid());
                response.sendRedirect("profile.jsp");
            }
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
