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
    public boolean validar(String valorAtual) {
        return this.classificacao != null && pattern.matcher(this.classificacao).matches();
    }

    @Override
    public String getMensagemErro() {
        return "digite uma classificacao valida (ex: x anos.. livre)";
    }

    @Override
    public String getValor() {
        return classificacao;
    }
}