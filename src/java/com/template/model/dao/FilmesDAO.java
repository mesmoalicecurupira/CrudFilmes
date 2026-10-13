package com.template.model.dao;

import com.template.model.Conexao;
import com.template.model.dto.FilmesDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FilmesDAO implements IFilmesDAO {

    private static final Logger LOGGER = Logger.getLogger(FilmesDAO.class.getName());
    private final Conexao conexao;

    public FilmesDAO() {
        this(new Conexao());
    }

    public FilmesDAO(Conexao conexao) {
        this.conexao = conexao;
    }

    @Override
    public void cadastrarFilmes(FilmesDTO filmes) {
        String sql = "INSERT INTO filmes (nome_filme, categoria_filme, classifica_indicativa, atores_filme) VALUES (?, ?, ?, ?)";

        try (Connection conn = conexao.conectaBD(); PreparedStatement smt = conn.prepareStatement(sql)) {
            smt.setString(1, filmes.getNome());
            smt.setString(2, filmes.getCategoria());
            smt.setString(3, filmes.getClassificacao());
            smt.setString(4, filmes.getAtores());
            smt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao cadastrar filme", e);
            throw new RuntimeException("Erro ao cadastrar filme no banco de dados", e);
        }
    }

    @Override
    public void deletarFilmes(int id) {
        String sqlDelete = "DELETE FROM filmes WHERE id_filmes = ?";

        try (Connection conn = conexao.conectaBD(); PreparedStatement smt = conn.prepareStatement(sqlDelete)) {
            smt.setInt(1, id);
            smt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao deletar filme", e);
            throw new RuntimeException("Erro ao deletar filme no banco de dados", e);
        }
    }

    public void deletarFilmes(FilmesDTO filmes) {
        if (filmes != null) {
            deletarFilmes(filmes.getId());
        }
    }

    @Override
    public void atualizarFilmes(FilmesDTO filmes) {
        String sqlAtualizar = "UPDATE filmes SET nome_filme = ?, categoria_filme = ?, classifica_indicativa = ?, atores_filme = ? WHERE id_filmes = ?";

        try (Connection conn = conexao.conectaBD(); PreparedStatement smt = conn.prepareStatement(sqlAtualizar)) {
            smt.setString(1, filmes.getNome());
            smt.setString(2, filmes.getCategoria());
            smt.setString(3, filmes.getClassificacao());
            smt.setString(4, filmes.getAtores());
            smt.setInt(5, filmes.getId());
            smt.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao atualizar filme", e);
            throw new RuntimeException("Erro ao atualizar filme no banco de dados", e);
        }
    }

    @Override
    public List<FilmesDTO> lerFilmes() {
        String sqlLer = "SELECT * FROM filmes";
        List<FilmesDTO> listaDeFilmes = new ArrayList<>();

        try (Connection conn = conexao.conectaBD(); PreparedStatement smt = conn.prepareStatement(sqlLer)) {
            ResultSet rs = smt.executeQuery();
            while (rs.next()) {
                FilmesDTO filmes = new FilmesDTO();
                filmes.setId(rs.getInt("id_filmes"));
                filmes.setNome(rs.getString("nome_filme"));
                filmes.setCategoria(rs.getString("categoria_filme"));
                filmes.setClassificacao(rs.getString("classifica_indicativa"));
                filmes.setAtores(rs.getString("atores_filme"));
                listaDeFilmes.add(filmes);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Erro ao ler filmes", e);
            throw new RuntimeException("Erro ao ler filmes do banco de dados", e);
        }

        return listaDeFilmes;
    }
}
