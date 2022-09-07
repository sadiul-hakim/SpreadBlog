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
<%@page import="com.hakim.dao.LikesDao" %>
<%@page import="com.hakim.dao.ServerCall" %>
<%@page import="java.util.List" %>

<%
    //Getting post details
    String id=request.getParameter("id");
    PostDao dao=new PostDao(ConnectionProvider.getConnection());
    Post post=dao.getAPost(Integer.parseInt(id));
    List<Post> postByCategory=ServerCall.getPostByCategory(ConnectionProvider.getConnection(),post.getCid());
    
    //Getting user details
    UserDao user_dao=new UserDao(ConnectionProvider.getConnection());
    int user_id=post.getId();
    User user=user_dao.getUserById(user_id);
    
    //Getting all likes
    LikesDao likes_dao=new LikesDao(ConnectionProvider.getConnection());
    int count=likes_dao.likeCount(post.getPid());
    boolean liked=likes_dao.liked(user.getId(),post.getPid());
%>

<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp">
        <jsp:param name="title" value="<%= post.getTitle()%>"/>
    </jsp:include>
    <body>
        <%@include file="/Navbar.jsp" %>

        <div class="container mt-4">
            <div class="row">
                <div class="col-md-7">
                    <div class="card">
                        <div class="card-header d-flex">
                            <img src="profile_pics/<%= user.getProfile_pic()%>" width="60px" height="60px" style="border-radius: 50%;border:2px solid wheat;margin-right: 10px;"/>
                            <div class="d-flex flex-column">
                                <a href="SingleUser.jsp?id=<%= user.getId()%>" style="color:#333;font-size: 20px;" class="m-0"><%= user.getName()%></a>
                                <p><%= user.getEmail()%></p>
                            </div>
                        </div>
                        <div class="card-body">
                            <img src="profile_pics/<%= post.getPic()%>" width="100%" height="400px"/>
                            <h5 class="display-5"><%= post.getTitle()%></h5>
                            <p class="text-muted card-text"><%= post.getPost_date()%></p>
                            <br/>

                            <p><%= post.getContent()%></p>

                            <br/>

                            <%if(!post.getCode().equals("")){%>

                            <h3>Codes : </h3>
                            <pre><code class="language-css"><%= post.getCode()%></code></pre>
                        
                            <%}%>
                </div>
                    <div class="card-footer">
                        <a href="LikingServlet?liked=<%= liked%>&pid=<%= post.getPid()%>&uid=<%= user.getId()%>" class="btn"><%= count%> <i class="fa fa-thumbs-o-up"></i> <%if(liked){%>Liked<%}else{%>Like<%}%></a>
                        <a href="" class="btn"><i class=" 	fa fa-commenting-o"></i> Comment</a>
                    </div>
                </div>
                </div>
                
                    <div class="col-md-3">
                        <div class="card">
                            <div class="card-header">
                                <h4 class="card-title">Related Posts : </h4>
                            </div>
                            <div class="card-body">
                                <ul class="list-group">
                                <%for(Post p:postByCategory){%>
                                        
                                <%if(p.getPid()!=post.getPid()){%>
                                            <li class="list-group-item">
                                                <a href="SinglePost.jsp?id=<%= p.getPid()%>"><%= p.getTitle()%></a>
                                            </li>
                                <%}%>
                                        
                                <%}%>
                                </ul>
                            </div>
                        </div>
                    </div>
               
            </div>
                 
        </div>
    </body>
</html>
