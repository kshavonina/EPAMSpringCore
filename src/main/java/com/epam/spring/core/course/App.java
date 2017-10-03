package com.epam.spring.core.course;

public class App {
    Client client;
    ConsoleEventLogger eventLogger;

    public App() {

    }

    public App(Client client, ConsoleEventLogger eventLogger) {
        this.client = client;
        this.eventLogger = eventLogger;
    }

    public static void main(String[] args) {
        App app = new App();

        app.client = new Client(1, "Jon Snow");
        app.eventLogger = new ConsoleEventLogger();

        app.logEvent("Some event for user 1");
    }

    void logEvent(String msg) {
        String message = msg.replaceAll(String.valueOf(client.getId()), client.getFullName());
        this.eventLogger.logEvent(message);
    }
}
