package com.template;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao
    {
        //conexao com o banco
        static String conexao = "jdbc:postgresql://localhost:5432/filmes";
        static String usuario = "postgres";
        static String senha = "postgres";

        public Connection conectaBD()//conexão com o banco de dados
        {
            try
            {
                return DriverManager.getConnection(conexao, usuario, senha);
            }
            catch (SQLException e)
            {
                throw new RuntimeException(e.getMessage()); //se falhar, lança um erro explicando o motivo
            }
        }
    }
