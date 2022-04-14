/*
 * MenuReader.java
 *
 * Created on 29 de Novembro de 2006, 10:18
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package br.org.flem.fwe.menu;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

/**PadraoScanner scanner
 *
 * @author mjpereira
 */
public class MenuReader {
    
    private DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
    private DocumentBuilder builder = null;
    private PadraoScanner scanner;
    
    /** Creates a new instance of MenuReader */
    public MenuReader() {
        try {
            builder = builderFactory.newDocumentBuilder();
            try {
                String arquivo = this.getClass().getClassLoader().getResource("menu.xml").getPath();
                scanner = new PadraoScanner (builder.parse(new org.xml.sax.InputSource(arquivo)));
            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (SAXException ex) {
                ex.printStackTrace();
            }
        } catch (ParserConfigurationException ex) {
            ex.printStackTrace();
        }
          
    }
    
    public Grupo obterMenu(){
        return scanner.visitDocument();
    }
    
    
    public static void main(String[] args){
        System.out.println(new MenuReader().obterMenu());
    }
    
    
}
