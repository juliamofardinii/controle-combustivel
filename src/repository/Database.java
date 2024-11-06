package repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/controlecombustivel";
    private static final String USER = "root";
    private static final String PASSWORD = "root";

    public static Connection connect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Carrega o driver explicitamente
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException e) {
            System.out.println("Driver MySQL não encontrado. Verifique se o driver está no classpath.");
        } catch (SQLException e) {
            System.out.println("Erro ao conectar: " + e.getMessage());
        }
        return null;
    }
}