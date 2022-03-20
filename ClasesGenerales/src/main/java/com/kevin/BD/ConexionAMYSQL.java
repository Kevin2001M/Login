/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.kevin.BD;
import java.sql.*;
import javax.swing.JOptionPane;
/**
 *
 * @author Kevin
 */
public class ConexionAMYSQL {
      private static Connection ConnectionBD = null;
    public Connection getConecction (){
    try {
        
        String url = "jdbc:mysql://localhost:3306/clase";
        String user = "alvarenga";
        String password ="Kevin123";
        
        ConnectionBD=DriverManager.getConnection(url,user,password);
        //System.out.println("Conexion exitosa");
        
    }catch (Exception e){
        JOptionPane.showMessageDialog(null, "Error"+e.toString());
        
    } 
        return ConnectionBD;
    }
}
