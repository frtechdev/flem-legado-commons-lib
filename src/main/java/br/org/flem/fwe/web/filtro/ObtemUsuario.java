/*
 * ObtemUsuario.java
 *
 * Created on 6 de Setembro de 2006, 17:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.org.flem.fwe.web.filtro;

import br.org.flem.fwe.service.IUsuarioExterno;
import br.org.flem.fwe.web.filtro.base.BaseServletFilterAb;
import br.org.flem.fwe.util.Constante;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author mjpereira
 */
public class ObtemUsuario extends BaseServletFilterAb {

    /**
     * Obtém os dados do usuário conectado ao sistema e armazena-o na sessão da
     * conexão e em uma variável "thread-safe", para que outras classes possam
     * acessar seus dados.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        // caso nenhum usuário tenha se autenticado, não precisa executar
        // este método. um usuário não está autenticado ainda quando ele
        // acessa um recurso livre.
        IUsuarioExterno usr = (IUsuarioExterno) req.getSession().getAttribute(Constante.USUARIO_LOGADO);
        if(usr==null){
            System.out.println(" "+req.getPathInfo());
            RequestDispatcher rd = request.getRequestDispatcher("/login.jsp");
            rd.forward(request,response);
        }
        chain.doFilter(request, response);
    }

}
