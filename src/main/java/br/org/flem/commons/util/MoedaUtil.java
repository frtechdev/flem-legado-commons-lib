/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.flem.commons.util;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.ParseException;

/**
 *
 * @author DBBarreto
 */
public class MoedaUtil {

    /**
     * Método que remove a precisão de um número. um número que deveria ser 10.20
     * pode ser armazenado em uma variável como 10.19989989, por exemplo, e isso
     * pode impactar em operações realizadas com esse número.
     * Ex.: MoedaUtil.removeprecisao(20.8877665, 2) = 20.89
     * @param numero Número preciso
     * @param precisao Quantidade de casas decimais
     * @return Numero sem a precisão.
     */
    public static Double removePrecisao(Double numero, int precisao) {
        BigDecimal retorno = new BigDecimal(numero);
        retorno = retorno.setScale(precisao, BigDecimal.ROUND_HALF_EVEN);
        return retorno.doubleValue();
    }

    /**
     * Método que remove a precisão de um número. um número que deveria ser 10.20
     * pode ser armazenado em uma variável como 10.19989989, por exemplo, e isso
     * pode impactar em operações realizadas com esse número.
     * Ex.: MoedaUtil.removeprecisao(20.8877665, 2) = 20.89
     * @param numero Número preciso
     * @param precisao Quantidade de casas decimais
     * @return Numero sem a precisão.
     */
    public static BigDecimal removePrecisao(BigDecimal numero, int precisao) {
        BigDecimal retorno = numero.setScale(precisao, BigDecimal.ROUND_HALF_EVEN);
        return retorno;
    }

}
