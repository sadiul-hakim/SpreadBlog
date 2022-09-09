<%-- 
    Document   : 404
    Created on : Sep 3, 2022, 7:39:14 PM
    Author     : ACT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp">
        <jsp:param name="title" value="Error Page"/>
    </jsp:include>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-6 text-center offset-2">
                    <img src="profile_pics/undraw_Page_not_found_re_e9o6.png" alt="Error" width="600px" height="500px"/>
                    <p>404 Page Not Found.</p>
                    <a href="index.jsp">Home Page</a>
                </div>
            </div>
        </div>
    </body>
</html>
