package com.template.service;

import com.template.model.dao.FilmesDAO;
import com.template.model.dto.FilmesDTO;
import com.template.validator.FilmesValidator;

import java.util.ArrayList;

// Classe que tira o dao do controller e coloca aqui (bd).

public class FilmesService {

    private final FilmesDAO filmesDAO = new FilmesDAO();

    public boolean salvarFilme(String nome, String classificacao, String categoria, String atores) {
        // Chama o seu Validator para garantir que os dados estão corretos
        if (!FilmesValidator.validarFilme(nome, categoria, classificacao, atores)) {
            return false;
        }


        FilmesDTO dto = new FilmesDTO();
        dto.setNome(nome);
        dto.setClassificacao(classificacao);
        dto.setCategoria(categoria);
        dto.setAtores(atores);

        filmesDAO.cadastrarFilmes(dto);
        return true;
    }

    public boolean atualizarFilme(int id, String nome, String classificacao, String categoria, String atores) {
        if (!FilmesValidator.validarFilme(nome, categoria, classificacao, atores)) {
            return false;
        }
        FilmesDTO dto = new FilmesDTO();
        dto.setId(id);
        dto.setNome(nome);
        dto.setClassificacao(classificacao);
        dto.setCategoria(categoria);
        dto.setAtores(atores);

        filmesDAO.atualizarFilmes(dto);
        return true;
    }

    public void excluirFilme(int id) {
        FilmesDTO dto = new FilmesDTO();
        dto.setId(id);

        filmesDAO.deletarFilmes(dto);
    }
    //cadastrados
    public ArrayList<FilmesDTO> listarFilmes() {
        return filmesDAO.lerFilmes();
    }
}