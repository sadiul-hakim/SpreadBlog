<%-- 
    Document   : EditPost
    Created on : Sep 5, 2022, 9:02:38 PM
    Author     : ACT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--<%@page import="java.util.List" %>--%>
<%--<%@page import="com.hakim.entities.Category" %>--%>
<%@page import="com.hakim.entities.Post" %>
<%@page import="com.hakim.helper.ConnectionProvider" %>
<%@page import="com.hakim.dao.ServerCall" %>
<%@page import="com.hakim.dao.PostDao" %>

<%
    //List<Category> categories=ServerCall.getCategories(ConnectionProvider.getConnection());
    int id=Integer.parseInt(request.getParameter("id"));
    PostDao dao=new PostDao(ConnectionProvider.getConnection());
    Post post=dao.getAPost(id);
%>
<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp">
        <jsp:param name="title" value="Edit Post" />
    </jsp:include>
    <body>
        <%@include file="Navbar.jsp" %>


        <div class="container">
            <div class="row">
                <div class="col-md-4 mx-auto mt-5">
                    <div class="card ">
                        <div class="card-header">
                            <h5 class="card-text">Edit Post</h5>
                        </div>
                        <div class="card-body">
                            <form action="EditPostServlet" method="post">
                                <label>Title : </label><br/>
                                <input type="text" name="title" placeholder="Anything" class="form-control" value="<%= post.getTitle()%>"/>
                                
                                <!--<label>Post Id : </label><br/>-->
                                <input type="hidden" name="post_id" value="<%= post.getPid()%>" class="form-control"/>
                                
                                <label>Content : </label><br/>
                                <textarea name="content" class="form-control" ><%= post.getContent()%></textarea>

                                <label>Code : </label><br/>
                                <textarea name="code" class="form-control" ><%= post.getCode()%></textarea><br/>

<!--                                <label>Choose File : </label>
                                <input type="file" name="pic" class="form-control"/><br/>-->

                                <input type="submit" value="Save" class="btn btn-primary"/>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
