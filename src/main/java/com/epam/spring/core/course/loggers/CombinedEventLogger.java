package com.epam.spring.core.course.loggers;

import com.epam.spring.core.course.bean.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

@Component
public class CombinedEventLogger extends AbstractLogger {
    @Resource(name = "combinedLoggers")
    private Collection<EventLogger> loggers;

    public CombinedEventLogger() {

    }

    public CombinedEventLogger(Collection<EventLogger> loggers) {
        this.loggers = loggers;
    }

    @Override
    public void logEvent(Event event) {
        for (EventLogger eventLogger : loggers) {
            eventLogger.logEvent(event);
        }
    }

    @Value("#{'Combined ' + combinedLoggers.![name].toString()}")
    @Override
    public void setName(String name) {
        this.name = name;
    }
}
