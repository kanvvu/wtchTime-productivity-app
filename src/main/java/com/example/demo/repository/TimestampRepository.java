package com.example.demo.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Timestamp;

@Repository
public interface TimestampRepository extends JpaRepository<Timestamp, Long>{

    List<Timestamp> findAllByOrderByIdDesc();
    List<Timestamp> findAllByOrderByDateDesc();
    @Query("select e from Timestamp e where date(e.date) =?1")
    List<Timestamp> findAllByDate(LocalDate date);

    @Query("select e from Timestamp e where year(e.date) =?1 and month(e.date) = ?2")
    List<Timestamp> getByYearAndMonth(int year, int month);
    
    @Query("select e from Timestamp e where year(e.date) =?1 and week(e.date) = ?2")
    List<Timestamp> getByYearAndWeek(int year, int week);

    @Query("select distinct year(p.date) from Timestamp p")
    List<Integer> findDistinctYears();

    @Query("select distinct week(p.date) from Timestamp p where year(p.date) = ?1 order by week(p.date) desc")
    List<Integer> findDistinctWeeksByYear(int year);
    
}
