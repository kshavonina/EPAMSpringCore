package com.epam.spring.core.course.loggers;

import com.epam.spring.core.course.bean.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class ConsoleEventLogger extends AbstractLogger {
    @Override
     public void logEvent(Event event) {
        System.out.println(event.toString());
    }

    @Value("Console logger")
    @Override
    public void setName(String name) {
        this.name = name;
    }
}
