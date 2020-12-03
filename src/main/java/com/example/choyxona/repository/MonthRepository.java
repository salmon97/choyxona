package com.example.choyxona.repository;

import com.example.choyxona.entity.Month;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MonthRepository extends JpaRepository<Month, Long> {

    @Query(value = "select * from month where month_num >= :monthNum and year >= :yearNum or year in (:yearNum+1) order by year asc limit 12", nativeQuery = true)
    List<Month> getByMonthNumAndYear(int monthNum, int yearNum);

    boolean existsByNameAndYear(String name, Integer year);
}