<%-- 
    Document   : login.jsp
    Created on : Sep 3, 2022, 2:28:20 PM
    Author     : Hakim
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.hakim.entities.User" %>

<%
    User user=(User) session.getAttribute("user");
    if(user!=null){
        response.sendRedirect("profile.jsp");
    }
%>


<!DOCTYPE html>
<html>
    <jsp:include page="/head.jsp">
        <jsp:param name="title" value="Login"/>
    </jsp:include>

    <body>
        
        <%@include file="/Navbar.jsp" %>
        
        <div class="container">
            <div class="row">
                <div class="col-md-4 mx-auto mt-5">
                    <div class="card ">
                        <div class="card-header">
                            <h5 class="card-text">Login Form <span class=" fa fa-sign-in"></span> </h5>
                        </div>
                        <div class="card-body">
                            <form action="LoginServlet" method="POST">
                                <label>Email Address : </label><br/>
                                <input type="email" name="email" placeholder="example@mail.com" class="form-control"/>
                                <label>Password : </label><br/>
                                <input type="password" name="password" placeholder="********" class="form-control"/><br/>
                                <input type="submit" value="Login"/><br/><br/>
                                
                                <a href="register.jsp">Don't have any account? Register</a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>
