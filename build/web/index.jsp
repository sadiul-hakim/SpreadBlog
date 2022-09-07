<%-- 
    Document   : index
    Created on : Sep 3, 2022, 11:53:55 AM
    Author     : ACT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.hakim.entities.User" %>
<%@page import="com.hakim.entities.Post" %>
<%@page import="com.hakim.helper.ConnectionProvider" %>
<%@page import="com.hakim.dao.ServerCall" %>
<%@page import="java.util.List" %>

<%
    User t=(User) session.getAttribute("user");
    List<Post> posts=ServerCall.getAllPosts(ConnectionProvider.getConnection());
%>


<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp">
        <jsp:param name="title" value="Home Page"/>
    </jsp:include>
    <body>
        <%@include file="/Navbar.jsp" %>

        <div class="wrapper pt-5 pb-4">

            <div class="container text-white">
                <h1 class="display-4">Welcome To Spread</h1>
                <p>Spread is a knowledge sharing platform.Here you can create your own account and start sharing your knowledge.And you can read others content also.That will increase you knowledge.In this modern age we should share our knowledge.</p>

                <a href="login.jsp" class="btn btn-outline-light"> <span class="fa fa-external-link"></span> Start ! It's Free.</a>
                       
            </div>

        </div>
        <br/>
        <div class="container">
            <div class="row">
                <%-- Single Post--%>
                <%for(Post p:posts){%>
                <%String showingText=p.getContent().length() > 100 ? p.getContent().substring(0,100) : p.getContent(); %>
                <div class="col-md-4 mb-3">
                    <div class="card">
                        
                        <div class="card-body">
                            <h5 class="card-title"><%= p.getTitle()%></h5>
                            <small class="card-subtitle mb-2 text-muted"><%= p.getPost_date()%></small>
                            <p class="card-text"><%= showingText %>...</p>
                            <a href="SinglePost.jsp?id=<%= p.getPid()%>" class="btn btn-outline-primary">Read More</a>
                        </div>
                    </div>
                </div>
                <%}%>
                <%-- End --%>
               
            </div>
                       
        </div>
        <br/>
        <%@include file="/Footer.jsp" %>
    </body>
    

</html>
