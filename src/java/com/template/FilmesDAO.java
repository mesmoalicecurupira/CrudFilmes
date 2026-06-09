package com.template;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FilmesDAO {

    String sql = "INSERT INTO filmes (nome_filme, categoria_filme, classifica_indicativa, atores_filme) VALUES (?, ?, ?, ?)";

    private static final Logger logger = Logger.getLogger(FilmesDAO.class.getName());

    public void cadastrarFilmes(FilmesDTO filmes){

            try(Connection conexao = new Conexao().conectaBD(); PreparedStatement smt = conexao.prepareStatement(sql))
            {
                smt.setString(1,filmes.getNome());//substitui as ? pelos dados reais do dto
                smt.setString(2,filmes.getCategoria());
                smt.setString(3,filmes.getClassificacao());
                smt.setString(4,filmes.getAtores());

                smt.execute();
                smt.close();

            }
            catch(SQLException e)
            {
                logger.log(Level.SEVERE,"erro", e);
            }
        }
        public void deletarFilmes(FilmesDTO filmes)
        {
            String sqlDelete = "DELETE from filmes where id_filmes = ?";

            try(Connection conexao = new Conexao().conectaBD(); PreparedStatement smt = conexao.prepareStatement(sqlDelete))
            {
                smt.setInt(1, filmes.getId());//define qual id sera excluido

                int removidos = smt.executeUpdate();
                if (removidos == 0)
                {
                    logger.log(Level.SEVERE,"nada pra ser removidos");
                }

            }
            catch(SQLException e )
            {
                logger.log(Level.SEVERE,"erro", e);

            }
        }
        public void atualizarFilmes(FilmesDTO filmes)
        {
            String sqlAtualizar = ("UPDATE filmes SET nome_filme = ?, categoria_filme = ?,classifica_indicativa = ?,atores_filme = ? WHERE id_filmes = ?");

            try(Connection conexao = new Conexao().conectaBD(); PreparedStatement smt = conexao.prepareStatement(sqlAtualizar))
            {
                smt.setString(1, filmes.getNome());
                smt.setString(2, filmes.getCategoria());
                smt.setString(3, filmes.getClassificacao());
                smt.setString(4, filmes.getAtores());
                smt.setInt(5, filmes.getId());

                int linhas = smt.executeUpdate();

                if (linhas > 0)
                {
                    logger.log(Level.SEVERE,"Sem linha");
                }

            }
            catch (SQLException e)
            {
                logger.log(Level.SEVERE,"erro", e);

            }
        }

        public ArrayList<FilmesDTO> lerFilmes()
        {
            String sqlLer = "Select * from filmes";
            ArrayList<FilmesDTO> listaDeFilmes = new ArrayList<>();

            try(Connection conexao = new Conexao().conectaBD(); PreparedStatement smt = conexao.prepareStatement(sqlLer))
            {
                ResultSet rs = smt.executeQuery();

                while(rs.next())
                {
                    FilmesDTO filmes = new FilmesDTO();

                    // Ajustado para os nomes reais das colunas da sua tabela do banco
                    filmes.setId(rs.getInt("id_filmes"));
                    filmes.setNome(rs.getString("nome_filme"));
                    filmes.setCategoria(rs.getString("categoria_filme"));
                    filmes.setClassificacao(rs.getString("classifica_indicativa"));
                    filmes.setAtores(rs.getString("atores_filme"));

                    listaDeFilmes.add(filmes);
                }
            } catch(SQLException e) {
                logger.log(Level.SEVERE,"erro ao ler filmes", e);
            }

            return listaDeFilmes;
        }
    }

