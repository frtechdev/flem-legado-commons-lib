/*
 * BaseServletFilterAb.java
 *
 * Created on 6 de Setembro de 2006, 17:14
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.org.flem.fwe.web.filtro.base;

import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;

/**
 *
 * @author mjpereira
 */
public abstract class BaseServletFilterAb implements Filter {
    
    protected FilterConfig filterConfig;
    
    public void init(FilterConfig fc) throws ServletException {
        this.filterConfig = fc;
    }
    
    public void destroy() {
        this.filterConfig = null;
    }
    
    
}
