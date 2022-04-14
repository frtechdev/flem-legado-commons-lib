/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.org.flem.fwe.util;

import br.org.flem.fwe.exception.AcessoArquivosException;
import java.io.File;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author dbbarreto
 * --
 */
public class LeitorArquivo {

    public String arquivoParaString(String caminhoArquivo) throws AcessoArquivosException {
        
        try {
            //org.apache.commons.io.FileUtils.readLines(file, caminhoArquivo)
            StringBuffer retorno = new StringBuffer();
            File file = new File(caminhoArquivo);
            //BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = FileUtils.readFileToString(file, "ISO-8859-1");

            retorno.append(line);

            return retorno.toString();
        }
        catch (Exception e) {
            throw new AcessoArquivosException(e);
        }   
    }

    public String arquivoParaString(String caminhoArquivo, String codificao) throws AcessoArquivosException {

        try {
            //org.apache.commons.io.FileUtils.readLines(file, caminhoArquivo)
            StringBuffer retorno = new StringBuffer();
            File file = new File(caminhoArquivo);
            //BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line = FileUtils.readFileToString(file, codificao);

            retorno.append(line);

            return retorno.toString();
        }
        catch (Exception e) {
            throw new AcessoArquivosException(e);
        }
    }
}
