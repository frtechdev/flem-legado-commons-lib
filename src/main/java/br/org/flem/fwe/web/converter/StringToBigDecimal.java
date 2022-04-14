package br.org.flem.fwe.web.converter;

import java.beans.Beans;
import java.math.BigDecimal;
import org.apache.commons.beanutils.Converter;

/**
 *
 * @author essantos
 */
public class StringToBigDecimal implements Converter {

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

        param = param.replaceAll("\\.", "").replace(",", ".");

        return new BigDecimal(param);
    }
}
