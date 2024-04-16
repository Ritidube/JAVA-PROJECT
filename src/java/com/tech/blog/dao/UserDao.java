/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.tech.blog.dao;

import com.tech.blog.entities.User;
import com.tech.blog.helper.ConnectionProvider;
import java.sql.*;

public class UserDao {

    private Connection con; // Declaring private Connection variable

    public UserDao(Connection con) {  // Constructor with Connection parameter
        this.con = con;                 // Assigning the Connection parameter to con
    }

    //method to insert user to data base:
    public boolean saveUser(User user) {
        boolean f = false;
        try {
            //user -->database
            // SQL query to insert user data into the database
            String query = "INSERT INTO user(name,email,password,gender,about) VALUES (?,?,?,?,?)";
            PreparedStatement pstmt = this.con.prepareStatement(query);  //Preparing the SQL statement
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setString(4, user.getGender());
            pstmt.setString(5, user.getAbout());

            pstmt.executeUpdate(); // Executing the SQL statemen
            f = true;
        } catch (Exception e) {
            e.printStackTrace(); // Printing stack trace for debugging
           // return f;
        }
        return f; // Returning the boolean value indicating insertion success or failure

    }

    //get user by useremail and userpassword:
    public User getUserByEmailAndPassword(String email, String password) {
        User user = null;

        try {
            // SQL query to retrieve user data based on email and password
            String query = "select * from user where email =? and password=?";
            PreparedStatement pstmt = con.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);

            ResultSet set = pstmt.executeQuery();   // Executing the SQL query and getting ResultSet

            if (set.next()) { // Checking if ResultSet has data
                user = new User();  // Instantiating User object

//             data from db
                String name = set.getString("name");
//             set to user object
                user.setName(name);

                user.setId(set.getInt("id"));
                user.setEmail(set.getString("email"));
                user.setPassword(set.getString("password"));
                user.setGender(set.getString("gender"));
                user.setAbout(set.getString("about"));
                user.setDateTime(set.getTimestamp("rdate"));
                user.setProfile(set.getString("profile"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
    // Method to update user data in the database
    public boolean updateUser(User user) {

        boolean f = false;
        try {
            // SQL query to update user data in the database
            String query = "update user set name=? , email=? , password=? , gender=? ,about=? , profile=? where  id =?";
            PreparedStatement p = con.prepareStatement(query);
            p.setString(1, user.getName());
            p.setString(2, user.getEmail());
            p.setString(3, user.getPassword());
            p.setString(4, user.getGender());
            p.setString(5, user.getAbout());
            p.setString(6, user.getProfile());
            p.setInt(7, user.getId());

            p.executeUpdate();
            f = true;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return f; // Returning the boolean value indicating update success or failure
    }
    // Method to retrieve user by userId from the database
    public User getUserByUserId(int userId) {
        User user = null;
        try {
            // SQL query to retrieve user data based on userId
            String q = "select * from user where id=?";
            PreparedStatement ps = this.con.prepareStatement(q);
            ps.setInt(1, userId);
            ResultSet set = ps.executeQuery(); // Executing the SQL query and getting ResultSet
            if (set.next()) {
                user = new User();

//             data from db
                String name = set.getString("name");
//             set to user object
                user.setName(name);

                user.setId(set.getInt("id"));
                user.setEmail(set.getString("email"));
                user.setPassword(set.getString("password"));
                user.setGender(set.getString("gender"));
                user.setAbout(set.getString("about"));
                user.setDateTime(set.getTimestamp("rdate"));
                user.setProfile(set.getString("profile"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }
}
