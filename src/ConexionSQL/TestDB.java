/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionSQL;

/**
 *
 * @author ABRAHAM
 */
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class TestDB {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        ConexionDefault conectar = new ConexionDefault();
        //ConexionPool conectar = new ConexionPool();
        //conectar.dataSource.getConnection();
        conectar.openConnection();
        
    }
    
}