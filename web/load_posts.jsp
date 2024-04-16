<%@page import="com.tech.blog.entities.User"%>
<%@page import="com.tech.blog.dao.LikeDao"%>
<%@page import="com.tech.blog.entities.Post"%>
<%@page import="java.util.List"%>
<%@page import="com.tech.blog.helper.ConnectionProvider"%>
<%@page import="com.tech.blog.dao.PostDao"%>

<div class="row">

    <%
        
        User uuu=(User)session.getAttribute("currentUser"); //<!-- Retrieve the current user from the session -->
        
        Thread.sleep(1000); //<!-- Pause the execution for 1000 milliseconds -->
        PostDao d = new PostDao(ConnectionProvider.getConnection()); // Create a PostDao object with a database connection
        
        int cid = Integer.parseInt(request.getParameter("cid")); //Parse the category ID from the request
        List<Post> posts = null;
        if (cid == 0) {
            posts = d.getAllPosts(); //Retrieve all posts from the database
        } else {
            posts = d.getPostByCatId(cid); //Retrieve posts by category ID
        }
        
        if (posts.size() == 0) {
            out.println("<h3 class='display-3 text-center'>No Posts in this category..</h3>");
            return;
        }
        
        for (Post p : posts) { // Loop through each post
    %>

    <div class="col-md-6 mt-2">
        <div class="card">
            <img class="card-img-top" src="blog_pics/<%= p.getpPic()%>" alt="Card image cap">
            <div class="card-body">
                <b><%= p.getpTitle()%></b>  <!-- Display post title -->
                <p><%= p.getpContent()%></p>  <!-- Display post content -->

            </div>
            <div class="card-footer primary-background text-center">
                <% 
                    LikeDao ld = new LikeDao(ConnectionProvider.getConnection());  //<!-- Create LikeDao object -->
                %>

                <a href="#!" onclick="doLike(<%= p.getPid()%>,<%= uuu.getId()%>)" class="btn btn-outline-light btn-sm"> <i class="fa fa-thumbs-o-up"></i> <span class="like-counter"><%= ld.countLikeOnPost(p.getPid())%></span>  </a>

                <a href="show_blog_page.jsp?post_id=<%= p.getPid()%>" class="btn btn-outline-light btn-sm">Read More...</a>
                <a href="#!" class="btn btn-outline-light btn-sm"> <i class="fa fa-commenting-o"></i> <span>20</span>  </a>
            </div>

        </div>


    </div>


    <%
        }
        

    %>

</div>