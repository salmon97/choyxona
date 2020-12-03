package com.example.choyxona.repository;

import com.example.choyxona.entity.Day;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DayRepository extends JpaRepository<Day, Long> {

    boolean existsByDayNumAndMonth_Id(Integer dayNum, Long month_id);

    Day getByDayNumAndMonth_Id(Integer dayNum, Long month_id);

    Day findByDayNumAndMonth_MonthNumAndMonth_Year(Integer dayNum, Integer month_monthNum, Integer month_year);

    @Query(value = "select * from day where month_id = :monthId", nativeQuery = true)
    List<Day> getDayByMonthId(long monthId);

    @Query(value = "select * from day where day_num >= :dayNum and month_id = :monthId ", nativeQuery = true)
    List<Day> getDayNumByMonthId(int dayNum, long monthId);
}
