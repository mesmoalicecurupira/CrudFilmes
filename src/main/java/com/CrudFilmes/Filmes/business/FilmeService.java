package com.CrudFilmes.Filmes.business;

import com.CrudFilmes.Filmes.infrastructure.entitys.Filmes;
import com.CrudFilmes.Filmes.infrastructure.repository.FilmesRepository;
import org.springframework.stereotype.Service;

@Service
public class FilmeService {
    private final FilmesRepository repository;

    public FilmeService(FilmesRepository repository) {
        this.repository = repository;
    }

    public void salvarFilmes(Filmes filmes) {
        repository.saveAndFlush(filmes);
    }

    public Filmes buscarFilmesPorNome(String nome){
        return repository.findByNome(nome).orElseThrow(
                () -> new RuntimeException("Filme não encontrado")
        );
    }
    public void deletarFilmesPorNome(String nome){
        repository.deleteByNome(nome);
    }
    public void atualizarFilmePorId(Integer id, Filmes filmes)
    {
        Filmes filmesEntity = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Filme não encontrado"));
        Filmes filmesAtualizado = Filmes.builder()
                .id(id)
                .nome(filmes.getNome() != null ? filmes.getNome() : filmesEntity.getNome())
                .ator(filmes.getAtor() != null ? filmes.getAtor() : filmesEntity.getAtor())
                .classificacao(filmes.getClassificacao() != null ? filmes.getClassificacao() : filmesEntity.getClassificacao())
                .categoria(filmes.getCategoria() != null ? filmes.getCategoria() : filmesEntity.getCategoria())
                .build();
        repository.saveAndFlush(filmesAtualizado);
    }
}
