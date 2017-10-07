package com.epam.spring.core.course;

import com.epam.spring.core.course.bean.Client;
import com.epam.spring.core.course.bean.Event;
import com.epam.spring.core.course.loggers.ConsoleEventLogger;
import com.epam.spring.core.course.loggers.EventLogger;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    private Client client;
    private EventLogger eventLogger;

    public App(Client client, ConsoleEventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        Event event = ctx.getBean(Event.class);
        app.logEvent(event, "Some event 1");

        event = ctx.getBean(Event.class);
        app.logEvent(event, "Some event 2");

        ctx.close();
    }

    void logEvent(Event event, String msg) {
        String message = msg.replaceAll(String.valueOf(client.getId()), client.getFullName());
        event.setMsg(msg);
        this.eventLogger.logEvent(event);
    }
}
