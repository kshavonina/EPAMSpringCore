package com.epam.spring.core.course.loggers;

import com.epam.spring.core.course.bean.Event;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.IOException;

@Component
public class FileEventLogger implements EventLogger {
    @Value("${events.file:target/events_log.txt}")
    private String fileName;
    private File file;

    public FileEventLogger() {

    }

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

    @PostConstruct
    public void init() throws IOException {
        this.file = new File(this.fileName);

        if (!file.exists()) {
            file.createNewFile();
        }

        if (!file.canWrite()) {
            throw new IllegalArgumentException("Can't write to file" + fileName);
        }
    }

    public void logEvent(Event event) {
        try {
            FileUtils.writeStringToFile(file, event.toString() + "\n", true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
