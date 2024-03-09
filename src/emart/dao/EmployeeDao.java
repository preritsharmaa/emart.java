/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emart.dao;

import emart.dbutil.DBConnection;
import emart.pojo.EmployeePojo;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Priyanshu
 */
public class EmployeeDao {
    
        public static boolean addEmployee(EmployeePojo e)throws SQLException{
            Connection conn=DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement("insert into employees values(?,?,?,?)");
            ps.setString(1, e.getEmpId());
            ps.setString(2, e.getEmpName());
            ps.setString(3, e.getJob());
            ps.setDouble(4, e.getSalary());
            return ps.executeUpdate()==1;
        }
        
        public static String getNextEmpId()throws SQLException{
            Connection conn=DBConnection.getConnection();
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery("select max(empid) from employees");
            rs.next();            
            String empId=rs.getString(1);
            int empNo=Integer.parseInt(empId.substring(1));
            return "E"+(++empNo);
        }
        
        public static List<EmployeePojo> getAllEmployees()throws SQLException{
            Connection conn=DBConnection.getConnection();
            Statement st=conn.createStatement();
            ResultSet rs=st.executeQuery("select * from employees order by empid");
            ArrayList <EmployeePojo> empLst=new ArrayList<>();
            while(rs.next()){
                EmployeePojo e=new EmployeePojo(rs.getString(1),rs.getString(2),rs.getString(3),rs.getDouble(4));
                empLst.add(e);
            }            
            return empLst;
        }
        
        public static List<String> getAllEmpId()throws SQLException{
            Connection conn=DBConnection.getConnection();
            Statement st=conn.createStatement();
            ArrayList<String> empId=new ArrayList<>();
            ResultSet rs=st.executeQuery("select empid from employees order by empid");
            while(rs.next()){
                empId.add(rs.getString(1));
            }
           
            return empId;
        }
        
        public static EmployeePojo getEmpDetails(String empId)throws SQLException{
            Connection conn=DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement("Select * from employees where empid=?");
            ps.setString(1, empId);
            ResultSet rs=ps.executeQuery();
            rs.next();
            return new EmployeePojo(rs.getString(1), rs.getString(2), rs.getString(3), rs.getDouble(4));
        }
        
        public static boolean updateEmp(EmployeePojo e)throws SQLException{
            Connection conn=DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement("update employees set empname=?, job=?, salary=? where empid=?");
            ps.setString(1, e.getEmpName());
            ps.setString(2, e.getJob());
            ps.setDouble(3, e.getSalary());
            ps.setString(4, e.getEmpId());
            int result=ps.executeUpdate();
            if(result==0)
                return false;
            
            if(!UserDao.isUserPresent(e.getEmpId()))
                    return true;
            ps=conn.prepareStatement("update users set username=?, usertype=? where empid=?");
            ps.setString(1, e.getEmpName());
            ps.setString(2, e.getJob());
            ps.setString(3, e.getEmpId());
            result=ps.executeUpdate();
            return result==1;               
        }
        
        public static boolean deleteEmp(String empId)throws SQLException{
            Connection conn=DBConnection.getConnection();
            PreparedStatement ps=conn.prepareStatement("delete from employees where empid=?");
            ps.setString(1, empId);
            return ps.executeUpdate()==1;
        }
   
}



