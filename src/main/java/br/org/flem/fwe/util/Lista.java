/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package br.org.flem.fwe.util;

import java.util.Collection;

/**
 *
 * @author mjpereira
 */
public class Lista {

    public static Collection convertCollectionInteger(String[] array , Collection<Integer> c){
        for(String s : array){
            c.add(Integer.parseInt(s));
        }
        return c;
    }
    
    
}
