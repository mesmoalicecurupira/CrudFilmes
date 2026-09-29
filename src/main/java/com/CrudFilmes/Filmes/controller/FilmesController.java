package com.CrudFilmes.Filmes.controller;

import com.CrudFilmes.Filmes.business.FilmeService;
import com.CrudFilmes.Filmes.infrastructure.entitys.Filmes;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/Filmes")
@RequiredArgsConstructor
public class FilmesController {

    private final FilmeService filmeService;

    @PostMapping
    public ResponseEntity<Void> salvarFilmes(@RequestBody Filmes filmes){
        filmeService.salvarFilmes(filmes);
        return ResponseEntity.ok().build();
    }
    @GetMapping
    public ResponseEntity<Filmes> buscarFilmesPorNome(@RequestParam String nome){
        return  ResponseEntity.ok(filmeService.buscarFilmesPorNome(nome));

    }
    @DeleteMapping
    public ResponseEntity<Void> deletarFilmesPorNome(@RequestParam String nome){
       filmeService.deletarFilmesPorNome(nome);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<Void> atualizarFilmePorId(@RequestParam Integer id, @RequestBody Filmes filmes) {
        filmeService.atualizarFilmePorId(id, filmes);
        return ResponseEntity.ok().build();
    }

}
