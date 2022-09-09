<%-- 
    Document   : SingleUser
    Created on : Sep 5, 2022, 8:34:31 PM
    Author     : ACT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.hakim.dao.UserDao" %>
<%@page import="com.hakim.entities.User" %>
<%@page import="com.hakim.helper.ConnectionProvider" %>

<%
    User auth_user=(User) session.getAttribute("user");
    if(auth_user==null){
        response.sendRedirect("login.jsp");
    }
    
    int id=Integer.parseInt(request.getParameter("id"));
    UserDao user_dao=new UserDao(ConnectionProvider.getConnection());
    User user=user_dao.getUserById(id);
%>

<!DOCTYPE html>
<html>
   <jsp:include page="head.jsp">
        <jsp:param name="title" value="<%= user.getName()%>"/>
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
            </div>
        </div>
    </body>
</html>
