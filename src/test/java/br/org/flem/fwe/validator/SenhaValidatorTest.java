/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.flem.fwe.validator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author ilfernandes
 */
public class SenhaValidatorTest {

    public SenhaValidatorTest() {
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

    /**
     * Test of isSenhaValida method, of class SenhaValidator.
     */
    @Test
    public void testSenhaMenorque12Caraceteres_FALSE() {
        System.out.println("isSenhaValida");
        String senha = "ABCDEF";
        boolean expResult = false;
        boolean result = SenhaValidator.isSenhaValida(senha);
        assertEquals(expResult, result);
    }

    @Test
    public void testSenha12CaraceteresMAISCULA_FALSE() {
        System.out.println("isSenhaValida");
        String senha = "ABCDEFABCDEF";
        boolean expResult = false;
        boolean result = SenhaValidator.isSenhaValida(senha);
        assertEquals(expResult, result);
    }

    @Test
    public void testSenha12CaraceteresMINUSCULA_FALSE() {
        System.out.println("isSenhaValida");
        String senha = "abcdefabcdef";
        boolean expResult = false;
        boolean result = SenhaValidator.isSenhaValida(senha);
        assertEquals(expResult, result);
    }

    @Test
    public void testSenha12Caraceteresnumeros_FALSE() {
        System.out.println("isSenhaValida");
        String senha = "012345678912";
        boolean expResult = false;
        boolean result = SenhaValidator.isSenhaValida(senha);
        assertEquals(expResult, result);
    }

    @Test
    public void testSenha12CaraceteresNumerosMasiculaseMinusculas_TRUE() {
        System.out.println("isSenhaValida");
        String senha = "Abcdefabcde1";
        boolean expResult = true;
        boolean result = SenhaValidator.isSenhaValida(senha);
        assertEquals(expResult, result);
    }
    /**/
}
