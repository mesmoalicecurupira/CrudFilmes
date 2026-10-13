package com.template.service;

import com.template.model.dto.FilmesDTO;

import java.util.List;

public interface IFilmesService {
    boolean salvarFilme(String nome, String categoria, String classificacao, String atores);
    boolean atualizarFilme(int id, String nome, String categoria, String classificacao, String atores);
    void excluirFilme(int id);
    List<FilmesDTO> listarFilmes();
}
