package com.epam.spring.core.course.util;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;

public class Monitor implements ApplicationListener<ApplicationEvent> {
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println(event.getClass().getSimpleName() + " > " + event.getSource().toString());
    }
}
