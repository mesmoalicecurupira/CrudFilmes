package com.template.validator;
import java.util.regex.Pattern;

import static com.template.util.DialogUtil.*;
public class FilmesValidator {
    public static boolean validarFilme(String nome, String categoria, String classificacao, String atores){

        if(nome.isEmpty() || categoria.isEmpty() || classificacao.isEmpty()||atores.isEmpty()) {
            showWarning("Preencha todos os campos antes de prosseguir");
            return false;
        }
        if(!validarNome(nome))
        {
            showWarning("digite um nome de filme valido");
            return false;
        }
        return true;
    }
    public static boolean validarNome(String nome){
        return Pattern.matches("^[\\p{L}0-9\\s:\\-'!?]+$", nome.trim());
    }
}
