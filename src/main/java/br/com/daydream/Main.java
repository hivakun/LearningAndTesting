package br.com.daydream;

import br.com.daydream.config.ConfigurationLoader;
import br.com.daydream.model.User;
import ch.qos.logback.core.joran.spi.JoranException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * Hello world!
 */
public class Main
{
    private static final Logger LOGGER = LoggerFactory.getLogger(Main.class);

    public static void main( String[] args )
    {
        Application app = startSpring();
        app.start();
    }

    // Inicia o contexto da aplicação
    private static Application startSpring() {

        Application app = null;

        try {
            ConfigurationLoader.getInstance().start();
            AbstractApplicationContext context = new ClassPathXmlApplicationContext("META-INF/spring-config.xml");
            context.registerShutdownHook();

            app = (Application) context.getBean("applicationBean");

        } catch (IOException ex) {
            LOGGER.error("As configurações não foram carregadas. A aplicação não pode ser iniciada.", ex);
        } catch (JoranException ex) {
            LOGGER.error("As configurações não foram carregadas. A aplicação não pode ser iniciada.", ex);
        }

        return app;
    }
}
