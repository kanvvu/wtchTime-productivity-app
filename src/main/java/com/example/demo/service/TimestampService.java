package com.example.demo.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Timestamp;
import com.example.demo.repository.TimestampRepository;

@Service
public class TimestampService {
    private final TimestampRepository timestampRepository;

    @Autowired
    public TimestampService(TimestampRepository timestampRepository) {
        this.timestampRepository = timestampRepository;
    }

    public void addNewTimestamp(Timestamp timestamp) {
        LocalDate now = LocalDate.now();
        if (timestamp.getDate() == null) {
            timestamp.setDate(now);
        }
        if (timestamp.getDescription() == "") {
            timestamp.setDescription("Empty");
        }
        timestampRepository.save(timestamp);

    }

    public List<Timestamp> getAllItemsByDate(LocalDate date) {
        List<Timestamp> result = timestampRepository.findAllByDate(date);
        Collections.reverse(result);
        return  result;
    }

    public List<Timestamp> getLatestNItems(int n) {
        List<Timestamp> result = new ArrayList<>();
        List<Timestamp> allItems = getAllItemsByDate(LocalDate.now());
        for (int i = 0; (i < n) && (i < allItems.size()); i++) {
            result.add(allItems.get(i));
        }

        return result;

    }

    public List<Timestamp> getAllItemsByDateOrder() {
        return timestampRepository.findAllByOrderByDateDesc();
    }

    public HashMap<String, Float> getHoursByDay(LocalDate date) {
        HashMap<String, Float> result = new HashMap<>();
        List<Timestamp> allItems = timestampRepository.findAllByDate(date);
        for (Timestamp time : allItems) {
            if (!result.containsKey(time.getCategory().getCategoryName())){
                result.put(time.getCategory().getCategoryName(), time.getTimeInHours());
            } else {
                float hoursToAdd = result.get(time.getCategory().getCategoryName());
                result.put(time.getCategory().getCategoryName(), hoursToAdd + time.getTimeInHours());
            }
        }
        return result;

    }

    public List<Float> getHoursByWeek(int year, int week) {
        List<Float> result = new ArrayList<>();
        List<Timestamp> weekItems = timestampRepository.getByYearAndWeek(year, week);

        String[] weekDays = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (int i = 0; i < weekDays.length ; i++) {
            result.add(0F);
        }

        for (Timestamp time : weekItems) {
                float hoursToAdd = (Float) result.get(time.getDate().getDayOfWeek().getValue() - 1);
                result.set(time.getDate().getDayOfWeek().getValue() - 1, hoursToAdd + time.getTimeInHours());
        }

        return result;
    }

    public List<Integer> getDistinctYears() {
        return timestampRepository.findDistinctYears();
    }

    public List<Integer> getDistinctWeeksByYear(int year) {
        return timestampRepository.findDistinctWeeksByYear(year);
    }

}
