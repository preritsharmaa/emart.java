/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emart.dao;

import emart.dbutil.DBConnection;
import emart.pojo.ReceptionistPojo;
import emart.pojo.UserPojo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Priyanshu
 */
public class ReceptionistDao {
    
    public static Map<String, String> getNonRegisteredReceptionist()throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select empid, empname from employees where job='Receptionist' and empid not in (select empid from users where usertype='Receptionist') order by empid");
        HashMap<String, String> receptionistList=new HashMap<>();
        while(rs.next()){
            String id=rs.getString(1);
            String name=rs.getString(2);
            receptionistList.put(id, name);
        }
        return receptionistList;
    }
    
    public static boolean addReceptionist(UserPojo u)throws SQLException {
       Connection conn=DBConnection.getConnection();
       PreparedStatement ps=conn.prepareStatement("insert into users values(?,?,?,?,?)");
       ps.setString(1, u.getUserid());
       ps.setString(2, u.getEmpid());
       ps.setString(3, u.getPassword());
       ps.setString(4, u.getUsertype());
       ps.setString(5, u.getUsername());
       return ps.executeUpdate()==1;
    }
    
    public static List<ReceptionistPojo> getAllReceptionist()throws SQLException{
        Connection conn= DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select users.empid,username,userid,salary from users,employees where usertype='Receptionist' and users.empid=employees.empid order by users.empid");
        ArrayList <ReceptionistPojo> al=new ArrayList<>();
        while(rs.next()){
            ReceptionistPojo r=new ReceptionistPojo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
            al.add(r);
        }
        return al;
    }

    public static Map<String, String> getRegisteredReceptionist()throws SQLException {
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select empid,username from users where usertype='Receptionist' order by empid");
        HashMap<String, String> receptionistLst=new HashMap<> ();
        while(rs.next()){
            String id=rs.getString(1);
            String name=rs.getString(2);
            receptionistLst.put(id, name);
        }
        return receptionistLst;
    }

    public static boolean updateReceptionist(String receptionistId, String password)throws SQLException {
        Connection conn= DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Update users set password=? where empid=?");
        
        ps.setString(1, password);
        ps.setString(2, receptionistId);
        return ps.executeUpdate()==1;
    }

    public static boolean removeReceptionist(String receptionistId)throws SQLException {
        Connection conn= DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Delete from users where empid=?");
        ps.setString(1, receptionistId);
        return ps.executeUpdate()==1;
    }
    
    public static List<String> getRegisteredReceptionistId()throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select empid from users where usertype='Receptionist' order by empid");
        List<String> empId=new ArrayList<>();
        while(rs.next()){
            empId.add(rs.getString(1));
        }
        return empId;
    }
}
