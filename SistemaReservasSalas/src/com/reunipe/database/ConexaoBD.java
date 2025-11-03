package com.reunipe.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static final String URL = "jdbc:mysql://localhost:3306/db_ReuniPe";
    private static final String USER = "root";
    private static final String PASSWORD = "root"; // ALTERE PARA SUA SENHA REAL!
    
    static {
        try {
            // Carregar o driver explicitamente
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver MySQL carregado com sucesso!");
        } catch (ClassNotFoundException e) {
            System.out.println("ERRO: Driver MySQL nao encontrado no classpath");
            e.printStackTrace();
        }
    }
    
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    
    public static void testarConexao() {
        try (Connection conn = getConnection()) {
            System.out.println("CONEXAO COM BANCO ESTABELECIDA!");
        } catch (SQLException e) {
            System.out.println("ERRO NA CONEXAO: " + e.getMessage());
            e.printStackTrace();
        }
    }
}