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
 * @author fcsilva
 */
public class RegistroJaExistenteException extends AplicacaoException{
    
    /** Creates a new instance of RegistroJaExistenteException */
    public RegistroJaExistenteException() {
    }
    
    public RegistroJaExistenteException(String arg0) {
        super(arg0);
    }
    
    public RegistroJaExistenteException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }
    
    public RegistroJaExistenteException(Throwable arg0) {
        super(arg0);
    }
    
}
