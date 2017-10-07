package com.epam.spring.core.course.loggers;

import com.epam.spring.core.course.bean.Event;

public class ConsoleEventLogger implements EventLogger {
    public ConsoleEventLogger() {

    }

    public void logEvent(Event event) {
        System.out.println(event);
    }
}
