package com.example.choyxona.service;

import com.example.choyxona.entity.Day;
import com.example.choyxona.entity.Month;
import com.example.choyxona.entity.TemplateMonth;
import com.example.choyxona.payload.ApiResponse;
import com.example.choyxona.payload.ReqMonth;
import com.example.choyxona.payload.ResMonth;
import com.example.choyxona.payload.ResPro_Client;
import com.example.choyxona.repository.*;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class MonthService {

    final
    MonthRepository monthRepository;

    final
    DayRepository dayRepository;

    final
    MonthTempRepository tempRepository;

    final
    ProductClientRepository productClientRepository;

    final
    ProductKgClientRepository productKgClientRepository;

    public MonthService(MonthRepository monthRepository, DayRepository dayRepository, MonthTempRepository tempRepository, ProductClientRepository productClientRepository, ProductKgClientRepository productKgClientRepository) {
        this.monthRepository = monthRepository;
        this.dayRepository = dayRepository;
        this.tempRepository = tempRepository;
        this.productClientRepository = productClientRepository;
        this.productKgClientRepository = productKgClientRepository;
    }


    public ApiResponse infoProClientMonth(int monthId) {
        List<ResPro_Client> prClientInfoMonth = productClientRepository.getPrClientInfoMonth(monthId);
        List<ResPro_Client> prKgClientInfoMonth = productKgClientRepository.getPrClientInfoMonth(monthId);
        Object[] objects = {prClientInfoMonth, prKgClientInfoMonth};
        return new ApiResponse("ok", true, objects);
    }

    public List<ResMonth> resMonths() {
        List<Month> all = monthRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
        List<ResMonth> resMonths = new ArrayList<>();
        all.forEach(month -> resMonths.add(new ResMonth(
                month.getId(), month.getName(), month.getNameUz(), month.getMonthNum(), month.getYear()
        )));
        return resMonths;
    }


    public ApiResponse delete(long id) {
//        Month month = monthRepository.findById(id).orElseThrow(() -> new ResourceFinderException("getMonth"));
        monthRepository.deleteById(id);
        return new ApiResponse(String.valueOf(id), true);
    }

    public ApiResponse addMonth(ReqMonth reqMonth) {
        if (!monthRepository.existsByNameAndYear(templateMonth(reqMonth.getTempId()).getName(), reqMonth.getYear())) {
            Month month = new Month();
            monthRepository.save(saveField(month, reqMonth));
            YearMonth yearMonthObject = YearMonth.of(reqMonth.getYear(), month.getMonthNum());
            for (int i = 1; i <= yearMonthObject.lengthOfMonth(); i++) {
                Day day = new Day();
                day.setMonth(month);
                day.setDayNum(i);
                day.setName(weekDay(month.getYear(), month.getMonthNum(), i));
                dayRepository.save(day);
            }
            ResMonth resMonth = new ResMonth(month.getId(), month.getName(), month.getNameUz(), month.getMonthNum(), month.getYear());
            return new ApiResponse("add", true, resMonth);
        } else {
            return new ApiResponse("shu yilga ushbu oy mavjud", false);
        }
    }


    public ApiResponse editMonth(ReqMonth reqMonth) {
        if (!monthRepository.existsByNameAndYear(templateMonth(reqMonth.getTempId()).getName(), reqMonth.getYear())) {
            Month month = monthRepository.findById(reqMonth.getId()).orElseThrow(() -> new ResourceNotFoundException("getMonth"));
            monthRepository.save(saveField(month, reqMonth));
            YearMonth yearMonthObject = YearMonth.of(reqMonth.getYear(), month.getMonthNum());
            for (int i = 1; i <= yearMonthObject.lengthOfMonth(); i++) {
                Day day;
                if (dayRepository.existsByDayNumAndMonth_Id(i, month.getId())) {
                    day = dayRepository.getByDayNumAndMonth_Id(i, month.getId());
                } else {
                    day = new Day();
                }
                day.setMonth(month);
                day.setDayNum(i);
                day.setName(weekDay(month.getYear(), month.getMonthNum(), i));
                dayRepository.save(day);
            }
            ResMonth resMonth = new ResMonth(month.getId(), month.getName(), month.getNameUz(), month.getMonthNum(), month.getYear());
            return new ApiResponse("edit", true, resMonth);
        } else {
            return new ApiResponse("bunday oy mavjud", false);
        }
    }

    private Month saveField(Month month, ReqMonth reqMonth) {
        TemplateMonth templateMonth = templateMonth(reqMonth.getTempId());
        month.setMonthNum(templateMonth.getMonthNum());
        month.setName(templateMonth.getName());
        month.setNameUz(templateMonth.getNameUz());
        month.setYear(reqMonth.getYear());
        return month;
    }

    private TemplateMonth templateMonth(long id) {
        return tempRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getTempMonth"));
    }

    private String weekDay(int year, int month, int day) {
        String dateString = String.format("%d-%d-%d", year, month, day);
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-M-d").parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat format = new SimpleDateFormat("EEEE");
        return format.format(date);
    }
}
