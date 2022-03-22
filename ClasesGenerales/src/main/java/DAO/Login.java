/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Entidades.Estudiante;
import Entidades.Nota;
import ViewModel.LoginVM;
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
public class Login {

    ConexionAMYSQL con = new ConexionAMYSQL();
    Connection conexion = con.getConecction();
    
    public boolean c = false;

    public ArrayList<LoginVM> ListUser(String User, String Password) {
        ArrayList<LoginVM> listado = null;

        try {
            listado = new ArrayList<LoginVM>();

            CallableStatement cb = conexion.prepareCall("{call SP_I_LOGIN()}");
            ResultSet resultado = cb.executeQuery();

            while (resultado.next()) {
                //Llamar a el objeto de Entidad
                LoginVM es = new LoginVM();
                es.getUser(resultado.getString("PUser"));
                es.getPass(resultado.getString("PPassword"));
                listado.add(es);

            }

        } catch (Exception e) {
            System.out.println(e.toString());
        }

        return listado;
    }

    public void AddUser(Estudiante es) {
        try {
            //Estudiante est = new Estudiante();

            CallableStatement cb = conexion.prepareCall("{call SP_I_LOGIN(?,?)}");
            cb.setString("PUser", es.getUser());
            cb.setString("PPassword", es.getPassword());
            cb.execute();

            JOptionPane.showMessageDialog(null, "Usuario Agregado", "Mensaje sistems", 1);

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error" + ex.toString(), "Mensaje sistems", 1);
        }

    }

    
    
    
   public boolean LoguinEstudiante(String usuario, String Pass) {
        ArrayList<Estudiante> ListaUsuariosPass = new ArrayList<>();
        try {
            CallableStatement Statement = conexion.prepareCall("call SP_S_LOGUIESTUDIANTE(?,?)");
            Statement.setString("pusuario", usuario);
            Statement.setString("ppass", Pass);
            ResultSet resultadoDeConsulta = Statement.executeQuery();
            while (resultadoDeConsulta.next()) {
                Estudiante es = new Estudiante();
                es.setUser(resultadoDeConsulta.getString("Usuario"));
                es.setPassword(resultadoDeConsulta.getString("Contra"));
                ListaUsuariosPass.add(es);
            }
            String usuariodebasedatos = null;
            String passsdebasedados = null;
            for (var iterador : ListaUsuariosPass) {
                usuariodebasedatos = iterador.getUser();
                passsdebasedados = iterador.getPassword();
            }

            if (usuariodebasedatos.equals(usuario) && passsdebasedados.equals(Pass)) {
                return true;

            }

            conexion.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        return false;
    }

   
   //PRUEBA TUTO
   
   
    int confirmation = 0;
    
    
    public int Acces(Estudiante st){
        
        String sqlQuery = "{call clase.SP_S_LOGUIESTUDIANTE(?,?)}";
        
        try {
            
            CallableStatement cs = conexion.prepareCall(sqlQuery);
            cs.setString("pusuario", st.getUser());
            cs.setString("ppass", st.getPassword());
            ResultSet rs = cs.executeQuery();
            
            if (rs.next()) {
                
                confirmation = 1;
                
                return confirmation;
            }
            
        } catch (Exception ex) {
            
            JOptionPane.showMessageDialog(null, "Error" + ex.toString());
        }
        
        return confirmation;
    }
   
   
}
