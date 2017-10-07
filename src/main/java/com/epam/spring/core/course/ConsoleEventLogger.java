package com.epam.spring.core.course;

public class ConsoleEventLogger implements EventLogger {
    public ConsoleEventLogger() {

    }

    public void logEvent(Event event) {
        System.out.println(event);
    }
}
