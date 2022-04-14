/*
 * PJBException.java
 *
 * Created on 8 de Março de 2007, 15:21
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.org.flem.fwe.exception;

/**
 *
 * @author mjpereira
 */
public class RelatorioSemDadosException extends AplicacaoException {

    public RelatorioSemDadosException(Exception ex) {
        super(ex);
    }
    
    public RelatorioSemDadosException(String message) {
        super(message);
    }
    
}
