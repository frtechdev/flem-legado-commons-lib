/*
 * StringUtil.java
 *
 *Arquivo Utilitario para manipular Strings.
 *
 * Created on 10 de Novembro de 2006, 09:17
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package br.org.flem.fwe.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author mjpereira
 */
public class StringUtil {
    
    public static final String EMPTY = "";
    public static final String SPACE = " "; 

    /** Creates a new instance of StringUtil */
    public StringUtil() {
    }

    public static String formatarCodigo(String codigo) {
        StringBuffer sb = new StringBuffer("P000000");
        sb.replace(7 - codigo.length(), 7, codigo);
        return sb.toString();
    }

    public static int parseCodigo(String codigo) {
        String numero = codigo.substring(1);
        return Integer.parseInt(numero);
    }

    //String.format ("%010d", 45075)
    public static String formatarNumeroINSS(String numero) {
        numero = numero.replaceAll("[^0-9]", "");
        return numero;
    }

    public static Integer formatarSexo(String sexo) {

        if (sexo != null) {
            if (sexo.trim().equalsIgnoreCase("M")) {
                return 1;
            } else if (sexo.trim().equalsIgnoreCase("F")) {
                return 2;
            }

        }

        return null;
    }

    /**
     *Corverte um numero decimal em uma string
     *Sem virgula ou ponto
     *
     *
     */
    public static String converteNumeroString(double valor) {
        DecimalFormat formatador = new DecimalFormat();
        formatador.applyPattern("0.00;(0.00)");
        return formatador.format(valor).replaceAll("[^0-9]", "");
    }

    public static String converteNumeroString(double valor, int tamanho) {
        DecimalFormat formatador = new DecimalFormat();
        formatador.setMinimumFractionDigits(tamanho);
        return formatador.format(valor).replaceAll("[^0-9]", "");
    }

    public static String converteNumeroString(BigDecimal valor) {
        return converteNumeroString(valor.doubleValue());
    }

    public static String converteNumeroString(BigDecimal valor, int tamanho) {
        return converteNumeroString(valor.doubleValue(), tamanho);
    }

    @Deprecated
    public static String formatarCampoNumerico(String numero, int tamCampo) {
        numero = numero.replaceAll("[^0-9]", "");
        StringBuffer sb = new StringBuffer(numero);
        while (sb.length() < tamCampo) {
            sb.insert(0, "0");
        }
        return sb.substring(0, tamCampo);
    }
    
    public static String formatarCampoNumerico(String numero, String tamEmZeros) {
        numero = numero.replaceAll("[^0-9]", "");
        String retorno = tamEmZeros+numero;
        return retorno.substring(retorno.length()-tamEmZeros.length());
    }

    public static String formatarCampoNumerico(int numero, int tamCampo) {
        NumberFormat nf = new DecimalFormat("0");
        nf.setMinimumIntegerDigits(tamCampo);
        return nf.format(numero);
    }

    public static String formatarZeros(int tamCampo) {
        return formatarCampoNumerico(0, tamCampo);
    }

    public static String formatarCampoCaracter(String texto, int tamCampo) {
        StringBuffer sb = new StringBuffer(texto);
        while (sb.length() < tamCampo) {
            sb.append(" ");
        }
        return sb.substring(0, tamCampo);
    }

    public static String repetirTexto(String texto, int tamCampo) {
        StringBuffer sb = new StringBuffer(tamCampo);
        do {
            sb.append(texto);
        } while (sb.length() <= tamCampo);
        return sb.substring(0, tamCampo);
    }

    public static String formatarCampoCaracterAlinhadoDireita(String texto, int tamCampo) {
        StringBuffer sb = new StringBuffer(tamCampo);
        int tamTexto = texto.trim().length();
        int sobra = tamCampo - tamTexto;
        for (int i = 0; i < sobra; i++) {
            sb.append(" ");
        }
        sb.append(texto);
        return sb.substring(0, tamCampo);

    }

    public static String formatarBrancos(int tamCampo) {
        return formatarCampoCaracter("", tamCampo);
    }

    /**
     * Formata uma String no formato XX,YY para XX. Exemplo: uma String 10,0 será formatada para 10
     * @param String com o valor
     * @return int com a parte inteira
     */
    public static int formatarStringParaInteiro(String str) {
        int inteiro = 0;
        String str_aux = "";
        for (int i = 0; i < str.length(); i++) {
             if(str.substring(i,i+1).equals(",")){
                 break;
             }
             str_aux = str_aux + str.substring(i,i+1);
        }
        inteiro = Integer.parseInt(str_aux);

        return inteiro;
    }

    public static String removeUltimoCaracter(String texto) {
        String retorno = null;
        if (texto != null && texto.length() >= 1) {
            retorno = texto.substring(0, texto.length() - 1);
        }
        return retorno;
    }

    public static String obterUltimoCaracter(String texto) {
        String retorno = null;
        if (texto != null && texto.length() >= 1) {
            retorno = texto.substring(texto.length() - 1);
        }
        return retorno;
    }

    public static String removeAcentosECaracteresEspecias(String texto) {
        texto = texto.replaceAll("[áàâäãªÁÀÄÃÂ]", "A");
        texto = texto.replaceAll("[èéêëÈÉÊË]", "E");
        texto = texto.replaceAll("[íìïîÍÌÏÎ]", "I");
        texto = texto.replaceAll("óòöôÓÒÖÔº", "O");
        texto = texto.replaceAll("[úùûüÚÙÛÜ]", "U");
        texto = texto.replaceAll("[çÇ]", "C");
        return texto;
    }

    public static String sugestaoLogin(String nome) {
        List<String> conectivos = new ArrayList<String>();
        conectivos.add("da");
        conectivos.add("de");
        conectivos.add("do");
        conectivos.add("das");
        conectivos.add("dos");
        conectivos.add("e");
        nome = removeAcentosECaracteresEspecias(nome).toLowerCase();
        String[] nomes = nome.split(" ");
        String user = "";
        int i = 0;
        while (i < nomes.length - 1) {
            String temp = nomes[i];
            if (!conectivos.contains(temp) && !temp.trim().isEmpty()) {
                user = user + temp.charAt(0);
            }
            i++;
        }
        user = user + nomes[i];
        return user;
    }

    public static String ultimoNomeMinusculo(String nome) {
        nome = removeAcentosECaracteresEspecias(nome).toLowerCase();
        String[] nomes = nome.split(" ");
        String user = nomes[nomes.length - 1];
        return user;
    }

    public static String primeiroNome(String nome) {
        String[] nomes = nome.split(" ");
        String user = nomes[0];
        return user;
    }

    public static String ultimoNome(String nome) {
        String[] nomes = nome.split(" ");
        String user = nomes[nomes.length - 1];
        return user;
    }

    /**
     * Transformar um endereço IP de Long para String.
     * @author denise
     * @since 18/05/2006
     * @param Long ip
     * @return String
     * @throws Exception
     *
     * Alterado
     * @author mjpereira
     * @since 10/01/2008
     *
     */
    public static String longToIp(Long ip) throws Exception {
        try {
            Long ipFinal = ip.longValue();
            return String.valueOf((ipFinal >> 24) + "." + ((ipFinal >> 16) & 255) + "." + ((ipFinal >> 8) & 255) + "." + (ipFinal & 255));
        } catch (Exception e) {
            throw new Exception("IP não pode ser nulo");
        }
    }//fim longToIp

    /**
     * Transformar um endereço IP de String para Long.
     * @author arildogueno
     * @since 05/04/2006
     * @param String ip
     * @return Long
     * @throws Exception
     *
     * Alterada
     * @author mjpereira
     * @since 10/01/2008
     */
    public static Long ipToLong(String ip) throws Exception {
        String ipString;
        Integer nr;
        try {
            StringTokenizer t = new java.util.StringTokenizer(ip, ".");
            ipString = "";
            while (t.hasMoreTokens()) {
                nr = Integer.valueOf(t.nextToken());
                //Se 0 <= n <= 15 ( n: numero obtido com hasMoreTokens )
                if (Integer.toHexString(nr.intValue()).length() == 1) {
                    //converter para hexadecimal e acrescentar um 0 a' esquerda
                    ipString = ipString.concat("0" + Integer.toHexString(nr.intValue()));
                } else {
                    //caso contrario, basta converter
                    ipString = ipString.concat(Integer.toHexString(nr.intValue()));
                }
            }
            //converter hexadecimal para long
            return new Long(Long.parseLong(ipString, 16));
        } catch (Exception e) {
            throw new Exception("Não pode converter String ip para Long ip = " + ip);
        }

    }//fim ipToLong

    /**
     * Formata CEP e o retorna com máscara correta.
     * @param String cep
     * @return String strCEP
     */
    public static String formataCEP(String cep) {
        if (cep == null) {
            return null;
        }
        String strCEP = "";

        if (cep.length() == 8) {
            for (int c = 0; c < 8; c++) {
                strCEP += cep.charAt(c);
                if (c == 1) {
                    strCEP += ".";
                }
                if (c == 4) {
                    strCEP += "-";
                }
            }
        }

        return strCEP;
    }

    /**
     * @deprecated
     * Recebe um CNPJ e o retorna com máscara correta.
     * @param String CNPJ a ser mascarado
     * @return String CNPJ mascarada
     */
    public static String formataCNPJ(String strCNPJ) {
        return CNPJ.formataCNPJ(strCNPJ);
    }

    /**
     * @deprecated
     * Recebe somente os números de um CPF e o retorna com a máscara correta.
     * @param String CPF a ser mascarado
     * @return String CPF mascarado
     */
    public static String formataCPF(String strCPF) throws ParseException, ParseException {
        return CPF.formataCPF(strCPF);
    }

    /**
     * Formata RG e o retorna com máscara adequada.
     * @param String rg
     * @return String strRG
     */
    public static String formataRG(String rg) {
        String strRG = "";
        int d = 0, l = 0, c = 0;

        if (rg == null) {
            return null;
        }
        l = rg.length();
        if (l == 8 || l == 9) {
            if (l == 9) {
                d = 1;
            }
            for (c = 0; c < (8 + d); c++) {
                strRG += rg.charAt(c);
                if (c == (0 + d)) {
                    strRG += ".";
                }
                if (c == (3 + d)) {
                    strRG += ".";
                }
                if (c == (6 + d)) {
                    strRG += "-";
                }
            }
        }
        return strRG;
    }

    /**
     * Completa a String com caracteres em branco no ínício
     * até atingir tamanho.
     * @param paramAlfa String a ser formatada
     * @param tamanho tamanho que a String retornada deve ter
     * @return String formatada
     * @throws Exception
     */
    public static String formataTamanho(String paramAlfa, int tamanho) throws Exception {
        if (paramAlfa == null) {
            return null;
        }
        StringBuffer retorno = new StringBuffer(paramAlfa);

        if (paramAlfa.length() > tamanho) {
            throw new Exception("String com tamanho inválido para conversão.");
        }

        for (int i = 0; i < tamanho - paramAlfa.length(); i++) {
            retorno.append(" ");
        }

        return retorno.toString();
    }

    /**
     * Retorna String sem acentuação e em caixa alta.
     * @param str String a ser formatada
     * @return
     */
    public static String padraoMainFrame(String str) {
        return tiraAcento(str.toUpperCase());
    }

    /**
     * Retorna a String informada como parâmetro modificando sua primeira letra pra letra maiúscula.
     * @param string  a ser modificada
     * @return String modificada
     */
    public static String primeiraLetraToUpperCase(String string) {
        if (string != null && string.length() > 0) {
            return string.replaceFirst(String.valueOf(string.charAt(0)), String.valueOf(string.charAt(0)).toUpperCase());
        }
        return string;
    }

    /**
     * Retorna a String informada como parâmetro modificando sua primeira letra pra letra minúscula.
     * @param string  a ser modificada
     * @return String modificada
     */
    public static String primeiraLetraToLowerCase(String string) {
        if (string != null && string.length() > 0) {
            return string.replaceFirst(String.valueOf(string.charAt(0)), String.valueOf(string.charAt(0)).toLowerCase());
        }
        return string;
    }

    /**
     * Remove acentuação de uma String passada como parâmetro.
     * @param linha String possilvemente acentuada
     * @return String sem acento
     */
    public static String tiraAcento(String linha) {
        if (linha == null) {
            return null;
        }
        String LinhaNova = "";
        String Acento = "";
        String Letra = "";
        String ComAcento = "àáãäâÀÁÃÄÂèéëêÈÉËÊ";
        ComAcento += "ìíïîÌÍÏÎòóõöôÒÓÕÖÔùúüûÙÚÜÛçÇ";
        String SemAcento = "aaaaaAAAAAeeeeEEEE";
        SemAcento += "iiiiIIIIoooooOOOOOuuuuUUUUcC";
        for (int i = 0; i <= linha.length() - 1; i++) {
            Letra = linha.substring(i, i + 1);
            for (int j = 0; j <= ComAcento.length() - 1; j++) {
                if (ComAcento.substring(j, j + 1).equals(Letra)) {
                    Letra = SemAcento.substring(j, j + 1);
                }
            }
            LinhaNova = LinhaNova + Letra;
        }
        Acento = LinhaNova;
        return Acento;
    }

    /**
     * Método utilizado para remover a formatação de Strings contendo números.
     * Retira pontos, barras, traços e espaços em branco.
     * @param s string a ser analisada.
     * @return apeas os números da string informada.
     */
    public static String removeFormatacao(String s) {
        if (s != null) {
            s = s.replace(".", "");
            s = s.replace("/", "");
            s = s.replace("-", "");
            s = s.replace(" ", "");
        }
        return s;
    }

    /**
     * Método utilizado para verificar se uma determinada String é composta sempre
     * do mesmo caracter.
     * Ex: "SSSSSS" - true
     *     "SSSXSS" - false
     * @param string a ser analisada.
     * @return true - caso a String seja composta sempre do mesmo caracter.
     *         false - caso contrário
     */
    public static boolean isSameCharacter(String s) {
        if (s != null && s.length() > 0) {
            char first = s.charAt(0);

            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != first) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    /**
     * Verifica se uma determinada string é não nula.
     * Verifica se a String é diferente de null e diferente de ""
     * @param s String a verificar
     * @return bool
     * @author gabriel.ortiz
     */
    public static boolean stringNotNull(String s) {
        return (s != null && !"".equals(s.trim()));
    }

    /**
     * Adiciona caracter especificado na posição indicada (inicio/fim) da String.
     * @param String : param (string original)
     * @param char : caractere (caractere a ser inserido na string)
     * @param int : quantidade (de caracteres a serem inseridos na posição
     * @param int : pos (0 = inicio, 1 = fim)
     * @param boolean : trim (true / false)
     * @return String (string modificada)
     * @author Leslie
     * @since 05/06/2007
     */
    public static String addCharStrPos(String param, char caractere, int quantidade, int pos, boolean trim) {
        int tam_final = param.length() + quantidade;
        StringBuilder sb = new StringBuilder(tam_final);
        sb.append(param != null ? param : "");

        switch (pos) {
            case 0: { // início
                while (sb.length() < tam_final) {
                    sb.insert(0, caractere);
                }

                break;
            }
            case 1: { // fim
                while (sb.length() < tam_final) {
                    sb.append(caractere);
                }
                break;
            }
            default:
                break;
        }

        if (trim) {
            return sb.toString().trim();
        } else {
            return sb.toString();
        }
    }

    /**
     * Adiciona caracter especificado n vezes no inicio da String.
     * @param String : param (string original)
     * @param char : caractere (caractere a ser inserido na string)
     * @param int : quantidade (de caracteres a serem inseridos na posição
     * @return String (string modificada)
     * @author Leslie
     * @since 05/06/2007
     */
    public static String addCharInicioStr(String param, char caractere, int quantidade) {
        return addCharStrPos(param, caractere, quantidade, 0, false);
    }

    /**
     * Adiciona caracter especificado n vezes no final da String.
     * @param String : param (string original)
     * @param char : caractere (caractere a ser inserido na string)
     * @param int : quantidade (de caracteres a serem inseridos na posição
     * @return String (string modificada)
     * @author Leslie
     * @since 05/06/2007
     */
    public static String addCharFinalStr(String param, char caractere, int quantidade) {
        return addCharStrPos(param, caractere, quantidade, 1, false);
    }

    /**
     * Adiciona caracter especificado n vezes no inicio da String fazendo trim ao final.
     * @param String : param (string original)
     * @param char : caractere (caractere a ser inserido na string)
     * @param int : quantidade (de caracteres a serem inseridos na posição
     * @return String (string modificada)
     * @author Leslie
     * @since 05/06/2007
     */
    public static String addCharInicioStrTrim(String param, char caractere, int quantidade) {
        return addCharStrPos(param, caractere, quantidade, 0, true);
    }

    /**
     * Adiciona caracter especificado n vezes no final da String.
     * @param String : param (string original)
     * @param char : caractere (caractere a ser inserido na string)
     * @param int : quantidade (de caracteres a serem inseridos na posição
     * @return String (string modificada)
     * @author Leslie
     * @since 05/06/2007
     */
    public static String addCharFinalStrTrim(String param, char caractere, int quantidade) {
        return addCharStrPos(param, caractere, quantidade, 1, true);
    }

    /**
     * Remove caracter especificado na posição indicada (inicio/fim) da String.
     * @param String : param (string original)
     * @param char : caractere (caractere a ser inserido na string)
     * @param int : quantidade (de caracteres a serem removidos da posição -1=todos, n>0-> número de remoções)
     * @param int : pos (0 = inicio, 1 = fim)
     * @param boolean : trim (true / false)
     * @return String (string modificada)
     * @author Leslie
     * @throws Exception No caso de param ser nulo
     * @since 05/06/2007
     */
    public static String delCharStrPos(String param, char caractere, int quantidade, int pos, boolean trim) throws Exception {
        int p;
        String retorno;

        if (param != null) {
            switch (pos) {
                case 0: { // início
                    switch (quantidade) {
                        case -1: // todos os caracteres
                        {
                            p = 0;
                            while (param.charAt(p) == (char) caractere && p < param.length()) {
                                p++;
                            }
                            retorno = param.substring(p);
                            break;
                        }
                        default: // somente um número x de caracteres
                        {
                            p = 0;
                            while (param.charAt(p) == caractere && p < quantidade) {
                                p++;
                            }
                            retorno = param.substring(p);
                            break;
                        }
                    }
                    break;
                }
                case 1: { // fim
                    switch (quantidade) {
                        case -1: // todos
                        {
                            p = param.length() - 1;
                            while (param.charAt(p) == caractere && p >= 0) {
                                p--;
                            }
                            retorno = param.substring(0, p + 1);
                            break;
                        }
                        case 0: {
                            retorno = param;
                            break;
                        }
                        default: // um número definido de caracteres
                        {
                            p = param.length() - 1;
                            int q = quantidade;
                            while ((param.charAt(p) == caractere) && (q > 0)) {
                                p--;
                                q--;
                            }
                            retorno = param.substring(0, p + 1);
                            break;
                        }
                    }
                    break;
                }
                default: // nem início nem fim...
                {
                    retorno = "";
                    break;
                }
            }

            if (trim) {
                return retorno.trim();
            } else {
                return retorno;
            }
        } // parametro nulo
        else {
            throw new Exception("ERRO! String de parâmetro não pode ser nula.");
        }
    }// final método

    /**
     * Remove caracter especificado no inicio da String.
     * @param String : param (string original)
     * @param char : caractere (caractere a ser removido da string)
     * @return String (string modificada)
     * @author Leslie
     * @throws Exception
     * @since 05/06/2007
     */
    public static String delCharInicioStr(String param, char caractere) throws Exception {
        return delCharStrPos(param, caractere, -1, 0, false);
    }

    /**
     * Remove caracter especificado no inicio da String.
     * @param String : param (string original)
     * @param char : caractere (caractere a ser inserido na string)
     * @param int : quantidade (de caracteres a serem removidos da posição (-1 => todos))
     * @return String (string modificada)
     * @author Leslie
     * @throws Exception
     * @since 05/06/2007
     */
    public static String delNumCharInicioStr(String param, char caractere, int quantidade) throws Exception {
        return delCharStrPos(param, caractere, quantidade, 0, false);
    }

    /**
     * Remove caracter especificado do final da String.
     * @param String : param (string original)
     * @param char : caractere (caractere a ser inserido na string)
     * @param int : quantidade (de caracteres a serem removidos da posição)
     * @return String (string modificada)
     * @author Leslie
     * @throws Exception
     * @since 05/06/2007
     */
    public static String delCharFinalStr(String param, char caractere) throws Exception {
        return delCharStrPos(param, caractere, -1, 1, false);
    }

    /**
     * Remove caracter especificado n vezes do final da String.
     * @param String : param (string original)
     * @param char : caractere (caractere a ser inserido na string)
     * @param int : quantidade (de caracteres a serem removidos da posição (-1 => todos))
     * @return String (string modificada)
     * @author Leslie
     * @throws Exception
     * @since 05/06/2007
     */
    public static String delNumCharFinalStr(String param, char caractere, int quantidade) throws Exception {
        return delCharStrPos(param, caractere, quantidade, 1, false);
    }

    /**
     * Remove caracter especificado n vezes do inicio da String fazendo trim após a remoção.
     * @param String : param (string original)
     * @param char : caractere (caractere a ser removido da string)
     * @param int : quantidade (de caracteres a serem removidos da posição (-1 => todos))
     * @return String (string modificada)
     * @author Leslie
     * @throws Exception
     * @since 05/06/2007
     */
    public static String delCharInicioStrTrim(String param, char caractere, int quantidade) throws Exception {
        return delCharStrPos(param, caractere, quantidade, 0, true);
    }

    /**
     * Remove caracter especificado n vezes no final da String fazendo trim após remoção.
     * @param String : param (string original)
     * @param char : caractere (caractere a ser inserido na string)
     * @param int : quantidade (de caracteres a serem removidos da posição (-1 => todos))
     * @return String (string modificada)
     * @author Leslie
     * @throws Exception
     * @since 05/06/2007
     */
    public static String delCharFinalStrTrim(String param, char caractere, int quantidade) throws Exception {
        return delCharStrPos(param, caractere, quantidade, 1, true);
    }
    
    public static String html2text(String texto){
        String novoTexto = "";
        
        novoTexto = texto.replaceAll("<BR>", "\n");
        novoTexto = novoTexto.replaceAll("</P>", "\n\n");
        novoTexto = novoTexto.replaceAll("&nbsp;", " ");
        
        return novoTexto.replaceAll("\\<.*?\\>", "");
        //return Jsoup.parse(html).text();
    }
    
    public static boolean isValidoEmail(String email) {
            Pattern pattern = Pattern.compile (
                     "([a-zA-Z0-9_\\-\\.]+)@((\\[a-z]{1,3}\\.[a-z]"
                     + "{1,3}\\.[a-z]{1,3}\\.)|(([a-zA-Z\\-]+\\.)+))"
                     + "([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)", 
                     Pattern.MULTILINE);
            Matcher m=pattern.matcher(email);
            return m.matches();
    }
    
    public static String convertListaIntToString(List<Integer> inteiros){
        
        String num="";
        for(Integer cod : inteiros){
          num = num + cod.toString()+',';  
        }
        
        if (num.length() > 0){ 
             num = num.substring (0, num.length() - 1);
        }

        return num;
    }

    
    public static void main(String[] args) {
        
        System.out.println(StringUtil.converteNumeroString(1.0));
        System.out.println("fora = '" + StringUtil.formatarCampoNumerico(StringUtil.converteNumeroString(1.0), 5));
        System.out.println("fora = '" + StringUtil.formatarCampoNumerico("17", "00000"));
        
        /*Calendar c1 = new GregorianCalendar(2007, 11, 1, 12, 0);
        Calendar c2 = new GregorianCalendar(2007, 11, 1, 12, 59);

        Date data1 = c1.getTime();
        Date data2 = c2.getTime();
        boolean teste = true;
        Calendar c = new GregorianCalendar();
        c.setTime(data1);
        c.add(Calendar.MINUTE, 59);
        teste = c.getTime().before(data2);
        System.out.println("teste = " + teste);*/


        /*String pattern = "0000000000%d";
        int entrada = 58;
        Formatter f = new Formatter().format(pattern, entrada);
        System.out.println("formatacao:" + f);*/

        /*System.out.println(StringUtil.sugestaoLogin("GILVA CARVALHO DOS SANTOS SOBRINHO"));

        System.out.println(StringUtil.formatarCampoCaracterAlinhadoDireita("TXT", 10));

        System.out.println(StringUtil.repetirTexto("-", 10));
        System.out.println(StringUtil.converteNumeroString(100));
        
        String htmlText = "<P>Destacam-se como atividades do Projeto:</P>"
                + "<P>. No ano de 2009:<BR>- realização de reuniões com técnicos da SEC e da FLEM para detalhamento do escopo e "
                + "alinhamento de conceitos sobre o Programa;<BR>- planejamento dos processos de recrutamento e seleção da equipe de profissionais que comporão a Unidade Gestora Central e dos Pólos;<BR>- montagem, na FLEM, de espaço físico, com toda a infraestrutura necessária à instalação e funcionamento da Unidade Gestora Central;<BR>- pesquisa e análise dos documentos técnicos e legais que dão base ao Programa TRILHA e PROJOVEM Urbano, tais como: Proposta Técnica, Plano de Trabalho, Termo de Referência e Módulos/Guia de Estudo; Projeto de Capacitação em Formação Cidadã para Jovens do Programa TRILHA e PROJOVEM Urbano, "
                + "Projeto Pedagógico Integrado - PPI, Leis, Decretos e Resoluções. </P>"
                + "<P>. No ano de 2010:</P>"
                + "<P>6) Equipe do Núcleo de Soluções Educacionais ? NSE/FLEM:</P>"
                + "<P>Ações Operacionais e Administrativas:<BR>- realização do Relatório Mensal das atividades da coordenação e "
                + "equipe técnica;<BR>- consolidação dos Relatórios de Acompanhamento do Programa sob a responsabilidade da FLEM;"
                + "<BR>- realização de reuniões de alinhamento com a área administrativo-financeira da FLEM;<BR>- acompanhamento "
                + "das atividades do Programa, nos municípios dos Pólos;<BR>- levantamento, análise e elaboração de Editais;"
                + "<BR>- emissão de Solicitação de Viagem ? SDV para cada profissional em deslocamento e acompanhamento para as "
                + "devidas prestações de contas;<BR>- realização de reserva de espaço, disponibilização de hotel, hospedagem e "
                + "refeições;<BR>- ressarcimentos de despesas de locomoção para as Equipes dos Pólos e<BR>de passagens "
                + "aéreas para alguns consultores;<BR>- fornecimento de táxis para consultores da Equipe Estadual;"
                + "<BR>- realização de apoio operacional ao Evento de Construção e Aprimoramento do Projeto Pedagógico do Programa TRILHA; <BR>"
                + "<P>&nbsp;</P>"
                + "<P>&nbsp;</P>"
                + "<P>&nbsp;</P>";
        
        System.out.println(html2text(htmlText));*/
    }
    
    public static int modulo11(String codigo){
        Integer count = Integer.parseInt(Character.toString(codigo.charAt(0)))*6 +
                        Integer.parseInt(Character.toString(codigo.charAt(1)))*5 +
                        Integer.parseInt(Character.toString(codigo.charAt(2)))*4 +
                        Integer.parseInt(Character.toString(codigo.charAt(3)))*3 +
                        Integer.parseInt(Character.toString(codigo.charAt(4)))*2 +
                        Integer.parseInt(Character.toString(codigo.charAt(5)))*9 +
                        Integer.parseInt(Character.toString(codigo.charAt(6)))*8 +
                        Integer.parseInt(Character.toString(codigo.charAt(7)))*7 +
                        Integer.parseInt(Character.toString(codigo.charAt(8)))*6 +
                        Integer.parseInt(Character.toString(codigo.charAt(9)))*5 +
                        Integer.parseInt(Character.toString(codigo.charAt(10)))*4 +
                        Integer.parseInt(Character.toString(codigo.charAt(11)))*3 +
                        Integer.parseInt(Character.toString(codigo.charAt(12)))*2;
        int digito = 11 - (count % 11 );
        return digito > 9 ? 0 : digito;
    }
}
