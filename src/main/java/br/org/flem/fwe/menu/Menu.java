/*
 * Menu.java
 *
 * Created on 29 de Novembro de 2006, 13:28
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.org.flem.fwe.menu;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author mjpereira
 */
public class Menu {
    
    private String nome;
    
    private List<Item> itens = new ArrayList<Item>();
    
    /** Creates a new instance of Menu */
    public Menu() {
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public List<Item> getItens() {
        return itens;
    }
    
    public void setItens(List<Item> itens) {
        this.itens = itens;
    }
    
    public Set<String> getPermissoes(){
        Set<String> permissao = new HashSet<String>();
        for(Item i : itens){
            permissao.add(i.getPerfil());
        }
        return permissao;
    }
    
}
