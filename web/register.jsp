<%-- 
    Document   : register
    Created on : Sep 3, 2022, 4:33:34 PM
    Author     : ACT
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
    <jsp:include page="head.jsp">
        <jsp:param name="title" value="Register Page"/>
    </jsp:include>
    <body>
        <%@include file="Navbar.jsp" %>

        <div class="container">
            <div class="row">
                <div class="col-md-4 mx-auto mt-5">
                    <div class="card ">
                        <div class="card-header">
                            <h5 class="card-text">Register Form <span class="fa fa-user-circle"></span> </h5>
                        </div>
                        <div class="card-body">
                            <form id="form" action="RegisterServlet" method="post">
                                <label>Name : </label><br/>
                                <input type="text" name="name" placeholder="John Doe" class="form-control"/>
                                <label>Email Address : </label><br/>
                                <input type="email" name="email" placeholder="example@mail.com" class="form-control"/>
                                <label>Password : </label><br/>
                                <input type="password" name="password" placeholder="********" class="form-control"/>
                                <input type="checkbox" name="check"/>
                                <label> Accept all terms</label><br/><br/>
                                <input type="submit" value="Register"/>
                                <br/><br/>
                                
                                <a href="login.jsp">Already have an account? Login</a>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
    
</html>
