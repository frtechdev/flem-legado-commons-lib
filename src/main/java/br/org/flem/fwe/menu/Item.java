/*
 * Item.java
 *
 * Created on 29 de Novembro de 2006, 13:31
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.org.flem.fwe.menu;

import java.util.HashSet;
import java.util.Set;


/**
 *
 * @author mjpereira
 */
public class Item {
    
    private String perfil;
    private String href;
    private String nome;
    /** Creates a new instance of Item */
    public Item() {
    }
    
    public String getHref() {
        return href;
    }
    
    public void setHref(String href) {
        this.href = href;
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public String getPerfil() {
        return perfil;
    }
    
    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }
    
}
