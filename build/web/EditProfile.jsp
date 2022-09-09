<%-- 
    Document   : EditProfile.jsp
    Created on : Sep 4, 2022, 1:29:45 PM
    Author     : ACT
--%>



<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.hakim.entities.User" %>

<%
    //authetication
    User auth_user=(User) session.getAttribute("user");
    if(auth_user==null){
        response.sendRedirect("login.jsp");
    }

%>


<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp">
        <jsp:param name="title" value="Edit Profile" />
    </jsp:include>
    <body>
        <%@include file="Navbar.jsp" %>

        <div class="container">
            <div class="row">
                <div class="col-md-4 mx-auto mt-5">
                    <div class="card ">
                        <div class="card-header">
                            <h5 class="card-text">Edit Profile<span class="fa fa-user-circle"></span> </h5>
                        </div>
                        <div class="card-body">
                            <form action="EditProfileServlet" method="post" enctype="multipart/form-data">
                                <label>Name : </label><br/>
                                <input type="text" name="name" placeholder="John Doe" class="form-control"/>
                                <label>Email Address : </label><br/>
                                <input type="email" name="email" placeholder="example@mail.com" class="form-control"/>
                                
                                <label>Bio : </label><br/>
                                <textarea name="bio" class="form-control"></textarea>
                                
                                <label>Choose File : </label>
                                <input type="file" name="profile_pic"/><br/><br/>
                                                                
                                <input type="submit" value="Save" class="btn btn-primary"/>
                                                               
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
