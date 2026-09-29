package com.CrudFilmes.Filmes.business;

import com.CrudFilmes.Filmes.infrastructure.entitys.Filmes;
import com.CrudFilmes.Filmes.infrastructure.repository.FilmesRepository;
import jakarta.persistence.Id;
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

    public Filmes buscarFilmesPorNome(String Nome){
        return repository.findByNome(Nome).orElseThrow(
                () -> new RuntimeException("Nome nao encontrado")
        );
    }
    public void deletarFilmesPorNome (String Nome){
        repository.deleteByNome(Nome);
    }
    public void atualizarFilmePorId(Integer id, Filmes filmes)
    {
        Filmes filmesEntity = repository.findById(id).orElseThrow(() ->
                new RuntimeException("Usuario nao encontrado"));
        Filmes filmesAtualizado = Filmes.builder()
                .nome(filmes.getNome() != null ? filmes.getNome() :
                        filmesEntity.getNome())
                .id(filmes.getId())
                .build();
        repository.saveAndFlush(filmesAtualizado);
    }
}
