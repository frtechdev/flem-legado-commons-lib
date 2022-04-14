package br.org.flem.fwe.web.converter;

import br.org.flem.fwe.util.Data;
import java.beans.Beans;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.beanutils.Converter;

/**
 *
 * @author essantos
 */
public class StringToDate implements Converter {

    public Object convert(Class type, Object value) {
        if ((value == null) || (value.toString().isEmpty())){
            return null;
        }

        String param = "";

        if (Beans.isInstanceOf(value, java.lang.String.class)) {
            param = (String) value;
        }
        else {
            return null;
        }

        try {
            if (param.length() == 10) {
                return Data.formataData(param, "dd/MM/yyyy");
            }
            else if (param.length() == 7) {
                return Data.formataData("01/" + param, "dd/MM/yyyy");
            }
        } catch (ParseException ex) {
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
            return null;
        }

        return null;
    }
}
