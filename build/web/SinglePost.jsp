<%-- 
    Document   : SinglePost.jsp
    Created on : Sep 5, 2022, 7:34:22 PM
    Author     : ACT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.hakim.entities.Post" %>
<%@page import="com.hakim.entities.User" %>
<%@page import="com.hakim.helper.ConnectionProvider" %>
<%@page import="com.hakim.dao.PostDao" %>
<%@page import="com.hakim.dao.UserDao" %>

<%
    //Getting post details
    String id=request.getParameter("id");
    PostDao dao=new PostDao(ConnectionProvider.getConnection());
    Post post=dao.getAPost(Integer.parseInt(id));
    
    //Getting user details
    UserDao user_dao=new UserDao(ConnectionProvider.getConnection());
    int user_id=post.getId();
    User user=user_dao.getUserById(user_id);
%>

<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp">
        <jsp:param name="title" value="<%= post.getTitle()%>"/>
    </jsp:include>
    <body>
        <%@include file="/Navbar.jsp" %>

        <div class="container mt-4">
            <div class="card">
                <div class="card-header d-flex">
                    <img src="profile_pics/<%= user.getProfile_pic()%>" width="60px" height="60px" style="border-radius: 50%;border:2px solid wheat;margin-right: 10px;"/>
                    <div class="d-flex flex-column">
                        <a href="SingleUser.jsp?id=<%= user.getId()%>" style="color:#333;font-size: 20px;" class="m-0"><%= user.getName()%></a>
                        <p><%= user.getEmail()%></p>
                    </div>
                </div>
                <div class="card-body">
                    <img src="profile_pics/<%= post.getPic()%>" width="700px" height="400px"/>
                    <h3 class="display-4"><%= post.getTitle()%></h3>
                    <p class="text-muted"><%= post.getPost_date()%></p>
                    <br/>
                    
                    <p><%= post.getContent()%></p>
                    
                    <br/>
                    
                    <%if(!post.getCode().equals("")){%>
                        
                    <h3>Codes : </h3>
                    <pre><code class="language-css"><%= post.getCode()%></code></pre>
                        
                    <%}%>
                </div>
            </div>
        </div>
    </body>
</html>
