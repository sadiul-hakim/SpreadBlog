package com.hakim.servlets;

import com.hakim.dao.PostDao;
import com.hakim.entities.Post;
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
public class CreatePostServlet extends HttpServlet {

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
            RequestDispatcher dispatcher=request.getRequestDispatcher("createPost.jsp");
            
            String title=request.getParameter("title");
            String category=request.getParameter("caterogy");
            String content=request.getParameter("content");
            String code=request.getParameter("code");
            Part part=request.getPart("pic");
            String pic_name=part.getSubmittedFileName();
            
            HttpSession session=request.getSession();
            User user=(User)session.getAttribute("user");
            int user_id=user.getId();
            
            String full_path=PathLocator.getPostPicUploadPath(request, user.getName(), user_id,pic_name );
            
            Helper.saveFile(part.getInputStream(), full_path);
            
            Connection connection=ConnectionProvider.getConnection();
            PostDao dao=new PostDao(connection);
            boolean isSaved=dao.addPost(new Post(Integer.parseInt(category),user_id,title,content,code,PathLocator.postPicFullName(user.getName(), user_id, pic_name)));
            
            if(!isSaved){
                out.print("<div class=\"alert alert-primary mb-0\" role=\"alert\">Could not create Post.</div>");
                dispatcher.include(request, response);
            }else{
                response.sendRedirect("index.jsp");
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

//