package br.org.flem.fwe.validator;

import java.util.ArrayList;

/**
 *
 * @author mccosta
 */
public class SenhaValidator {

    public static String regexFLEM = "";
    private static String regexMin = "[a-z]";
    private static String regexMai = "[A-Z]";
    private static String regexNum = "[1-9]";

    public SenhaValidator() {
    }

    /* public static boolean isSenhaFraca(String senha, String regex){
    boolean retorno = false;
    
    return retorno;
    }*/
    public static boolean isSenhaValida(String senha) {
        boolean minuscula = false;
        boolean maiuscula = false;
        boolean numero = false;
        if (senha.length() < 12) {
            return false;
        }

        for (int i = 0; i < senha.length(); i++) {
            if (senha.substring(i, i + 1).matches(regexMai)) {
                maiuscula = true;
                break;
            }
        }

        for (int i = 0; i < senha.length(); i++) {
            if (senha.substring(i, i + 1).matches(regexMin)) {
                minuscula = true;
                break;
            }
        }

        for (int i = 0; i < senha.length(); i++) {
            if (senha.substring(i, i + 1).matches(regexNum)) {
                numero = true;
                break;
            }
        }
        return maiuscula && minuscula && numero;
    }
}
