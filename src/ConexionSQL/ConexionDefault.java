/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author JorgeLPR
 */
public class ConexionDefault {

    private final String DB = "sql10492533";
    private final String URL = "jdbc:mysql://sql10.freesqldatabase.com:3306/" + DB + "?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private final String USER = "sql10492533";
    private final String PASS = "AaVRdhMYmc";
    //establece la conexion con la base de datos
    public Connection openConnection() {

        Connection connect = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = (Connection) DriverManager.getConnection(URL, USER, PASS);

            if (connect != null) {
                System.out.println("Conexión exitosa");
            } else {
                System.out.println("Conexión Fallida");
            }

        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println("error " + ex.getMessage());
        }
        return connect;

    }
    //cierra conexion con la base de datos
    public void closeConnection(Connection connect) {
        try {
            connect.close();
            System.out.println("Conexión Cerrada Exitosamente");
        } catch (SQLException ex) {
            Logger.getLogger(ConexionDefault.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
