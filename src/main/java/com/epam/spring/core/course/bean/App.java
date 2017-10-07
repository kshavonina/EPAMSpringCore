package com.epam.spring.core.course.bean;

import com.epam.spring.core.course.loggers.ConsoleEventLogger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    Client client;
    ConsoleEventLogger eventLogger;

    public App() {

    }

    public App(Client client) {
        this.client = client;
    }

    public App(ConsoleEventLogger eventLogger) {
        this.eventLogger = eventLogger;
    }

    public App(Client client, ConsoleEventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
        ConfigurableApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        App app = (App) ctx.getBean("app");

        //app.logEvent("Some event 1");
        //app.logEvent("Some event 2");

        ctx.close();
    }

    void logEvent(Event event) {
        String message = event.msg.replaceAll(String.valueOf(client.getId()), client.getFullName());
        this.eventLogger.logEvent(event);
    }
}
