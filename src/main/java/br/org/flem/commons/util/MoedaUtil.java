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
     * M�todo que remove a precis�o de um n�mero. um n�mero que deveria ser 10.20
     * pode ser armazenado em uma vari�vel como 10.19989989, por exemplo, e isso
     * pode impactar em opera��es realizadas com esse n�mero.
     * Ex.: MoedaUtil.removeprecisao(20.8877665, 2) = 20.89
     * @param numero N�mero preciso
     * @param precisao Quantidade de casas decimais
     * @return Numero sem a precis�o.
     */
    public static Double removePrecisao(Double numero, int precisao) {
        BigDecimal retorno = new BigDecimal(numero);
        retorno = retorno.setScale(precisao, BigDecimal.ROUND_HALF_EVEN);
        return retorno.doubleValue();
    }

    /**
     * M�todo que remove a precis�o de um n�mero. um n�mero que deveria ser 10.20
     * pode ser armazenado em uma vari�vel como 10.19989989, por exemplo, e isso
     * pode impactar em opera��es realizadas com esse n�mero.
     * Ex.: MoedaUtil.removeprecisao(20.8877665, 2) = 20.89
     * @param numero N�mero preciso
     * @param precisao Quantidade de casas decimais
     * @return Numero sem a precis�o.
     */
    public static BigDecimal removePrecisao(BigDecimal numero, int precisao) {
        BigDecimal retorno = numero.setScale(precisao, BigDecimal.ROUND_HALF_EVEN);
        return retorno;
    }

}
