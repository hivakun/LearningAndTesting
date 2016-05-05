package br.com.daydream.config;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.util.ContextInitializer;
import ch.qos.logback.core.joran.spi.JoranException;
import com.google.common.base.Strings;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Locale;
import java.util.Properties;

/**
 * @author Rivaldo
 */
public class ConfigurationLoader {

    private String applicationName;
    private String applicationLanguage;
    private String applicationCountry;
    private String dateFormat;
    private String logName;
    private String logLevel;
    private String logHistory;

    private ConfigurationLoader() {
        applicationLanguage = "pt";
        applicationCountry = "BR";
        dateFormat = "dd/MM/yyyy HH:mm:ss";
        logLevel = "debug";
        logHistory = "15";
    }

    public void start() throws IOException, JoranException {
        Properties properties = new Properties();

        properties.load(getClass().getClassLoader().getResourceAsStream("config.properties"));

        applicationName = properties.getProperty("application.name");

        if (!Strings.isNullOrEmpty(properties.getProperty("application.language"))) {
            applicationLanguage = properties.getProperty("application.language");
        }

        if (!Strings.isNullOrEmpty(properties.getProperty("application.country"))) {
            applicationCountry = properties.getProperty("application.country");
        }

        if (!Strings.isNullOrEmpty(properties.getProperty("date.format"))) {
            dateFormat = properties.getProperty("date.format");
        }

        if (!Strings.isNullOrEmpty(properties.getProperty("log.name"))) {
            logName = properties.getProperty("log.name");
        } else if (!Strings.isNullOrEmpty(applicationName)) {
            logName = applicationName.replaceAll("\\s+", "").toLowerCase();
        }

        if (!Strings.isNullOrEmpty(properties.getProperty("log.level"))) {
            logLevel = properties.getProperty("log.level");
        }

        if (!Strings.isNullOrEmpty(properties.getProperty("log.history"))) {
            logHistory = properties.getProperty("log.history");
        }

        validateConfiguration();
        setLocaleConfiguration();
        setLoggerConfiguration();
    }

    public String getApplicationName() {
        return applicationName;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    private void validateConfiguration() {
        if (Strings.isNullOrEmpty(applicationName)) {
            throw new RequiredArgumentException("O nome da aplicação não foi identificado. Analise o arquivo de configurações.");
        }
    }

    private void setLocaleConfiguration() {
        Locale.setDefault(new Locale(applicationLanguage, applicationCountry));
    }

    private void setLoggerConfiguration() throws JoranException {
        System.setProperty("log.name", logName);
        System.setProperty("log.level", logLevel);
        System.setProperty("log.history", logHistory);

        LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
        ContextInitializer ci = new ContextInitializer(lc);
        lc.reset();
        ci.autoConfig();
    }

    private static class BootstrapperHolder {
        static final ConfigurationLoader BOOTSTRAPPER = new ConfigurationLoader();
    }

    public static ConfigurationLoader getInstance() {
        return BootstrapperHolder.BOOTSTRAPPER;
    }
}
