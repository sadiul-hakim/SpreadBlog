<%-- 
    Document   : profile.jsp
    Created on : Sep 3, 2022, 8:05:54 PM
    Author     : ACT
--%>


<%@page import="com.hakim.entities.User" %>
<%@page import="com.hakim.helper.ConnectionProvider" %>
<%@page import="com.hakim.dao.ServerCall" %>
<%@page import="com.hakim.entities.Post" %>
<%@page import="java.util.List" %>
<%@page import="java.util.stream.Collectors" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    User user=(User) session.getAttribute("user");
    if(user==null){
        request.getRequestDispatcher("/login.jsp").forward(request,response);
//        response.sendRedirect("/login.jsp");
    }
    
    List<Post> total_posts=ServerCall.getAllPosts(ConnectionProvider.getConnection());
    List<Post> posts=total_posts.stream().filter(p->p.getId()==user.getId()).collect(Collectors.toList());
%>

<!DOCTYPE html>
<html>
    <jsp:include page="/head.jsp">
        <jsp:param name="title" value="Profile"/>
    </jsp:include>
    <body>
        <%@include file="/Navbar.jsp" %>

        <div class="card w-25 mx-auto mt-5">
            <div class="card-body text-center">
                <img src="profile_pics/<%= user.getProfile_pic()%>" style="width:10rem;height:9rem;border-radius: 50%;border:2px solid wheat;"/>
                <h4 class="mt-2"><%= user.getName()%></h4>
            </div>
            <div class="card-footer">
                <p class="mb-0">Contact At : <%= user.getEmail()%></p>
                <p>Bio : <%= user.getBio() %> </p>
                <p>Started On : <%= user.getDateTime() %> </p>
                <p>Total Posts : <%= posts.size() %> </p>
                <%System.out.println(user.getDateTime());%>
                <a href="EditProfile.jsp" class="btn btn-primary">Edit Profile</a>
            </div>
        </div>

        <br/>

        <div class="container">
            <h3 class="display-4" style="border-bottom:2px solid #999; display:inline-block;">Your Posts : </h3>

            <div class="row">
                <%-- Single Post--%>
                <%for(Post p:posts){%>
                <%if(p.getId()==user.getId()){%>
                <div class="col-md-4 mb-2">
                    <div class="card">

                        <div class="card-body">
                            <h5 class="card-title"><%= p.getTitle()%></h5>
                            <small class="card-subtitle mb-2 text-muted"><%= p.getPost_date()%></small>
                            <p class="card-text"><%= p.getContent()%></p>
                            <a href="SinglePost.jsp?id=<%= p.getPid()%>" class="btn btn-outline-primary">Read More</a>
                            <a href="EditPost.jsp?id=<%= p.getPid()%>" class="btn btn-outline-primary">Edit Post</a>
                            <a href="DeletePostServlet?id=<%= p.getPid()%>" class="btn btn-danger">Delete</a>
                        </div>
                    </div>
                </div>
                <%}%>
                <%}%>
                <%-- End --%>

            </div>

        </div>



    </body>
</html>
