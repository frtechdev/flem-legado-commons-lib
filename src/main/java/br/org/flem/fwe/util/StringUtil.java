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
     * Formata uma String no formato XX,YY para XX. Exemplo: uma String 10,0 ser� formatada para 10
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
        texto = texto.replaceAll("[����������]", "A");
        texto = texto.replaceAll("[��������]", "E");
        texto = texto.replaceAll("[��������]", "I");
        texto = texto.replaceAll("�������Ժ", "O");
        texto = texto.replaceAll("[��������]", "U");
        texto = texto.replaceAll("[��]", "C");
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
     * Transformar um endere�o IP de Long para String.
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
            throw new Exception("IP n�o pode ser nulo");
        }
    }//fim longToIp

    /**
     * Transformar um endere�o IP de String para Long.
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
            throw new Exception("N�o pode converter String ip para Long ip = " + ip);
        }

    }//fim ipToLong

    /**
     * Formata CEP e o retorna com m�scara correta.
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
     * Recebe um CNPJ e o retorna com m�scara correta.
     * @param String CNPJ a ser mascarado
     * @return String CNPJ mascarada
     */
    public static String formataCNPJ(String strCNPJ) {
        return CNPJ.formataCNPJ(strCNPJ);
    }

    /**
     * @deprecated
     * Recebe somente os n�meros de um CPF e o retorna com a m�scara correta.
     * @param String CPF a ser mascarado
     * @return String CPF mascarado
     */
    public static String formataCPF(String strCPF) throws ParseException, ParseException {
        return CPF.formataCPF(strCPF);
    }

    /**
     * Formata RG e o retorna com m�scara adequada.
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
     * Completa a String com caracteres em branco no �n�cio
     * at� atingir tamanho.
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
            throw new Exception("String com tamanho inv�lido para convers�o.");
        }

        for (int i = 0; i < tamanho - paramAlfa.length(); i++) {
            retorno.append(" ");
        }

        return retorno.toString();
    }

    /**
     * Retorna String sem acentua��o e em caixa alta.
     * @param str String a ser formatada
     * @return
     */
    public static String padraoMainFrame(String str) {
        return tiraAcento(str.toUpperCase());
    }

    /**
     * Retorna a String informada como par�metro modificando sua primeira letra pra letra mai�scula.
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
     * Retorna a String informada como par�metro modificando sua primeira letra pra letra min�scula.
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
     * Remove acentua��o de uma String passada como par�metro.
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
        String ComAcento = "������������������";
        ComAcento += "����������������������������";
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
     * M�todo utilizado para remover a formata��o de Strings contendo n�meros.
     * Retira pontos, barras, tra�os e espa�os em branco.
     * @param s string a ser analisada.
     * @return apeas os n�meros da string informada.
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
     * M�todo utilizado para verificar se uma determinada String � composta sempre
     * do mesmo caracter.
     * Ex: "SSSSSS" - true
     *     "SSSXSS" - false
     * @param string a ser analisada.
     * @return true - caso a String seja composta sempre do mesmo caracter.
     *         false - caso contr�rio
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
     * Verifica se uma determinada string � n�o nula.
     * Verifica se a String � diferente de null e diferente de ""
     * @param s String a verificar
     * @return bool
     * @author gabriel.ortiz
     */
    public static boolean stringNotNull(String s) {
        return (s != null && !"".equals(s.trim()));
    }

    /**
     * Adiciona caracter especificado na posi��o indicada (inicio/fim) da String.
     * @param String : param (string original)
     * @param char : caractere (caractere a ser inserido na string)
     * @param int : quantidade (de caracteres a serem inseridos na posi��o
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
            case 0: { // in�cio
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
     * @param int : quantidade (de caracteres a serem inseridos na posi��o
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
     * @param int : quantidade (de caracteres a serem inseridos na posi��o
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
     * @param int : quantidade (de caracteres a serem inseridos na posi��o
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
     * @param int : quantidade (de caracteres a serem inseridos na posi��o
     * @return String (string modificada)
     * @author Leslie
     * @since 05/06/2007
     */
    public static String addCharFinalStrTrim(String param, char caractere, int quantidade) {
        return addCharStrPos(param, caractere, quantidade, 1, true);
    }

    /**
     * Remove caracter especificado na posi��o indicada (inicio/fim) da String.
     * @param String : param (string original)
     * @param char : caractere (caractere a ser inserido na string)
     * @param int : quantidade (de caracteres a serem removidos da posi��o -1=todos, n>0-> n�mero de remo��es)
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
                case 0: { // in�cio
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
                        default: // somente um n�mero x de caracteres
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
                        default: // um n�mero definido de caracteres
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
                default: // nem in�cio nem fim...
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
            throw new Exception("ERRO! String de par�metro n�o pode ser nula.");
        }
    }// final m�todo

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
     * @param int : quantidade (de caracteres a serem removidos da posi��o (-1 => todos))
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
     * @param int : quantidade (de caracteres a serem removidos da posi��o)
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
     * @param int : quantidade (de caracteres a serem removidos da posi��o (-1 => todos))
     * @return String (string modificada)
     * @author Leslie
     * @throws Exception
     * @since 05/06/2007
     */
    public static String delNumCharFinalStr(String param, char caractere, int quantidade) throws Exception {
        return delCharStrPos(param, caractere, quantidade, 1, false);
    }

    /**
     * Remove caracter especificado n vezes do inicio da String fazendo trim ap�s a remo��o.
     * @param String : param (string original)
     * @param char : caractere (caractere a ser removido da string)
     * @param int : quantidade (de caracteres a serem removidos da posi��o (-1 => todos))
     * @return String (string modificada)
     * @author Leslie
     * @throws Exception
     * @since 05/06/2007
     */
    public static String delCharInicioStrTrim(String param, char caractere, int quantidade) throws Exception {
        return delCharStrPos(param, caractere, quantidade, 0, true);
    }

    /**
     * Remove caracter especificado n vezes no final da String fazendo trim ap�s remo��o.
     * @param String : param (string original)
     * @param char : caractere (caractere a ser inserido na string)
     * @param int : quantidade (de caracteres a serem removidos da posi��o (-1 => todos))
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
                + "<P>. No ano de 2009:<BR>- realiza��o de reuni�es com t�cnicos da SEC e da FLEM para detalhamento do escopo e "
                + "alinhamento de conceitos sobre o Programa;<BR>- planejamento dos processos de recrutamento e sele��o da equipe de profissionais que compor�o a Unidade Gestora Central e dos P�los;<BR>- montagem, na FLEM, de espa�o f�sico, com toda a infraestrutura necess�ria � instala��o e funcionamento da Unidade Gestora Central;<BR>- pesquisa e an�lise dos documentos t�cnicos e legais que d�o base ao Programa TRILHA e PROJOVEM Urbano, tais como: Proposta T�cnica, Plano de Trabalho, Termo de Refer�ncia e M�dulos/Guia de Estudo; Projeto de Capacita��o em Forma��o Cidad� para Jovens do Programa TRILHA e PROJOVEM Urbano, "
                + "Projeto Pedag�gico Integrado - PPI, Leis, Decretos e Resolu��es. </P>"
                + "<P>. No ano de 2010:</P>"
                + "<P>6) Equipe do N�cleo de Solu��es Educacionais ? NSE/FLEM:</P>"
                + "<P>A��es Operacionais e Administrativas:<BR>- realiza��o do Relat�rio Mensal das atividades da coordena��o e "
                + "equipe t�cnica;<BR>- consolida��o dos Relat�rios de Acompanhamento do Programa sob a responsabilidade da FLEM;"
                + "<BR>- realiza��o de reuni�es de alinhamento com a �rea administrativo-financeira da FLEM;<BR>- acompanhamento "
                + "das atividades do Programa, nos munic�pios dos P�los;<BR>- levantamento, an�lise e elabora��o de Editais;"
                + "<BR>- emiss�o de Solicita��o de Viagem ? SDV para cada profissional em deslocamento e acompanhamento para as "
                + "devidas presta��es de contas;<BR>- realiza��o de reserva de espa�o, disponibiliza��o de hotel, hospedagem e "
                + "refei��es;<BR>- ressarcimentos de despesas de locomo��o para as Equipes dos P�los e<BR>de passagens "
                + "a�reas para alguns consultores;<BR>- fornecimento de t�xis para consultores da Equipe Estadual;"
                + "<BR>- realiza��o de apoio operacional ao Evento de Constru��o e Aprimoramento do Projeto Pedag�gico do Programa TRILHA; <BR>"
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
