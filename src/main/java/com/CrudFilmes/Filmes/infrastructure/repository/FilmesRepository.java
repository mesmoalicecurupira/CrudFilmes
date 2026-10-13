package com.CrudFilmes.Filmes.infrastructure.repository;

import com.CrudFilmes.Filmes.infrastructure.entitys.Filmes;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FilmesRepository extends JpaRepository<Filmes, Integer> {

    Optional<Filmes> findByNome(String nome);

    @Transactional
    void deleteByNome(String nome);
}
