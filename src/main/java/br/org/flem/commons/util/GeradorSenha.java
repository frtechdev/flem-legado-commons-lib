/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.flem.commons.util;

import java.util.Random;

/**
 *
 * @author ajlima
 */
public class GeradorSenha {
    
    String senha="Flem";
    
    public String gerador(){
        
        String[] num ={"0","1","2","3","4","5","6","7","8","9"};
        String[] especial ={"f","l","e","m","b","a"};
        
        for(int x=0;x<=3;x++){
            Random rdm = new Random();
            senha=senha+num[rdm.nextInt(num.length)];
        }
        for(int x=0;x<=3;x++){
            Random rdm = new Random();
            senha=senha+especial[rdm.nextInt(especial.length)];
        }
        
        return senha;
    }
    
}
