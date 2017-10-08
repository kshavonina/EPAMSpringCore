package com.epam.spring.core.course;

import com.epam.spring.core.course.bean.Client;
import com.epam.spring.core.course.bean.Event;
import com.epam.spring.core.course.bean.EventType;
import com.epam.spring.core.course.loggers.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Map;

public class App {
    private Client client;
    private EventLogger defaultLogger;
    private Map<EventType, EventLogger> loggers;

    public App(Client client, EventLogger defaultLogger, Map<EventType, EventLogger> loggers) {
        this.client = client;
        this.defaultLogger = defaultLogger;
        this.loggers = loggers;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        Client client = ctx.getBean(Client.class);
        System.out.println("Client says: " + client.getGreeting());

        Event event = ctx.getBean(Event.class);
        app.logEvent(EventType.INFO, event, "Some event 1");

        event = ctx.getBean(Event.class);
        app.logEvent(EventType.ERROR, event, "Some event 2");

        event = ctx.getBean(Event.class);
        app.logEvent(null, event, "Some event 3");

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
