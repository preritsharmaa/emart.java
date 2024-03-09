/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emart.dao;

import emart.dbutil.DBConnection;
import emart.pojo.OrderPojo;
import emart.pojo.ProductPojo;
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
public class OrderDao {
    
    public static String getNextOrderId()throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select max(order_id) from orders");
        rs.next();
        String oId=rs.getString(1);
        if(oId==null)
            return "O-101";
        int idNo=Integer.parseInt(oId.substring(2));
        return "O-"+(++idNo);
    }
    
    public static boolean addOrderId(List<ProductPojo> p, String userId, String oId)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Insert into orders values(?,?,?,?)");
        int count=0;
        for(ProductPojo product:p){
            ps.setString(1, oId);
            ps.setString(2, product.getProductId());
            ps.setInt(3, product.getQuantity());
            ps.setString(4, userId);
            count+=ps.executeUpdate();            
        }
        return count==p.size();
    }
    
    public static List<String> getAllOrderIdForReceptionist(String userId)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Select distinct order_id from orders where userId = ? order by order_id");
        ps.setString(1, userId);        
        ResultSet rs=ps.executeQuery();
        ArrayList<String> al=new ArrayList<>();
        while(rs.next()){
            al.add(rs.getString(1));
        }
        return al;
    }
    
    public static List<String> getOrderIdForManager()throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select distinct order_id from orders order by order_id");
        ArrayList<String> al=new ArrayList<> ();
        while(rs.next()){
            al.add(rs.getString(1));
        }
        return al;
    }
    
    public static List<OrderPojo> getOrderDetails(String oId)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Select p_id, quantity, userid from orders where order_id=?");
        ps.setString(1, oId);
        ResultSet rs=ps.executeQuery();
        ArrayList<OrderPojo> al=new ArrayList<>();
        OrderPojo o;
        while(rs.next()){
            o=new OrderPojo();
            o.setProductId(rs.getString(1));
            o.setQuantity(rs.getInt(2));
            o.setUserId(rs.getString(3));
            al.add(o);
        }       
        return al;
    }
    
}
