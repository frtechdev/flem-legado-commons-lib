package br.org.flem.fwe.web.converter;

import java.math.BigDecimal;
import java.util.Date;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.commons.beanutils.ConvertUtils;

public final class ConverterListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent event) {
        ConvertUtils.register(new StringToDate(), Date.class);
        ConvertUtils.register(new StringToInteger(), Integer.class);
        ConvertUtils.register(new StringToBigDecimal(), BigDecimal.class);
    }

    public void contextDestroyed(ServletContextEvent sce) {
         ConvertUtils.deregister(Date.class);
         ConvertUtils.deregister(Integer.class);
         ConvertUtils.deregister(BigDecimal.class);
    }

}
