
package com.tech.blog.servlets;

import com.tech.blog.dao.UserDao;
import com.tech.blog.entities.Message;
import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) { // Using try-with-resources to handle PrintWriter
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
//          login 
//            fetch username and password from request
            String userEmail = request.getParameter("email");
            String userPassword = request.getParameter("password");

            UserDao dao = new UserDao(ConnectionProvider.getConnection()); // Creating UserDao object using ConnectionProvider class

            User u = dao.getUserByEmailAndPassword(userEmail, userPassword); // Retrieving user from database based on email and password

            if (u == null) {
                //login.................
//                error///
//                out.println("Invalid Details..try again");
                Message msg = new Message("Invalid Details ! try with another", "error", "alert-danger"); //message blog entites (message.java)
                HttpSession s = request.getSession(); // Retrieving session object
                s.setAttribute("msg", msg); // Setting error message in session attribute

                response.sendRedirect("login_page.jsp"); // Redirecting to login page
            } else {
                //......
//                login success
                HttpSession s = request.getSession();
                s.setAttribute("currentUser", u); // Setting current user in session attribute
                response.sendRedirect("profile.jsp"); // Redirecting to profile page

            }

            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

//Overall, this servlet handles user login functionality by interacting with the UserDao class to authenticate users and manage sessions. If the login is successful, the user is redirected to the profile page; otherwise, an error message is displayed on the login page.
