/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.flem.flemcommons.util;

import br.org.flem.commons.util.MoedaUtil;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.text.ParseException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author DBBarreto
 */
public class MoedaUtilTest{

    public MoedaUtilTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void removePrecisaoDouble_NumeroSimples_Ok() {
        Double numero = new Double(20.18);
        Double expResult = new Double(20.18);
        Double result = MoedaUtil.removePrecisao(numero, 2);
        assertEquals(expResult, result);
    }

    @Test
    public void removePrecisaoDouble_NumeroComPrecisao_Erro() {
        Double numero = new Double(20/3);
        Double expResult = new Double(6.67);
        Double result = MoedaUtil.removePrecisao(numero, 2);
        assertFalse(expResult == result);

        numero = new Double(43.19 * 2.16);
        expResult = new Double(93.2904);
        result = MoedaUtil.removePrecisao(numero, 4);
        assertFalse(expResult == result);

        numero = new Double(47.52 + 63.21);
        expResult = new Double(110.73);
        result = MoedaUtil.removePrecisao(numero, 2);
        assertFalse(expResult == result);
    }

    @Test
    public void removePrecisaoDouble_NumeroComPrecisao_Ok() {
        Double expResult = new Double(6.67);
        Double result = MoedaUtil.removePrecisao(new Double(20d/3d), 2);
        assertEquals(expResult, result);

        expResult = new Double(93.2904);
        result = MoedaUtil.removePrecisao(new Double(43.19 * 2.16), 4);
        assertEquals(expResult, result);

        expResult = new Double(110.73);
        result = MoedaUtil.removePrecisao(new Double(47.52 + 63.21), 2);
        assertEquals(expResult, result);
    }

    @Test
    public void removePrecisaoBigDecimal_NumeroSimples_Ok() {
        BigDecimal numero = new BigDecimal(20.18);
        BigDecimal expResult = new BigDecimal(20.18).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal result = MoedaUtil.removePrecisao(numero, 2);
        assertEquals(expResult, result);
    }

    @Test
    public void removePrecisaoBigDecimal_NumeroComPrecisao_Erro() {
        BigDecimal numero = new BigDecimal(20).divide(new BigDecimal(3), MathContext.DECIMAL32);
        BigDecimal expResult = new BigDecimal(6.67);
        BigDecimal result = MoedaUtil.removePrecisao(numero, 2);
        assertFalse(expResult == result);

        numero = new BigDecimal(43.19).multiply(new BigDecimal(2.16), MathContext.DECIMAL32);
        expResult = new BigDecimal(93.2904);
        result = MoedaUtil.removePrecisao(numero, 4);
        assertFalse(expResult == result);

        numero = new BigDecimal(47.52).add(new BigDecimal(63.21), MathContext.DECIMAL32);
        expResult = new BigDecimal(110.73);
        result = MoedaUtil.removePrecisao(numero, 2);
        assertFalse(expResult == result);
    }

    @Test
    public void removePrecisaoBigDecimal_NumeroComPrecisao_Ok() throws ParseException {
        BigDecimal expResult = new BigDecimal(6.67).setScale(2, BigDecimal.ROUND_HALF_EVEN);
        BigDecimal result = MoedaUtil.removePrecisao(new BigDecimal(20).divide(new BigDecimal(3), MathContext.DECIMAL32), 2);
        assertEquals(expResult, result);
    }

}
