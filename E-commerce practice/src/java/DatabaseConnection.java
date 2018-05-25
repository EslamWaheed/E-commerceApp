/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hazem
 */
public class DatabaseConnection {
    
    public static Connection getConnection(){
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection =  DriverManager.getConnection("jdbc:mysql://localhost:3306/chatting", "hazem", "12621992");
        } catch (ClassNotFoundException ex) {
            System.out.println("cannot connect to the database");
        } catch (SQLException ex) {
            System.out.println("sql command error");
        }
        return connection;

    }
}
