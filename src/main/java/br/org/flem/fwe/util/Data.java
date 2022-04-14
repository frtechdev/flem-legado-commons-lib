package br.org.flem.fwe.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.SimpleTimeZone;
import java.util.TimeZone;
import org.apache.commons.validator.GenericValidator;
import org.joda.time.MutableDateTime;

/**
 * Funcões para manipulação de data.
 * A maioria das operações necessárias e complicadas com
 * datas, como conversões, soma de dias, comparações, etc
 * estão presentes nessa classe.
 *
 * @author Grupo Framework - Componentes
 * @version 1.2, 19/01/2005
 * @since 1.0
 */
public abstract class Data {

    static int timeZone = -3;

    /**
     * Soma ou substrai uma quantidade de anos de uma determinada data.
     * @param qtdeAnos Anos que serão somados à data (números negativos para fazer substração)
     * @param data Data a partir da qual será feita a operação
     * @return Date Resultado da Operação
     */
    public static Date addAnos(int qtdeAnos, Date data) {
        if (data == null) {
            return null;
        }
        Calendar calendar = getCalendar(data);
        calendar.add(Calendar.YEAR, qtdeAnos);
        return calendar.getTime();
    }

    /**
     * Soma ou substrai uma quantidade de dias de uma determinada data.
     * @param qtdeDias Dias que serão somados à data (números negativos para fazer substração)
     * @param data Data a partir da qual será feita a operação
     * @return Date Resultado da Operação
     */
    public static Date addDias(int qtdeDias, Date data) {
        if (data == null) {
            return null;
        }
        Calendar calendar = getCalendar(data);
        calendar.add(Calendar.DATE, qtdeDias);
        return calendar.getTime();
    }

    /**
     * Soma ou substrai uma quantidade de meses de uma determinada data.
     * @param qtdeMeses Meses que serão somados à data (números negativos para fazer substração)
     * @param data Data a partir da qual será feita a operação
     * @return Date Resultado da Operação
     */
    public static Date addMeses(int qtdeMeses, Date data) {
        if (data == null) {
            return null;
        }
        Calendar calendar = getCalendar(data);
        calendar.add(Calendar.MONTH, qtdeMeses);
        return calendar.getTime();
    }

    /**
     * Dada uma data de nascimento, calcula a idade.
     * @param Date data de nascimento
     * @return Integer idade
     */
    public static Integer calculaIdade(Date date) {
        int idade = -1;
        if (date != null) {
            GregorianCalendar calendarAtual = new GregorianCalendar();
            int anoAtual = calendarAtual.get(GregorianCalendar.YEAR);
            GregorianCalendar calendarParametro = new GregorianCalendar();
            calendarParametro.setTime(date);
            int anoParametro = calendarParametro.get(GregorianCalendar.YEAR);
            calendarParametro.set(GregorianCalendar.YEAR, anoAtual);
            //se mesma data no ano atual for antes de hoje
            if (calendarAtual.after(calendarParametro)) {
                // fez aniversário esse ano
                idade = anoAtual - anoParametro;
            } else {
                //ainda não fez aniversário esse ano
                idade = anoAtual - anoParametro - 1;
            }
        } else {
            return null;
        }
        return new Integer(idade);
    }

    /** Retorna o dia atual.
     * @return String data no formato dd/MM/yyyy
     */
    public static String dataAtual() {
        String ret = null;
        ret = formataData(new Date());
        return ret;
    }

    /**
     * Converte um Date para uma String.
     * @param data Data a ser formatada
     * @return String no formato dd/MM/yyyy
     * @throws Exception Caso a String esteja no formato errado
     */
    public static String formataData(Date data) {
        String strRetorno = null;
        if (data != null) {
            strRetorno = new SimpleDateFormat("dd/MM/yyyy").format(data);
        }
        return strRetorno;
    }

    /**
     * Converte uma String para um objeto Date. Caso a String seja vazia ou nula,
     * retorna null - para facilitar em casos onde formulários podem ter campos
     * de datas vazios.
     * @param data String no formato dd/MM/yyyy a ser formatada
     * @return Date Objeto Date ou null caso receba uma String vazia ou nula
     */
    public static Date formataData(String data) throws ParseException {
        Date date = null;
        if (!GenericValidator.isBlankOrNull(data) && GenericValidator.isDate(data, "dd/MM/yyyy", false)) {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(data);
        }
        return date;
    }

    /**
     * Converte um Date para uma String.
     * @param data Data a ser formatada
     * @param padrao Padrao da Data a ser formatada
     * @return String no formato escolhido
     * @throws Exception Caso a String esteja no formato errado
     */
    public static String formataData(Date data, String padrao) {
        String strRetorno = null;
        if (data != null) {
            strRetorno = new SimpleDateFormat(padrao).format(data);
        }
        return strRetorno;
    }

    /**
     * Converte uma String para um objeto Date. Caso a String seja vazia ou nula,
     * retorna null - para facilitar em casos onde formulários podem ter campos
     * de datas vazios.
     * @param data String no formato padrao a ser formatado
     * @param padrao Formato para converter a String em uma data
     * @return Date Objeto Date ou null caso receba uma String vazia ou nula
     */
    public static Date formataData(String data, String padrao) throws ParseException {
        Date date = null;
        if (!GenericValidator.isBlankOrNull(data) && GenericValidator.isDate(data, padrao, false)) {
            date = new SimpleDateFormat(padrao).parse(data);
        }
        return date;
    }

    /**
     * Converte um Date para uma String no formato dd/mm/aaaa HH:mm:ss
     * @param data Data a ser formatada
     * @return String
     */
    public static String formataDataHora(Date data) {
        String formato = "dd/MM/yyyy HH:mm:ss";
        String strRetorno = null;
        SimpleDateFormat formatter = new SimpleDateFormat(formato);
        if ((data != null)) {
            strRetorno = formatter.format(data);
        } else {
            strRetorno = null;
        }
        return strRetorno;
    }

    /**
     * Converte um Date para uma String no formato dd/mm/aaaa HH:mm
     * @param data Data a ser formatada
     * @return String
     */
    public static String formataDataHoraCurta(Date data) {
        String formato = "dd/MM/yyyy HH:mm";
        String strRetorno = null;
        SimpleDateFormat formatter = new SimpleDateFormat(formato);
        if ((data != null)) {
            strRetorno = formatter.format(data);
        } else {
            strRetorno = null;
        }
        return strRetorno;
    }

    /**
     * Converte um Date para uma String no formato HH:mm:ss
     * @param data Data a ser formatada
     * @return String
     */
    public static String formataHoraLonga(Date data) {
        String formato = "HH:mm:ss";
        String strRetorno = null;
        SimpleDateFormat formatter = new SimpleDateFormat(formato);
        if ((data != null)) {
            strRetorno = formatter.format(data);
        } else {
            strRetorno = null;
        }
        return strRetorno;
    }

    /**
     * Converte um Date para uma String no formato HH:mm
     * @param data Data a ser formatada
     * @return String
     */
    public static String formataHoraCurta(Date data) {
        String formato = "HH:mm";
        String strRetorno = null;
        SimpleDateFormat formatter = new SimpleDateFormat(formato);
        if ((data != null)) {
            strRetorno = formatter.format(data);
        } else {
            strRetorno = null;
        }
        return strRetorno;
    }

    /**
     * Formata data vinda no formato do SQLServer para o formato dd/MM/yyyy.
     * @param data Data vinda do SQLServer
     * @return
     */
    public static String formataDataVoltaSQL(String data) {
        if (data == null) {
            return null;
        }
        String dia = data.substring(8, 10);
        String mes = data.substring(5, 7);
        String ano = data.substring(0, 4);
        return dia + "/" + mes + "/" + ano;
    }

    /**
     * Recebe um objeto Date e retorna a data por extenso (ex: 01 de Janeiro de 2000).
     * @param date
     * @return string com a data
     */
    public static String formatExtenso(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat format = new SimpleDateFormat("dd 'de' MMMM 'de' yyyy");
        return format.format(date);
    }

    /**
     * Retorna um objeto Calendar com a data fornecida como parâmetro.
     * @param data Objeto Date
     * @return calendar Objeto Calendar com a Data fornecida
     */
    public static Calendar getCalendar(Date data) {
        if (data == null) {
            return null;
        }
        String[] ids = TimeZone.getAvailableIDs(timeZone * 60 * 60 * 1000);
        SimpleTimeZone pdt = new SimpleTimeZone(timeZone * 60 * 60 * 1000, ids[0]);
        Calendar calendar = new GregorianCalendar(pdt);
        calendar.setFirstDayOfWeek(Calendar.SUNDAY);
        calendar.setTime(data);
        return calendar;
    }

    /**
     * Verifica se a data passada como parâmetro é futuro.
     * @param data Objeto Date a ser verificado
     * @return boolean true se data for futuro
     */
    public static boolean isFuturo(Date data) {
        return (new Date().before(data));
    }

    /**
     * Verifica se a data passada como parâmetro é futuro.
     * @param data String no formato dd/MM/yyyy a ser verificada
     * @return boolean true se data for futuro
     * @throws Exception Caso a String esteja no formato errado
     */
    public static boolean isFuturo(String data) throws Exception {
        return new Date().before(formataData(data));
    }

    /**
     * Verifica se a data passada como parâmetro é passado.
     * @param data Objeto date a ser verificado
     * @return true se data for passado
     */
    public static boolean isPassado(Date data) {
        return new Date().after(data);
    }

    /**
     * Verifia se a data passada como parâmetro é passado
     * @param data String no formato dd/MM/yyyy a ser verificada
     * @return true se data for passado
     * @throws Exception Caso a String esteja no formato errado
     */
    public static boolean isPassado(String data) throws Exception {
        return new Date().after(formataData(data));
    }

    /**
     * Recebe uma data formato dd/MM/yyyy e a retorna no formato yyyy-mm-dd.
     * @param String strData
     * @return String
     * @throws Exception caso a String esteja em formato incorreto
     */
    public static String retornaDataBD(String strData) throws Exception {
        if (strData == null || strData.equals("")) {
            return "";
        }
        String date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            date = formatter.format(Data.formataData(strData));
        } catch (ParseException e) {
            throw e;
        }
        return date;
    }

    /**
     * Tranforma uma String do formato dd/MM/yyyy para formato yyyymmdd.
     * @param String com data a ser transformada
     * @return String Data no formato yyyymmdd
     * @throws Exception caso a String esteja em formato incorreto
     */
    public static String retornaDataInversa(String strData) throws Exception {
        if (strData == null || strData.equals("")) {
            return "";
        }

        String date = null;
        try {
            DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            date = formatter.format(Data.formataData(strData));
        } catch (ParseException e) {
            throw e;
        }
        return date;
    }

    /**
     * Compara duas datas, retornando a diferença em dias entre elas (datamaior - datamenor).
     *  Caso a datamaior seja menor que a datamenor, retorna a diferença em valores
     * negativos.
     * @param dataMenor Data a ser subtraída da dataMaior
     * @param dataMaior Data maior
     * @return null caso algum parâmetro seja nulo ou Integer com a diferença
     * das datas
     */
    public static Integer horaDiff(Date dataMenor, Date dataMaior) throws ParseException {
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        if (dataMenor == null || dataMaior == null) {
            return null;
        }

        Date d1 = simpleDate.parse("01/01/2000 " + Data.formataHoraCurta(dataMenor) + ":00");
        Date d2 = simpleDate.parse("01/01/2000 " + Data.formataHoraCurta(dataMaior) + ":00");

        return new Integer(new Long(((d2.getTime() - d1.getTime()) / 1000) / 3600).intValue());
    }

    /**
     * Compara duas datas, retornando a diferença em dias entre elas (datamaior - datamenor).
     *  Caso a datamaior seja menor que a datamenor, retorna a diferença em valores
     * negativos.
     * @param dataMenor Data a ser subtraída da dataMaior
     * @param dataMaior Data maior
     * @return null caso algum parâmetro seja nulo ou Integer com a diferença
     * das datas
     */
    public static Integer dataDiff(Date dataMenor, Date dataMaior) {
        if (dataMenor == null || dataMaior == null) {
            return null;
        }
        return new Integer(new Long((dataMaior.getTime() - dataMenor.getTime()) / (24 * 60 * 60 * 1000)).intValue());
    }

    /**
     * Compara duas datas, retornando a diferença em meses entre elas (datamaior - datamenor).
     * Considera somente os valores de ano e mês para o cálculo (exemplo: diferença entre 31/12/2005 e 01/01/2006 é um mês).
     *  Caso a datamaior data seja menor que a datamenor, retorna a diferença em valores
     * negativos.
     * @param dataMenor Data a ser subtraída da dataMaior
     * @param dataMaior Data maior
     * @return null caso algum parâmetro seja nulo ou Integer com a diferença
     * das datas
     */
    public static Integer mesDiff(Date dataMenor, Date dataMaior) {
        if (dataMenor == null || dataMaior == null) {
            return null;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(dataMaior);
        int anoMaior = cal.get(Calendar.YEAR);
        int mesMaior = cal.get(Calendar.MONTH) + 1;

        cal = Calendar.getInstance();
        cal.setTime(dataMenor);
        int anoMenor = cal.get(Calendar.YEAR);
        int mesMenor = cal.get(Calendar.MONTH) + 1;

        int mesesDiff = 0;

        if (anoMaior != anoMenor) {
            int anosEntreMaiorEMenor = anoMaior - anoMenor;
            // subtrai um (ou soma, caso ano menor > ano maior) por não considerar o maior ano (nem o menor).
            // Neles, é feita a contagem de meses por não ser o ano inteiro.
            anosEntreMaiorEMenor = (anoMaior > anoMenor) ? anosEntreMaiorEMenor - 1 : anosEntreMaiorEMenor + 1;

            mesesDiff = anosEntreMaiorEMenor * 12;
            mesesDiff = (anoMaior > anoMenor) ? mesesDiff + 12 - mesMenor : mesesDiff - (12 - mesMaior);
            mesesDiff = (anoMaior > anoMenor) ? mesesDiff + mesMaior : mesesDiff - mesMenor;

        } else if (mesMaior != mesMenor) {
            mesesDiff = mesMaior - mesMenor;
        }
        return new Integer(mesesDiff);
    }

    /**
     * Compara duas datas, retornando a diferença em anos entre elas (datamaior - datamenor).
     * Considera somente o valor do ano para o cálculo (exemplo: diferença entre 31/12/2005 e 01/01/2006 é um ano).
     *  Caso a datamaior seja menor que a datamenor, retorna a diferença em valores
     * negativos.
     * @param dataMenor Data a ser subtraída da dataMaior
     * @param dataMaior Data maior
     * @return null caso algum parâmetro seja nulo ou Integer com a diferença
     * das datas
     */
    public static Integer anoDiff(Date dataMenor, Date dataMaior) {
        if (dataMenor == null || dataMaior == null) {
            return null;
        }
        Calendar calMaior = Calendar.getInstance();
        calMaior.setTime(dataMaior);
        int anoMaior = calMaior.get(Calendar.YEAR);

        Calendar calMenor = Calendar.getInstance();
        calMenor.setTime(dataMenor);
        int anoMenor = calMenor.get(Calendar.YEAR);

        return new Integer(anoMaior - anoMenor);
    }
    
    /**
     * Compara duas datas, retornando a diferença em anos entre elas em casas decimais
     * Calculado em cima dos meses, sendo necessário aplicar um fator de correção nos dias do mês
     * @param dataMenor Data a ser subtraída da dataMaior
     * @param dataMaior Data maior
     * @return 
     */
    public static Float anoDiffDecimal(Date dataMenor, Date dataMaior) {
        Float anoDiff =  Data.mesDiff(dataMenor, dataMaior)/12f;
        Calendar calDataMaior = Calendar.getInstance();
        calDataMaior.setTime(dataMaior);
        
        Calendar calDataMenor = Calendar.getInstance();
        calDataMenor.setTime(dataMenor);

        float diasNoMesdiff = calDataMaior.get(Calendar.DATE) - calDataMenor.get(Calendar.DATE);
        float  fator = diasNoMesdiff / calDataMaior.getMaximum(Calendar.DAY_OF_YEAR);//pela quantidade de dias do "ultimo ano"
        return anoDiff + fator;
    }

    /**
     * Valida se determinada idade é menor, maior ou igual à idade limite.
     * A data de referência pode ser considerada a data de hoje ou uma data específica
     * @author Leslie H. Watter <leslieh@celepar.pr.gov.br>
     * @param Date dataNascimento
     * @param int limite - Data limite (p.ex. 18 anos, 21 anos, 0 = sem limite)
     * @param int operacao - operação a ser executada ( x<0- menor, x=0 - igual, x>0 - maior)
     * @param Date dataReferencia - Data a ser comparada
     * @return bool true or false
     * @throws Exception, ApplicationException
     * @throws ApplicationException
     * @throws Exception
     * @since 04/06/2007
     */
    public static boolean validarIdadeReferencia(Date dataNascimento, int limite_anos, int operacao, Date dataReferencia) throws Exception {

        Date dnasc = dataNascimento;
        int x;
        Date dataRef;
        boolean retorno;

        if (limite_anos >= 0) {

            Calendar dataCalendario = GregorianCalendar.getInstance();
            dataCalendario.setTime(dataReferencia);

            dataCalendario.add(GregorianCalendar.YEAR, -limite_anos);
            dataRef = dataCalendario.getTime();

            //		x recebe
            //		x<0 -> data dnasc menor
            //		x=0 -> datas iguais
            //		x>0 -> data dnasc maior

            x = dnasc.compareTo(dataRef);

            switch (operacao) {
                case -1: // menor
                {
                    if (x > 0) {
                        retorno = true;
                    } else {
                        retorno = false;
                    }

                    break;
                }
                case 0: // igual
                {
                    if (x == 0) {
                        retorno = true;
                    } else {
                        retorno = false;
                    }

                    break;
                }
                case 1: // maior
                {
                    if (x < 0) {
                        retorno = true;
                    } else {
                        retorno = false;
                    }

                    break;
                }
                default:
                    retorno = false;
            }
        } else {
            throw new Exception("Erro: Idade Limite deve ser maior ou igual a ZERO");
        }
        return retorno;
    }

    /**
     * Valida se determinada idade é menor, maior ou igual à idade limite.
     * A data de referência pode ser considerada a data de hoje ou uma data específica
     * @author Leslie H. Watter <leslieh@celepar.pr.gov.br>
     * @param dataNascimento
     * @param limite_anos - Data limite (p.ex. 18 anos, 21 anos, 0 = sem limite)
     * @param operacao - operação a ser executada ( -1 -> menor, 0 -> igual, +1 -> maior)
     * @return bool true or false
     * @throws ApplicationException
     * @throws Exception
     * @since 04/06/2007
     */
    public static boolean validarIdade(Date dataNascimento, int limite_anos, int operacao) throws Exception {

        boolean retorno;

        Calendar dataCalendario = GregorianCalendar.getInstance();
        Date dataHoje = dataCalendario.getTime();

        if (limite_anos >= 0) {
            retorno = validarIdadeReferencia(dataNascimento, limite_anos, operacao, dataHoje);
        } else {
            throw new Exception("Erro: Idade Limite deve ser maior ou igual a ZERO");
        }
        return retorno;
    }

    /**
     * Valida a data de nascimento. Verifica se a idade informada na data de nascimento é menor que a idade limite.
     * @author Leslie H. Watter <leslieh@celepar.pr.gov.br>
     * @param Date dataNascimento
     * @param int limite_anos - Data limite (p.ex. 18 anos, 21 anos)
     * @return bool true or false
     * @throws ApplicationException
     * @throws Exception
     * @since 04/06/2007
     */
    public static boolean validarIdadeMenor(Date dataNascimento, int limite_anos) throws Exception {

        boolean retorno;

        Calendar dataCalendario = GregorianCalendar.getInstance();
        Date dataHoje = dataCalendario.getTime();

        if (limite_anos >= 0) {
            retorno = validarIdadeReferencia(dataNascimento, limite_anos, -1, dataHoje);
        } else {
            throw new Exception("Erro: Idade Limite deve ser maior ou igual a ZERO");
        }
        return retorno;
    }

    /**
     * Valida a data de nascimento. Verifica se a idade informada na data de nascimento é MAIOR que a idade limite.
     * @author Leslie H. Watter <leslieh@celepar.pr.gov.br>
     * @param Date dataNascimento
     * @param int limite_anos - Data limite (p.ex. 18 anos, 21 anos)
     * @return bool true or false
     * @throws ApplicationException
     * @throws Exception
     * @since 04/06/2007
     */
    public static boolean validarIdadeMaior(Date dataNascimento, int limite_anos) throws Exception {

        boolean retorno;

        Calendar dataCalendario = GregorianCalendar.getInstance();
        Date dataHoje = dataCalendario.getTime();

        if (limite_anos >= 0) {
            retorno = validarIdadeReferencia(dataNascimento, limite_anos, 1, dataHoje);
        } else {
            throw new Exception("Erro: Idade Limite deve ser maior ou igual a ZERO");
        }
        return retorno;
    }

    /**
     * Trazer o texto do dia ou mês indicado  de acordo com o número.
     * 1 = Domingo .. 7 = Sábado
     * 1 = Janeiro .. 12 = Dezembro
     * @author Leslie
     * @since 05/06/2007
     * @param int num (número do dia ou mês) (0 = Domingo .. 6 = Sábado | 0 = Janeiro .. 11 = Dezembro)
     * @param int diaMes (0 - dia, 1 - mes)
     * @param int abreviado (0=abreviado - 'Dom', 1=Extenso - 'Domingo')
     * @return String
     * @throws ApplicationException
     * @throws Exception
     */
    public static String retornarTextoData(int num, int diaMes, int abreviado) throws Exception {

        String mesAbrev[] = {"Jan", "Fev", "Mar", "Abr", "Mai", "Jun", "Jul", "Ago", "Set", "Out", "Nov", "Dez"};
        String mesExtenso[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho", "Julho", "Agosto", "Setembro",
            "Outubro", "Novembro", "Dezembro"
        };
        String diaAbrev[] = {"Dom", "Seg", "Ter", "Qua", "Qui", "Sex", "Sáb"};
        String diaExtenso[] = {"Domingo", "Segunda", "Terça", "Quarta", "Quinta", "Sexta", "Sábado"};



        switch (diaMes) {
            case 0: { // dia
                if (num >= 1 && num <= 7) {
                    if (abreviado == 0) {
                        return diaAbrev[num - 1];
                    } else {
                        return diaExtenso[num - 1];
                    }
                } else {
                    throw new Exception("Erro: Valor para DIA deve ser entre 1(Dom) e 7(Sab) inclusive");
                }
            }
            case 1: { // mes
                if (num >= 1 && num <= 12) {
                    if (abreviado == 0) {
                        return mesAbrev[num - 1];
                    } else {
                        return mesExtenso[num - 1];
                    }
                } else {
                    throw new Exception("Erro: Valor para MES deve ser entre 1 (Jan) e 12 (Dez) inclusive");
                }
            }
            default:
                throw new Exception("Erro: Valor fora da faixa para dia/mes");
        }

    } // final do método

    /**
     * Trazer dia da semana de acordo com o número.
     * 1 = Domingo .. 7 = Sábado
     * @author Leslie
     * @since 05/06/2007
     * @param int numDia
     * @return String
     * @throws ApplicationException
     * @throws Exception
     */
    public static String retornarDiaSemana(int numDia) throws Exception {
        return retornarTextoData(numDia, 0, 1);
    }

    /**
     * Trazer dia da semana de acordo com o número.
     * 1 = Domingo .. 7 = Sábado
     * @author Leslie
     * @since 05/06/2007
     * @param int numDia, int abreviado (0=abreviado - 'Dom', 1=Extenso - 'Domingo')
     * @return String
     * @throws ApplicationException
     * @throws Exception
     */
    public static String retornarTextoDiaSemana(int numDia, int abreviado) throws Exception {
        return retornarTextoData(numDia, 0, abreviado);
    } // final do método

    /**
     * Trazer texto Mês  de acordo com o número.
     * 1 = Janeiro .. 12 = Dezembro
     * @author Leslie
     * @since 05/06/2007
     * @param int numDia
     * @return String
     * @throws ApplicationException
     * @throws Exception
     */
    public static String retornarMes(int numMes) throws Exception {
        return retornarTextoData(numMes, 1, 1);
    } // final do método

    /**
     * Trazer texto Mês  de acordo com o número.
     * 1 = Janeiro .. 12 = Dezembro
     * @author Leslie
     * @since 05/06/2007
     * @param int numDia, int abreviado (0=abreviado - 'Dom', 1=Extenso - 'Domingo')
     * @return String
     * @throws ApplicationException
     * @throws Exception
     */
    public static String retornarTextoMes(int numMes, int abreviado) throws Exception {
        return retornarTextoData(numMes, 1, abreviado);
    } // final do método

    /**
     * Converte um objeto java.util.Date no formato numérico do GEM
     * @return
     */
    public static Integer converteDataParaInteiro(Date data) {
        return Integer.valueOf(new SimpleDateFormat("yyyyMMdd").format(data));
    }

    public static Date converteNumeroParaData(String data) throws ParseException {
        return new SimpleDateFormat("yyyyMMdd").parse(data);
    }

    public static boolean dentroDoPerido(Date data, Date inicio, Date fim) {
        Integer numdata = Data.converteDataParaInteiro(data);
        Integer numinicio = Data.converteDataParaInteiro(inicio);
        Integer numfim = Data.converteDataParaInteiro(fim);
        boolean teste = false;
        if ((numdata >= numinicio) && (numdata <= numfim)) {
            teste = true;
        }
        return teste;
    }

    public static Date obterPrimeiroDiaMes(Date data) throws ParseException {
        MutableDateTime jodaData = new MutableDateTime(data);
        jodaData.setDayOfMonth(1);
        return jodaData.toDate();
    }

    public static Date obterUltimoDiaMes(Date data) throws ParseException {
        MutableDateTime jodaData = new MutableDateTime(data);
        jodaData.setDayOfMonth(jodaData.dayOfMonth().getMaximumValue());
        return jodaData.toDate();
    }
    
    public static Date obterUltimoDiaMesInformado(int mes) throws ParseException{
            
            MutableDateTime jodaData =  new MutableDateTime();
            jodaData.setMonthOfYear(mes);
            jodaData.setDayOfMonth(jodaData.dayOfMonth().getMaximumValue());
        return jodaData.toDate();
    }
    
    public static Date obterPrimeiroDiaMesInformado (int mes)throws ParseException{
        Calendar cal1 = getCalendar(new Date());
        cal1.set(Calendar.DATE,1);
        cal1.set(Calendar.MONTH, mes-1);
        return cal1.getTime();
    }

    public static List<String> obterAnosAteAtual(int ano) throws ParseException {
        return obterAnosAteAtual(formataData("01/01/" + ano));
    }

    public static List<String> obterAnosAteAtual(Date data1) throws ParseException {
        if (data1 == null) {
            return null;
        }
        return obterAnos(data1, new Date());
    }

    public static List<String> obterAnos(Date data1, Date data2) throws ParseException {
        if (data1 == null || data2 == null) {
            return null;
        }
        Calendar cal1 = getCalendar(data1);
        Calendar cal2 = getCalendar(data2);
        List<String> anos = new ArrayList<String>();
        while (cal1.before(cal2)) {
            cal1.add(Calendar.YEAR, 1);
            anos.add(cal1.get(Calendar.YEAR) + "");
        }
        return anos;
    }

    public synchronized static Long obterTime(Date data) {
        return data.getTime();
    }

    public static long setarHora(Date data, int hora, int minuto) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.set(Calendar.HOUR_OF_DAY, hora);
        cal.set(Calendar.MINUTE, minuto);
        return cal.getTimeInMillis();
    }

    public static long setarHora(Date data, int hora, int minuto, int segundo, int milisegundo) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.set(Calendar.HOUR_OF_DAY, hora);
        cal.set(Calendar.MINUTE, minuto);
        cal.set(Calendar.SECOND, segundo);
        cal.set(Calendar.MILLISECOND, milisegundo);
        return cal.getTimeInMillis();
    }

    public static boolean validarFormato(String strData,String formato) {
        Date data = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(formato);
            data = sdf.parse(strData);
            if (!strData.equals(sdf.format(data))) {
                return false;
            }
            return true;
        } catch (ParseException ex) {
            return false;
        }
    }

}
