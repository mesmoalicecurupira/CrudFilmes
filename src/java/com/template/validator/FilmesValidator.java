package com.template.validator;

import java.util.ArrayList;
import java.util.List;

public class FilmesValidator implements IFilmesValidator {

    @Override
    public boolean validarFilme(String nome, String categoria, String classificacao, String atores) {
        List<Validador<String>> validadores = new ArrayList<>();

        validadores.add(new CampoObrigatorioValidador("Nome", nome));
        validadores.add(new CampoObrigatorioValidador("Categoria", categoria));
        validadores.add(new CampoObrigatorioValidador("Classificação", classificacao));
        validadores.add(new CampoObrigatorioValidador("Atores", atores));
        validadores.add(new ClassificacaoValidador(classificacao));

        for (Validador<String> validador : validadores) {
            if (!validador.validar(validador.getValor())) {
                throw new IllegalArgumentException(validador.getMensagemErro());
            }
        }

        return true;
    }
}