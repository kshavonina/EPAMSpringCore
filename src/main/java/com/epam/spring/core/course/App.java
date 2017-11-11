package com.epam.spring.core.course;

import com.epam.spring.core.course.bean.Client;
import com.epam.spring.core.course.bean.Event;
import com.epam.spring.core.course.bean.EventType;
import com.epam.spring.core.course.loggers.EventLogger;
import com.epam.spring.core.course.spring.AppConfig;
import com.epam.spring.core.course.spring.LoggerConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class App {
    @Autowired
    private Client client;

    //@Resource(name = "defaultLogger")
    @Value("#{T(com.epam.spring.core.course.bean.Event).isDay() ?" +
            " fileEventLogger : consoleEventLogger}")
    private EventLogger defaultLogger;

    @Resource(name = "loggerMap")
    private Map<EventType, EventLogger> loggers;

    public App() {

    }

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggersMap) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggersMap;
    }

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(AppConfig.class, LoggerConfig.class);
        ctx.scan("com.epam.spring.core.course");
        ctx.refresh();

        App app = (App) ctx.getBean("app");

        Client client = (Client) ctx.getBean(Client.class);
        System.out.println("Client says: " + client.getGreeting());

        Event event = ctx.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "Some event for 1");

        event = ctx.getBean(Event.class);
        app.logEvent(EventType.ERROR, event, "Some event for 2");

        event = ctx.getBean(Event.class);
        app.logEvent(null, event, "Some event for 3");

        ctx.close();
    }

    void logEvent(EventType eventType, Event event, String msg) {
        String message = msg.replaceAll(String.valueOf(client.getId()), client.getFullName());
        event.setMsg(message);

        EventLogger logger = loggers.get(eventType);

        if (logger == null) {
            logger = defaultLogger;
        }

        logger.logEvent(event);
    }
}
