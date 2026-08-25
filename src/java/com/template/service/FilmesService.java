package com.template.service;

import com.template.model.dao.FilmesDAO;
import com.template.model.dto.FilmesDTO;
import com.template.validator.FilmesValidator;

import java.util.ArrayList;

public class FilmesService {

    private final FilmesDAO filmesDAO = new FilmesDAO();

    public boolean salvarFilme(String nome, String categoria, String classificacao, String atores) {
        if (!FilmesValidator.validarFilme(nome, categoria, classificacao, atores)) {
            return false;
        }

        FilmesDTO dto = new FilmesDTO();
        dto.setNome(nome);
        dto.setCategoria(categoria);
        dto.setClassificacao(classificacao);
        dto.setAtores(atores);

        filmesDAO.cadastrarFilmes(dto);
        return true;
    }

    public boolean atualizarFilme(int id, String nome, String categoria, String classificacao, String atores) {
        if (!FilmesValidator.validarFilme(nome, categoria, classificacao, atores)) {
            return false;
        }

        FilmesDTO dto = new FilmesDTO();
        dto.setId(id);
        dto.setNome(nome);
        dto.setCategoria(categoria);
        dto.setClassificacao(classificacao);
        dto.setAtores(atores);

        filmesDAO.atualizarFilmes(dto);
        return true;
    }

    public void excluirFilme(int id) {
        FilmesDTO dto = new FilmesDTO();
        dto.setId(id);
        filmesDAO.deletarFilmes(dto);
    }

    public ArrayList<FilmesDTO> listarFilmes() {
        return filmesDAO.lerFilmes();
    }
}