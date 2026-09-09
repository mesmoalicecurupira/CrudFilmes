package com.template.validator;

import java.util.regex.Pattern;

public class ClassificacaoValidador implements Validador<String> {

    private static final String CLASSIFICACAO_REGEX = "(?i)^(livre|\\d+\\s*anos)$";
    private final Pattern pattern = Pattern.compile(CLASSIFICACAO_REGEX);
    private final String classificacao;

    public ClassificacaoValidador(String classificacao) {
        this.classificacao = classificacao;
    }

    @Override
    public boolean validar(String valor) {
        String texto = (valor != null) ? valor : this.classificacao;
        return texto != null && pattern.matcher(texto).matches();
    }

    @Override
    public String getMensagemErro() {
        return "Digite uma classificação válida (ex: 12 anos, livre).";
    }

    @Override
    public String getValor() {
        return classificacao;
    }
}