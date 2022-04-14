/*
 * Autentica.java
 *
 * Created on 6 de Dezembro de 2006, 14:08
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.org.flem.fwe.service;

import br.org.flem.fwe.exception.LoginInvalidoException;

/**
 *
 * @author mjpereira
 */
public interface Autentica {

    IUsuarioExterno obterUsuario(String username) throws LoginInvalidoException;
    
    
    
}
