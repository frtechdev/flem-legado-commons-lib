/*
 * RegistroUnicoInvalidoException.java
 *
 * Created on 12 de Fevereiro de 2009, 08:37
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.org.flem.fwe.exception;

/**
 *
 * Esta exceção é usada quando apenas um registro deveria retornar de uma consulta, só que mais de um são retornados.
 *
 * @author dbbarreto
 */
public class RegistroUnicoInvalidoException extends AplicacaoException{
    
    /** Creates a new instance of RegistroUnicoInvalidoException */
    public RegistroUnicoInvalidoException() {
    }
    
    public RegistroUnicoInvalidoException(String arg0) {
        super(arg0);
    }
    
    public RegistroUnicoInvalidoException(String arg0, Throwable arg1) {
        super(arg0, arg1);
    }
    
    public RegistroUnicoInvalidoException(Throwable arg0) {
        super(arg0);
    }
    
}
