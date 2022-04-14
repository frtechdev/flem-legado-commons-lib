/*
 * AcessoDadosException.java
 *
 * Created on 6 de Setembro de 2006, 17:19
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
 
package br.org.flem.fwe.exception;

/**
 *
 * @author mjpereira
 */
public class LegadoException extends Exception{
    
    
    public LegadoException(String arg0) {
        super(arg0);
    }
    
    public LegadoException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }
    
    public LegadoException(Throwable arg0) {
        super(arg0);
    }
    
}
