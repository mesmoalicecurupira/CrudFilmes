package com.template.validator;
import java.util.ArrayList;
import java.util.List;
import static com.template.util.DialogUtil.*;

public class FilmesValidator {

    public static boolean validarFilme(String nome, String categoria, String classificacao, String atores) {

        List<Validador<String>> validadores = new ArrayList<>();
        validadores.add(new CampoObrigatorioValidador("Nome", nome));
        validadores.add(new CampoObrigatorioValidador("Categoria", categoria));
        validadores.add(new CampoObrigatorioValidador("Classificacao", classificacao));
        validadores.add(new CampoObrigatorioValidador("Atores", atores));

        for (Validador<String> validador : validadores) {
            if (!validador.validar(validador.getValor())) {
                showWarning(validador.getMensagemErro());
                return false;
            }
        }
        return true;
    }
}