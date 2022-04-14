/*
 * NewInterface.java
 * 
 * Created on 07/08/2007, 07:29:11
 * 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.flem.fwe.service;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author mjpereira
 */
public interface IUsuarioExterno extends Serializable {

    public Integer getId();

    public String getUsername();

    public Integer getMatriculaHR();
    
    public Integer getCodigoDominio();

    public String getNome();
    
    public String getLotacao();
    
    public Integer getLotacaoDominio();
    
    public Boolean getAtivo() ;
    
    public Collection<String> getPermissoes();

}
