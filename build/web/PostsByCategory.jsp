
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.hakim.entities.Post" %>
<%@page import="com.hakim.entities.User" %>
<%@page import="com.hakim.helper.ConnectionProvider" %>
<%@page import="com.hakim.dao.ServerCall" %>
<%@page import="java.util.List" %>

<%
    //authentication
    User auth_user=(User) session.getAttribute("user");
    if(auth_user==null){
        response.sendRedirect("login.jsp");
    }
    
    //posts
    int category=Integer.parseInt(request.getParameter("category"));
    List<Post> posts=ServerCall.getPostByCategory(ConnectionProvider.getConnection(),category);
%>


<!DOCTYPE html>
<html>
     <jsp:include page="head.jsp">
        <jsp:param name="title" value="Posts By Category."/>
    </jsp:include>
    <body>
        <%@include file="/Navbar.jsp" %>
        
        <div class="container">
            <h1 class="display-5 mt-3" style="border-bottom: 2px solid #333;display: inline-block;"><%= posts.size()%> posts found :</h1>
            <div class="row">
                <%-- Single Post--%>
                <%for(Post p:posts){%>
                <div class="col-md-4 mb-2">
                    <div class="card">
                        
                        <div class="card-body">
                            <h5 class="card-title"><%= p.getTitle()%></h5>
                            <small class="card-subtitle mb-2 text-muted"><%= p.getPost_date()%></small>
                            <p class="card-text"><%= p.getContent()%></p>
                            <a href="SinglePost.jsp?id=<%= p.getPid()%>" class="btn btn-outline-primary">Read More</a>
                        </div>
                    </div>
                </div>
                <%}%>
                <%-- End --%>
               
            </div>
                       
        </div>
    </body>
</html>
