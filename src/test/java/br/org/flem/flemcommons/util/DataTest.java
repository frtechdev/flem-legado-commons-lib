/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.flem.flemcommons.util;

import br.org.flem.fwe.util.Data;
import java.util.Calendar;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author emsilva
 */
public class DataTest {
    
    public DataTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testeJaFezAniversario() {
        Calendar calDtNascimento = Calendar.getInstance();
        calDtNascimento.setTime(new Date());
        calDtNascimento.set(Calendar.YEAR, calDtNascimento.get(Calendar.YEAR) - 1);
        calDtNascimento.set(Calendar.DATE, calDtNascimento.get(Calendar.DATE) - 2);
        System.out.println(Data.anoDiffDecimal(calDtNascimento.getTime(), new Date()));
        assertEquals(Data.anoDiffDecimal(calDtNascimento.getTime(), new Date()).toString(), "1.0054644");
    }
    
    @Test
    public void testeVaiFazerAniversario() {
        Calendar calDtNascimento = Calendar.getInstance();
        calDtNascimento.setTime(new Date());
        calDtNascimento.set(Calendar.YEAR, calDtNascimento.get(Calendar.YEAR) - 1);
        calDtNascimento.set(Calendar.DATE, calDtNascimento.get(Calendar.DATE) + 2);
        System.out.println(Data.anoDiffDecimal(calDtNascimento.getTime(), new Date()));
        assertEquals(Data.anoDiffDecimal(calDtNascimento.getTime(), new Date()).toString(), "0.9945355");
    }
}
