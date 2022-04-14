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
public class AcessoArquivosException extends AplicacaoException{
    
    /** Creates a new instance of AcessoDadosException */
    public AcessoArquivosException() {
    }
    
    public AcessoArquivosException(String arg0) {
        super(arg0);
    }
    
    public AcessoArquivosException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }
    
    public AcessoArquivosException(Throwable arg0) {
        super(arg0);
    }
    
}
