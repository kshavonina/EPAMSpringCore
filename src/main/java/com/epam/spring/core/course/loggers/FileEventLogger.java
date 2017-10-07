package com.epam.spring.core.course.loggers;

import com.epam.spring.core.course.bean.Event;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

public class FileEventLogger implements EventLogger {
    String fileName;
    File file;

    public FileEventLogger(String fileName) {
        this.fileName = fileName;
    }

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
            FileUtils.writeStringToFile(file, event.toString(), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
