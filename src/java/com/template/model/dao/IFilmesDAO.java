package com.template.model.dao;

import com.template.model.dto.FilmesDTO;

import java.util.List;

public interface IFilmesDAO {
    void cadastrarFilmes(FilmesDTO filmes);
    void deletarFilmes(int id);
    void atualizarFilmes(FilmesDTO filmes);
    List<FilmesDTO> lerFilmes();
}
