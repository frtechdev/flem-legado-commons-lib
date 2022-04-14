package br.org.flem.fwe.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author mccosta
 */
public class Calendario {
    private Calendar calendar;

    public Calendario() {
        calendar = GregorianCalendar.getInstance();
    }

    public int calcIdade(Date dataNasc){
       Date hoje = new Date();

       calendar.setTime(hoje);
       int day1 = calendar.get(Calendar.DAY_OF_YEAR);
       int ano1 = calendar.get(Calendar.YEAR);

       calendar.setTime(dataNasc);
       int day2 = calendar.get(Calendar.DAY_OF_YEAR);
       int ano2 = calendar.get(Calendar.YEAR);

       int nAno = ano1 - ano2;

       if(day1 < day2)
          nAno--; //Ainda não completou aniversario esse ano.

       return nAno;
    }

    public float calcAnos(Date dataNasc){
       calendar.setTime(new Date());

       long maxDaysOfYear = calendar.getMaximum(GregorianCalendar.DAY_OF_YEAR);

       int day1 = calendar.get(Calendar.DAY_OF_YEAR);
       int ano1 = calendar.get(Calendar.YEAR);

       calendar.setTime(dataNasc);
       int day2 = calendar.get(Calendar.DAY_OF_YEAR);
       int ano2 = calendar.get(Calendar.YEAR);

       float nAno = (float)ano1 - (float)ano2;

       return nAno+((day1-day2)/(float)maxDaysOfYear);
    }

}