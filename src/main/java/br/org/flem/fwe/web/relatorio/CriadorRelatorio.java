/*
 * CriadorRelatorio.java
 *
 * Created on 13 de Mar�o de 2007, 08:32
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package br.org.flem.fwe.web.relatorio;

import br.org.flem.fwe.exception.RelatorioSemDadosException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 * Classe respons�vel por montar e exportar os relat�rios.
 * @author dbbarreto
 */
public abstract class CriadorRelatorio {

    /**
     * M�todo que monta o relat�rio com os par�metros e a cole��o de objetos. Este m�todo � privado para que o usu�rio da classe escolha um dos m�todos para a exporta��o desse relat�rio.
     * @param request O request utilizado no momento, pela action.
     * @param localArquivo Local do arquivo, relativo ao contexto da aplica��o.
     * @param parametros Lista de par�metros definida para o relat�rio.
     * @param resultado Cole��o de objetos que ser� utilizada como DataSource do relat�rio.
     * @throws net.sf.jasperreports.engine.JRException Exce��o lan�ada, caso o relat�rio apresente algum problema.
     * @return Relat�rio pronto, que ser� utilizado por algum m�todo de exporta��o.
     */
    protected abstract void montaRelatorio(HttpServletRequest request, Map parametros);

    public void exportaRelatorioPDF(HttpServletRequest request, HttpServletResponse response, String localArquivo, Map parametros, Collection resultado) throws JRException, RelatorioSemDadosException, IOException {

        if (resultado == null || resultado.size() <= 0) {
            throw new RelatorioSemDadosException("O relat�rio selecionado n�o possui dados a serem exibidos.");
        }
        ServletOutputStream servletOutputStream = null;
        try {

            response.setContentType("application/save");

            response.setHeader("Content-disposition", "attachment; filename=" + "FlemWeb_Relatorio.pdf");

            servletOutputStream = response.getOutputStream();

            InputStream input = request.getSession().getServletContext().getResourceAsStream(localArquivo);

            JRBeanCollectionDataSource beanDataSource = new JRBeanCollectionDataSource(resultado);

            this.montaRelatorio(request, parametros);

            JasperRunManager.runReportToPdfStream(input, servletOutputStream, parametros, beanDataSource);

            servletOutputStream.flush();
            servletOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
            throw new JRException(e);
        } finally {
            if (servletOutputStream != null) {
                servletOutputStream.flush();
                servletOutputStream.close();
            }
        }
    }

    public void exportaRelatorioPDF(HttpServletRequest request, HttpServletResponse response, String localArquivo, Collection resultado) throws JRException, RelatorioSemDadosException, IOException {
        exportaRelatorioPDF(request, response, localArquivo, null, resultado);
    }
}
