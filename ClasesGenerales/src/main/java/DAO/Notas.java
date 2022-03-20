/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import Entidades.Estudiante;
import Entidades.Nota;
import ViewModel.NotaVM;
import com.kevin.BD.ConexionAMYSQL;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Kevin
 */
public class Notas {
    
      ConexionAMYSQL con = new ConexionAMYSQL();
      Connection conexion = con.getConecction();
       
      
      
      //Sin ViewModel 
      
//    public ArrayList <Nota> listadoNotas (){
//         ArrayList<Nota> listado = null;
//       
//        try {
//           listado  = new ArrayList<Nota>();
//
//           
//           CallableStatement cb = conexion.prepareCall("{call SP_S_MOSTRARNOTAS()}");
//           ResultSet resultado = cb.executeQuery();
//           
//           
//           while (resultado.next()){
//               //Llamar a el objeto de Entidad
//               Nota es = new Nota ();
//               es.setNota(resultado.getString("nota"));
//               es.setNombreMateria(resultado.getString("nombreMateria"));
//               es.setNombre(resultado.getString("Nombre"));
//               
//               listado.add(es);
//               
//           }
//   
//        }catch (Exception e){
//            System.out.println(e.toString());
//        }
//        
//         return listado;     
//    }
    
    
      
      ///
      
      
      //Usando ViewModel
      
      public ArrayList <NotaVM> listadoNotas (){
         ArrayList<NotaVM> listado = null;
       
        try {
           listado  = new ArrayList<NotaVM>();

           
           CallableStatement cb = conexion.prepareCall("{call SP_S_MOSTRARNOTAS()}");
           ResultSet resultado = cb.executeQuery();
           
           
           while (resultado.next()){
               //Llamar a el objeto de Entidad
               NotaVM es = new NotaVM ();
               es.setNota(resultado.getString("nota"));
               es.setNombremateria(resultado.getString("nombreMateria"));
               es.setNombre(resultado.getString("Nombre"));
               listado.add(es);
               
           }
   
        }catch (Exception e){
            System.out.println(e.toString());
        }
        
         return listado;     
    }
      
      
      
    
     public void AddNotas(Nota es){
          try {
              CallableStatement cb = conexion.prepareCall("{call SP_I_NOTA(?,?,?)}");
              cb.setString("PNota", es.getNota());
              cb.setString("PIdMateria", es.getNombreMateria());
              cb.setString("PIdEstudiante", es.getNombre());
              cb.execute();
              
              JOptionPane.showMessageDialog(null, "Nota Agregada","Mensaje sistems",1);
              
          } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error"+ex.toString(),"Mensaje sistems",1);
          }
        
        
    }
}
 /*   public void AddNotas(Nota es) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    
    } */
    
    

