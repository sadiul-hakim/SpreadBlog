
<%@page import="com.hakim.entities.User" %>
<%@page import="java.util.List" %>
<%@page import="com.hakim.entities.Category" %>
<%@page import="com.hakim.helper.ConnectionProvider" %>
<%@page import="com.hakim.dao.ServerCall" %>


<%
    User u=(User) session.getAttribute("user");
    List<Category> navCategories=ServerCall.getCategories(ConnectionProvider.getConnection());
%>


<nav class="navbar navbar-expand-lg navbar-dark primary-backgroud">
    <div class="container-fluid">
        <a class="navbar-brand" href="index.jsp"><span class="fa fa-graduation-cap"></span> Spread</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="index.jsp"><span class="fa fa-home"></span> Home</a>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                        <span class="fa fa-cloud-download"></span> Categories
                    </a>
                    <ul class="dropdown-menu">
                        <%for(Category c:navCategories){%>
                        <li title="<%= c.getDescription()%>"><a class="dropdown-item" href="PostsByCategory.jsp?category=<%= c.getCid()%>"><%= c.getName()%></a></li>
                            <li><hr class="dropdown-divider"></li>
                        <%}%>
                    </ul>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#"><span class="fa fa-volume-control-phone"></span> Contact</a>
                </li>


            </ul>
                       
            <ul class="navbar-nav">
                
                <%
                    if(u==null){
                    
                %>


                <li class="nav-item">
                    <a class="nav-link" href="login.jsp"><span class=" fa fa-sign-in"></span> Login</a>
                </li>

                <%  }else{%>

                <li class="nav-item">
                    <a class="nav-link" href="createPost.jsp">Create Post</a>
                </li>
                 <li class="nav-item">
                    <a class="nav-link" href="profile.jsp"><span class="fa fa-user-circle"></span> <%= u.getName() %> </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="LogoutServlet"><span class=" fa fa-sign-in"></span> Logout</a>
                </li>
                <%  } %>

            </ul>

        </div>
    </div>
</nav>