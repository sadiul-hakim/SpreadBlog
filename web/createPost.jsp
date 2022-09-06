<%-- 
    Document   : createPost
    Created on : Sep 5, 2022, 12:18:16 PM
    Author     : ACT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List" %>
<%@page import="com.hakim.entities.Category" %>
<%@page import="com.hakim.helper.ConnectionProvider" %>
<%@page import="com.hakim.dao.ServerCall" %>

<%
    List<Category> categories=ServerCall.getCategories(ConnectionProvider.getConnection());
%>

<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp">
        <jsp:param name="title" value="Create Post" />
    </jsp:include>
    <body>
        <%@include file="Navbar.jsp" %>

        <div class="container">
            <div class="row">
                <div class="col-md-4 mx-auto mt-5">
                    <div class="card ">
                        <div class="card-header">
                            <h5 class="card-text">Create Post </h5>
                        </div>
                        <div class="card-body">
                            <form action="CreatePostServlet" method="post" enctype="multipart/form-data">
                                <label>Title : </label><br/>
                                <input type="text" name="title" placeholder="Anything" class="form-control"/>
                                
                                <label>Category : </label><br/>
                                <select name="caterogy" class="form-control" id="category">
                                    <option>-Select Category-</option>
                                <%for(Category c:categories){%>
                                <option value="<%= c.getCid()%>"><%= c.getName()%></option>
                                <%}%>
                                </select>
                                
                                <label>Content : </label><br/>
                                <textarea name="content" class="form-control"></textarea>
                                
                                <label id="code-label">Code : </label><br/>
                                <textarea name="code" class="form-control" id="code"></textarea>

                                <label>Choose File : </label>
                                <input type="file" name="pic" class="form-control"/><br/>

                                <input type="submit" value="Post" class="btn btn-primary"/>

                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
    <script>
        let category=document.getElementById("category");
        let codeLabel=document.getElementById("code-label");
        let code=document.getElementById("code");

        category.addEventListener("change",(e)=>{
            console.log(category.value);
            if(category.value!=="1"){
                codeLabel.style.display="none";
                code.style.display="none";
            }
        })
        
    </script>
</html>
