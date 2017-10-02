package com.epam.spring.core.course;

public class App {
    Client client;
    ConsoleEventLogger eventLogger;

    public static void main(String[] args) {

    }

    void logEvent(String msg) {
        this.eventLogger.logEvent(msg);
    }
}
