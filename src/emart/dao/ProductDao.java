/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emart.dao;

import emart.dbutil.DBConnection;
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
public class ProductDao {
    public static String getNextProductId()throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select max(p_id) from products");
        rs.next();
        if(rs.getString(1)==null){
            return "P101";
        }
        int pId=Integer.parseInt(rs.getString(1).substring(1));
        return "P"+(++pId);
    }
    
    public static boolean addProduct(ProductPojo p)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Insert into products values(?,?,?,?,?,?,?,'Y')");
        ps.setString(1, p.getProductId());
        ps.setString(2, p.getProductName());
        ps.setString(3, p.getProductCompany());
        ps.setDouble(4, p.getProductPrice());
        ps.setDouble(5, p.getOurPrice());
        ps.setInt(6, p.getTax());
        ps.setInt(7, p.getQuantity());        
        return ps.executeUpdate()==1;
    }
    
    public static List<ProductPojo> getAllProductDetails()throws SQLException{
        Connection conn=DBConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet rs=st.executeQuery("Select * from products where status='Y' order by p_id");
        List<ProductPojo> pDetails=new ArrayList<>();
        ProductPojo p;
        while(rs.next()){
            p=new ProductPojo(rs.getString(1),rs.getString(2), rs.getString(3),rs.getDouble(4),rs.getDouble(5), rs.getInt(6), rs.getInt(7));
            pDetails.add(p);
        }
        return pDetails;
    }
    
    public static boolean deleteProduct(String pId)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Update products set status='N' where p_id=?");
        ps.setString(1, pId);
        return ps.executeUpdate()==1;
    }
    
    public static boolean updateProduct(ProductPojo p)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Update products set p_name=?, p_companyname=?, p_price=?, our_price=?, p_tax=?, quantity=? where p_id=?");
        ps.setString(1, p.getProductName());
        ps.setString(2, p.getProductCompany());
        ps.setDouble(3, p.getProductPrice());
        ps.setDouble(4, p.getOurPrice());
        ps.setInt(5, p.getTax());
        ps.setInt(6, p.getQuantity());
        ps.setString(7, p.getProductId());
        return ps.executeUpdate()==1;
    }
    
    public static ProductPojo getProductDetails(String pId)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Select * from products where p_id=? and status='Y'");
        ps.setString(1, pId);
        ResultSet rs=ps.executeQuery();
        ProductPojo p = new ProductPojo();
        if(rs.next()){
            p.setProductId(rs.getString(1));
            p.setProductName(rs.getString(2));
            p.setProductCompany(rs.getString(3));
            p.setProductPrice(rs.getDouble(4));
            p.setOurPrice(rs.getDouble(5));
            p.setTax(rs.getInt(6));
            p.setQuantity(1);
        }
        return p;
    }
    
    public static boolean updateStocks(List<ProductPojo> productList)throws SQLException{
        Connection conn=DBConnection.getConnection();
        PreparedStatement ps=conn.prepareStatement("Update products set quantity=quantity-? where p_id=?");
        int x=0;
        for(ProductPojo p: productList){
            ps.setInt(1, p.getQuantity());
            ps.setString(2, p.getProductId());
            int rows = ps.executeUpdate();
            if(rows!=0)
                x++;            
        }
        return x==productList.size();
    }
    
}
