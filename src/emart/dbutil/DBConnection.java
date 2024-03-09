/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package emart.dbutil;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Priyanshu
 */
public class DBConnection {
    
    private static Connection conn;
    
    static{
      try{
          Class.forName("oracle.jdbc.OracleDriver");
          conn=DriverManager.getConnection("jdbc:oracle:thin:@//Priyanshu:1521/XE", "grocery", "grocery");
          System.out.println("Connection opened successfully!");
      }  
      catch(ClassNotFoundException e){
          JOptionPane.showMessageDialog(null, "Error in loading the driver", "Driver Error!", JOptionPane.ERROR_MESSAGE);
          e.printStackTrace();
          System.exit(1);
      }
      catch(SQLException e){
          JOptionPane.showMessageDialog(null, "Error in opening the class", "DB Error!", JOptionPane.ERROR_MESSAGE);
          e.printStackTrace();
          System.exit(1);
      }
    }
    
  public static Connection getConnection(){
      return conn;
  }

  public static void closeConnection(){
      try{
          conn.close();
          System.out.println("Connection closed succesfully");
      }
      catch(SQLException e){
          JOptionPane.showMessageDialog(null, "Cannot close connection", "DB Error!", JOptionPane.ERROR_MESSAGE);
      }
  }  
}
