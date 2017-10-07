package com.epam.spring.core.course.loggers;

import com.epam.spring.core.course.bean.Event;

import java.util.Collection;

public class CombinedEventLogger implements EventLogger {
    Collection<EventLogger> loggers;

    public CombinedEventLogger(Collection<EventLogger> loggers) {
        this.loggers = loggers;
    }

    public void logEvent(Event event) {
        for (EventLogger eventLogger : loggers) {
            eventLogger.logEvent(event);
        }
    }
}
