package com.template.service;

import com.template.model.dao.FilmesDAO;
import com.template.model.dao.IFilmesDAO;
import com.template.model.dto.FilmesDTO;
import com.template.validator.FilmesValidator;
import com.template.validator.IFilmesValidator;

import java.util.List;

public class FilmesService implements IFilmesService {

    private final IFilmesDAO filmesDAO;
    private final IFilmesValidator filmesValidator;

    public FilmesService() {
        this(new FilmesDAO(), new FilmesValidator());
    }

    public FilmesService(IFilmesDAO filmesDAO, IFilmesValidator filmesValidator) {
        this.filmesDAO = filmesDAO;
        this.filmesValidator = filmesValidator;
    }

    @Override
    public boolean salvarFilme(String nome, String categoria, String classificacao, String atores) {
        filmesValidator.validarFilme(nome, categoria, classificacao, atores);

        FilmesDTO dto = new FilmesDTO();
        dto.setNome(nome);
        dto.setCategoria(categoria);
        dto.setClassificacao(classificacao);
        dto.setAtores(atores);

        filmesDAO.cadastrarFilmes(dto);
        return true;
    }

    @Override
    public boolean atualizarFilme(int id, String nome, String categoria, String classificacao, String atores) {
        filmesValidator.validarFilme(nome, categoria, classificacao, atores);

        FilmesDTO dto = new FilmesDTO();
        dto.setId(id);
        dto.setNome(nome);
        dto.setCategoria(categoria);
        dto.setClassificacao(classificacao);
        dto.setAtores(atores);

        filmesDAO.atualizarFilmes(dto);
        return true;
    }

    @Override
    public void excluirFilme(int id) {
        filmesDAO.deletarFilmes(id);
    }

    @Override
    public List<FilmesDTO> listarFilmes() {
        return filmesDAO.lerFilmes();
    }
}