package com.template.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String CONEXAO = "jdbc:postgresql://localhost:5432/filmes";
    private static final String USUARIO = "postgres";
    private static final String SENHA = "postgres";

    public Connection conectaBD() {
        try {
            return DriverManager.getConnection(CONEXAO, USUARIO, SENHA);
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados: " + e.getMessage(), e);
        }
    }
}
