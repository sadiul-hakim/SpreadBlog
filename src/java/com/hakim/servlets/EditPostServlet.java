package com.hakim.servlets;

import com.hakim.dao.PostDao;
import com.hakim.entities.Post;
import com.hakim.helper.ConnectionProvider;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *
 * @author ACT
 */
public class EditPostServlet extends HttpServlet {

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
                
                  int id=Integer.parseInt(request.getParameter("post_id"));
                  System.out.println(id);
                PostDao dao=new PostDao(ConnectionProvider.getConnection());
                Post post=dao.getAPost(id);
//
                String title=request.getParameter("title") ;
                String content=request.getParameter("content");
                String code=request.getParameter("code");
                
                System.out.println(content);

            boolean updated=dao.updatePost(title, content, code, id);
            System.out.println(updated);
            if(!updated){
                out.print("<div class=\"alert alert-primary mb-0\" role=\"alert\">Could not edit post.</div>");
                RequestDispatcher dispatcher=request.getRequestDispatcher("EditPost.jsp");
                dispatcher.include(request, response);
            }else{
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
