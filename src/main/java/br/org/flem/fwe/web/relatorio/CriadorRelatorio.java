/*
 * CriadorRelatorio.java
 *
 * Created on 13 de Março de 2007, 08:32
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
 * Classe responsável por montar e exportar os relatórios.
 * @author dbbarreto
 */
public abstract class CriadorRelatorio {

    /**
     * Método que monta o relatório com os parâmetros e a coleção de objetos. Este método é privado para que o usuário da classe escolha um dos métodos para a exportação desse relatório.
     * @param request O request utilizado no momento, pela action.
     * @param localArquivo Local do arquivo, relativo ao contexto da aplicação.
     * @param parametros Lista de parâmetros definida para o relatório.
     * @param resultado Coleção de objetos que será utilizada como DataSource do relatório.
     * @throws net.sf.jasperreports.engine.JRException Exceção lançada, caso o relatório apresente algum problema.
     * @return Relatório pronto, que será utilizado por algum método de exportação.
     */
    protected abstract void montaRelatorio(HttpServletRequest request, Map parametros);

    public void exportaRelatorioPDF(HttpServletRequest request, HttpServletResponse response, String localArquivo, Map parametros, Collection resultado) throws JRException, RelatorioSemDadosException, IOException {

        if (resultado == null || resultado.size() <= 0) {
            throw new RelatorioSemDadosException("O relatório selecionado não possui dados a serem exibidos.");
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
