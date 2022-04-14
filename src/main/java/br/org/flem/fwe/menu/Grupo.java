/*
 * Grupo.java
 *
 * Created on 29 de Novembro de 2006, 13:28
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.org.flem.fwe.menu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author mjpereira
 */
public class Grupo {
    
    private String nome;
    
    private List<Menu> menus = new ArrayList<Menu>();
    
    /** Creates a new instance of Grupo */
    public Grupo() {
    }
    
    public String getNome() {
        return nome;
    }
    
    public void setNome(String nome) {
        this.nome = nome;
    }
    
    public List<Menu> getMenus() {
        return menus;
    }
    
    public void setMenus(List<Menu> menus) {
        this.menus = menus;
    }
    
    public Set<String> getPermissoes(){
        Set<String> permissao = new HashSet<String>();
        for(Menu m : menus){
            permissao.addAll(m.getPermissoes());
        }
        return permissao;
    }
    
    public Set<Menu> getMenusComPermissao(Collection<String> permissoes) {
        Set<Menu> menusComPermissao = new HashSet<Menu>();
        for (Menu menu : menus) {
            for(String permissao : permissoes) {
                if (menu.getPermissoes().contains(permissao)) {
                    menusComPermissao.add(menu);
                    //break;
                }
            }
        }
        return menusComPermissao;
    }
}
