/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vahiculos.pspro.pruebaconexionservidor;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dam
 */
public class Conexion {

    static Connection conn = null;
    static Statement st = null;
    static ResultSet rs = null;
    static String bd = "clase";
    static String login = "root";
    static String password = "";
    static String url = "jdbc:mysql://localhost/" + bd;

    public static Connection enlace(Connection conn) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
        } catch (SQLException ex) {
            System.out.println("Excepicon en la conexión");
        } catch (ClassNotFoundException ex) {
            System.out.println("No se encuentra la clase");
        } catch (Exception ex){
            System.err.println(ex.getMessage());
        }
        return conn;
    }

    static ResultSet consulta() {
        conn = enlace(conn);
        try {
            st = (Statement) conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("excepcion");
        }
        try {
            rs = st.executeQuery("select * from alumno");
        } catch (SQLException ex) {
            System.out.println("error en la query");
        }

        return rs;
    }

    public static void imprimirConsulta(ResultSet rs) {
        try {
            while (rs.next()) {
                System.out.print(rs.getInt(1) + "    ");
                System.out.print(rs.getString("Nombre") + "     ");
                System.out.print(rs.getString("Apellido1") + "     ");
                System.out.println(rs.getString("Apellido2"));
            }
        } catch (SQLException ex) {
            System.out.println("Error en el resultset");
            ex.printStackTrace();
        }
    }

    public static void cerrarSesion() {
        try {
            rs.close();
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {

        conn = enlace(conn);
        rs = consulta();
        imprimirConsulta(rs);
        cerrarSesion();

    }
}
