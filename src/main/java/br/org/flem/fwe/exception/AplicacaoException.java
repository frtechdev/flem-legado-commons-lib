/*
 * AplicacaoException.java
 *
 * Created on 15 de Maio de 2009
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.org.flem.fwe.exception;

/**
 *
 * @author dbbarreto
 */
public class AplicacaoException extends Exception{
    
    /** Creates a new instance of AcessoDadosException */
    public AplicacaoException() {
    }
    
    public AplicacaoException(String arg0) {
        super(arg0);
    }
    
    public AplicacaoException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }
    
    public AplicacaoException(Throwable arg0) {
        super(arg0);
    }
    
}
