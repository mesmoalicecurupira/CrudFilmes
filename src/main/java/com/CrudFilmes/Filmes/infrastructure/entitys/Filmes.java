package com.CrudFilmes.Filmes.infrastructure.entitys;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table (name = "Filmes")
@Entity


public class Filmes {

    @Id
    @GeneratedValue( strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "ator")
    private String ator;

    @Column(name = "classificacao")
    private String classificacao;

    @Column(name = "categoria")
    private String categoria;


}
