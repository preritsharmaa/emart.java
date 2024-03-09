/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emart.dao;

import emart.dbutil.DBConnection;
import emart.pojo.UserPojo;
import emart.pojo.UserProfile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Priyanshu
 */
public class UserDao {
    
    public static boolean validateUser(UserPojo user)throws SQLException{
        
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps= conn.prepareStatement("select * from users where userid = ? and password= ? and usertype= ?");
                
        ps.setString(1, user.getUserid());
        ps.setString(2, user.getPassword());
        ps.setString(3, user.getUsertype());
        
        ResultSet rs= ps.executeQuery();
       
        if (rs.next()){
            UserProfile.setUsername(rs.getString("username"));
            return true;
        }
        return false;
    }

    public static boolean isUserPresent(String empId)throws SQLException {
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps= conn.prepareStatement("select * from users where empid = ?");
        ps.setString(1, empId);
        ResultSet rs=ps.executeQuery();
        return rs.next();
    }
    
    
    
}
