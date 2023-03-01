package com.example.demo.model;

import java.time.LocalDate;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;


@Entity
public class Timestamp {
    @Id
    @GeneratedValue
    private Long id;
    private Long hours;
    private Long minutes;
    private Long seconds;
    private Long milliseconds;
    private String description;
    private LocalDate date;

    @Transient
    private float timeInHours;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    
    public Timestamp() {
    }

    
    public Timestamp(Long id, Long hours, Long minutes, Long seconds, Long milliseconds, String description,
            LocalDate date, Category category) {
        this.id = id;
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = milliseconds;
        this.description = description;
        this.date = date;
        this.category = category;
    }

    public Timestamp(Long hours, Long minutes, Long seconds, Long milliseconds, String description, LocalDate date, Category category) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
        this.milliseconds = milliseconds;
        this.description = description;
        this.date = date;
        this.category = category;
    }


    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public Category getCategory() {
        return category;
    }


    public void setCategory(Category category) {
        this.category = category;
    }


    public Long getHours() {
        return hours;
    }

    public void setHours(Long hours) {
        this.hours = hours;
    }

    public Long getMinutes() {
        return minutes;
    }

    public void setMinutes(Long minutes) {
        this.minutes = minutes;
    }

    public Long getSeconds() {
        return seconds;
    }

    public void setSeconds(Long seconds) {
        this.seconds = seconds;
    }

    public Long getMilliseconds() {
        return milliseconds;
    }

    public void setMilliseconds(Long miliseconds) {
        this.milliseconds = miliseconds;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getTime() {
        String res = "";

        if (hours == null) {
            hours = 0L;
        }
        if (minutes == null) {
            minutes = 0L;
        }
        if (seconds == null) {
            seconds = 0L;
        }
        if (milliseconds == null) {
            milliseconds = 0L;
        }


        if (hours < 10) {
            res += "0" + hours;
        } else {
            res += hours;
        }
        res += ":";

        if (minutes < 10) {
            res += "0" + minutes;
        } else {
            res += minutes;
        }
        res += ":";

        if (seconds < 10) {
            res += "0" + seconds;
        } else {
            res += seconds;
        }

        return res;
    }


    @Override
    public String toString() {
        return "Timestamp [id=" + id + ", hours=" + hours + ", minutes=" + minutes + ", seconds=" + seconds
                + ", milliseconds=" + milliseconds + ", description=" + description + ", date=" + date
                + ", timeInHours=" + timeInHours + ", category=" + category + "]";
    }


    public float getTimeInHours() {
        float result = (float) hours;

        if (minutes != 0) result +=(float) minutes/60;
        if (seconds != 0) result +=(float) seconds/3600;
        System.out.println(result);
        return result;

    }


    public void setTimeInHours(float timeInHours) {
        this.timeInHours = timeInHours;
    }

}
