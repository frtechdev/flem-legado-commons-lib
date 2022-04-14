package br.org.flem.fwe.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Utilitários para formatação/utilização de valores (inteiros e floats).
 *
 * @author Grupo Framework - Conponentes
 * @version 1.0, 19/01/2005
 * @since 1.0
 */
public class Valores {

    /**
     * Simbolos especificos do Dolar Americano
     */
    private static final DecimalFormatSymbols SIMBOLOS_DOLAR = new DecimalFormatSymbols(Locale.US);
    /**
     * Mascara de dinheiro para Dolar Americano
     */
    public static final DecimalFormat DOLAR = new DecimalFormat("###,###,##0.00", SIMBOLOS_DOLAR);
    /**
     * Simbolos especificos do Euro
     */
    private static final DecimalFormatSymbols SIMBOLOS_EURO = new DecimalFormatSymbols(Locale.GERMANY);
    /**
     * Mascara de dinheiro para Euro
     */
    public static final DecimalFormat EURO = new DecimalFormat("###,###,##0.00", SIMBOLOS_EURO);
    /**
     * Smbolos especificos do Real Brasileiro
     */
    private static final DecimalFormatSymbols SIMBOLOS_REAL = new DecimalFormatSymbols(new Locale("pt", "BR"));
    /**
     * Mascara de dinheiro para Real Brasileiro
     */
    public static final DecimalFormat REAL = new DecimalFormat("###,###,##0.00", SIMBOLOS_REAL);

    /**
     * Retorna uma String com máscara para um float.
     * <b> Ex: <b/>
     * Um float com valor 100132 é transformada para 100.132,00
     * @param float valor
     * @return String valor formatado
     */
    public static String formataValor(float valor) {
        String mascara = "#,##0.00";
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        dfs.setDecimalSeparator(',');
        dfs.setGroupingSeparator('.');

        DecimalFormat df = new DecimalFormat(mascara, dfs);
        return df.format(valor);
    }

    /**
     * Retorna o valor com máscara para uma String sem máscara.
     * <b> Ex: <b/>
     * Uma String com valor 1001.32 é transformada para 1.001,32
     * @param String valor
     * @return String valor formatado
     */
    public static String formataValor(String valor) {
        if (valor == null) {
            return null;
        }
        float fValor = 0;
        //retorna uma string vazia se não for número
        try {
            fValor = Float.parseFloat(valor);
        } catch (Exception ex) {
            return "";
        }
        return formataValor(fValor);
    }

     /**
     * Retorna o valor com m�scara para um Double
     * <b> Ex: <b/>
     * Uma String com valor 1.001,32 � transformada em um Double 1001.32
     * @param String valor formatado
     * @return Double valor
     */
    public static Double desformataValor(String valor) {
        if (valor.equals(null)) {
            return null;
        }
        Double dValor = 0d;
        //retorna uma string vazia se n�o for n�mero
        try {
            dValor = Double.parseDouble(valor.replaceAll("\\.", "").replace(",", "."));
        } catch (Exception ex) {
            return null;
        }
        return dValor;
    }

     /**
     * Retorna o valor com m�scara para um BigDecimal
     * <b> Ex: <b/>
     * Uma String com valor 1.001,32 � transformada em um Double 1001.32
     * @param String valor formatado
     * @return BigDecimal dvalor
     */

    public static BigDecimal desformataValorBigDecimal(String valor) {
        if (valor.equals(null)) {
            return null;
        }
        BigDecimal dValor = BigDecimal.ZERO;
        //retorna uma string vazia se n�o for n�mero
        try {
            dValor = new BigDecimal(valor.replaceAll("\\.", "").replace(",", "."));
                    //Double.parseDouble(valor.replaceAll("\\.", "").replace(",", "."));
        } catch (Exception ex) {
            return null;
        }
        return dValor;
    }

    /**
     * Verifica se uma String é um Inteiro.
     * @param String com o valor
     * @return boolean true se for Inteiro
     */
    public static boolean isInteger(String str) {
        try {
            if (str.length() > 9) {
                for (int i = 0; i < str.length(); ++i) {
                    Integer.parseInt(str.substring(i, i + 1));
                }
            } else {
                Integer.parseInt(str);
            }
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    /**
     * Formata um Long para o formato alfanumérico do Natural, <br/>
     * iniciando o Long com o valor '0' até completar tamanho.
     * @param paramNumeric Long a ser completado com zeros
     * @param tamanho tamanho do número
     * @return String formatada
     * @throws Exception
     */
    public static String formataTamanho(Long paramNumeric, int tamanho) throws Exception {

        String alfa = paramNumeric.toString();

        if (alfa.length() > tamanho) {
            throw new Exception("Valor com tamanho inválido para conversão.");
        }

        StringBuffer retorno = new StringBuffer(tamanho);
        for (int i = 0; i < tamanho - alfa.length(); i++) {
            retorno.append("0");
        }
        retorno.append(alfa);

        return retorno.toString();
    }

    /**
     * Retorna um número com formatação Monetária.
     * @param number Double com o valor
     * @return String com formatação monetária
     */
    public static String formataMoeda(double number) {
        NumberFormat formatter = new DecimalFormat("###,###,##0.00");
        return formatter.format(number);
    }

    /**
     * Retorna um número com formatação Monetária.
     * @param number BigDecimal com o valor
     * @return String com formatação monetária
     */
    public static String formataMoeda(BigDecimal number) {
        NumberFormat formatter = new DecimalFormat("###,###,##0.00");
        return formatter.format(number);
    }

    public static Double formataValor(String valor, DecimalFormat formatoValor) throws ParseException {
        return formatoValor.parse(valor).doubleValue();
    }

    public static String formataMoeda(double d, DecimalFormat formatoValor) throws ParseException {
        return formatoValor.format(d);
    }

    public static Double cambio(Double valor,Double taxa){
        return valor / taxa;
    }

}
