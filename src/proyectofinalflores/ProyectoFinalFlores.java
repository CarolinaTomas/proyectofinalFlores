/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyectofinalflores;

/**
 *
 * @author wcaro
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ProyectoFinalFlores {
    private static final String URL = "jdbc:mysql://localhost:3306/negocioFlores";
    private static final String USER = "root";
    private static final String PASSWORD = "carolina93";

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}


