package com.epam.spring.core.course.loggers;

import com.epam.spring.core.course.bean.Event;

public interface EventLogger {
    void logEvent(Event event);
}
