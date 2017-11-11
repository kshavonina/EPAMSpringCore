package com.epam.spring.core.course.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.time.LocalTime;
import java.util.Date;

@Component
@Scope("prototype")
public class Event {
    private int id = (int) (Math.random() * 99) + 1;
    private String msg;

    @Autowired
    @Qualifier("newDate")
    private Date date;

    @Autowired
    private DateFormat df;

    public Event() {

    }

    public Event(Date date, DateFormat df) {
        this.date = date;
        this.df = df;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", msg='" + msg + '\'' +
                ", date=" + df.format(date) +
                '}';
    }

    public static boolean isDay() {
        LocalTime currentTime = LocalTime.now();
        int currentHour = currentTime.getHour();
        System.out.println(currentHour);

        return currentHour >= 8 && currentHour < 17;
    }

    public static void main(String[] args) {
        isDay();
    }
}
