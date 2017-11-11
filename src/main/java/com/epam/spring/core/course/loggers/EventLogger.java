package com.epam.spring.core.course.loggers;

import com.epam.spring.core.course.bean.Event;
import org.springframework.beans.factory.annotation.Autowired;

public interface EventLogger {
    void logEvent(Event event);

    String getName();
}
