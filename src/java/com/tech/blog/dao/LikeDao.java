package com.tech.blog.dao;

import java.sql.*;

public class LikeDao {

    Connection con;
    // Constructor to initialize the connection
    public LikeDao(Connection con) {
        this.con = con;
    }
    // Method to insert a like for a post
    public boolean insertLike(int pid, int uid) {
        boolean f = false;
        try {
            // SQL query to insert a like into the 'liked' table
            String q = "insert into liked(pid,uid)values(?,?)";
            PreparedStatement p = this.con.prepareStatement(q);
            //values set...
            p.setInt(1, pid);
            p.setInt(2, uid);
            p.executeUpdate();
            // Set flag to true indicating successful insertion
            f = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }
    // Method to count the number of likes on a post
    public int countLikeOnPost(int pid) {
        int count = 0;
        // SQL query to count likes on a post
        String q = "select count(*) from liked where pid=?";
        try {
            PreparedStatement p = this.con.prepareStatement(q);
            p.setInt(1, pid);
            ResultSet set = p.executeQuery();
            if (set.next()) {
                count = set.getInt("count(*)");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return count;
    }
    // Method to check if a post is liked by a user
    public boolean isLikedByUser(int pid, int uid) {
        boolean f = false;
        try {
            PreparedStatement p = this.con.prepareStatement("select * from liked where pid=? and uid=?");
            p.setInt(1, pid);
            p.setInt(2, uid);
            ResultSet set = p.executeQuery();
            if (set.next()) {
                // If result set is not empty, set flag to true
                f = true;
            }

        } catch (Exception e) {
        }
        return f;
    }
    // Method to delete a like given post id and user id
    public boolean deleteLike(int pid, int uid) {
        boolean f = false;
        try {
            PreparedStatement p = this.con.prepareStatement("delete from liked where pid=? and uid=? ");
            p.setInt(1, pid);
            p.setInt(2, uid);
            p.executeUpdate();
            f = true;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return f;
    }
}
