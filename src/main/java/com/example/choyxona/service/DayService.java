package com.example.choyxona.service;

import com.example.choyxona.entity.Day;
import com.example.choyxona.entity.Month;
import com.example.choyxona.payload.ApiResponse;
import com.example.choyxona.payload.ResDay;
import com.example.choyxona.payload.ResPro_Client;
import com.example.choyxona.repository.DayRepository;
import com.example.choyxona.repository.MonthRepository;
import com.example.choyxona.repository.ProductClientRepository;
import com.example.choyxona.repository.ProductKgClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DayService {

    @Autowired
    DayRepository dayRepository;

    @Autowired
    MonthRepository monthRepository;

    @Autowired
    ProductClientRepository productClientRepository;

    @Autowired
    ProductKgClientRepository productKgClientRepository;

    public List<ResDay> resDays(Long id) {
        Month month = monthRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("getMonth"));
        List<ResDay> resDays = new ArrayList<>();
        for (Day day : dayRepository.getDayByMonthId(month.getId())) {
            resDays.add(new ResDay(
                    day.getId(),
                    day.getDayNum(),
                    month.getNameUz(),
                    month.getYear()));
        }
        return resDays;
    }


    public ApiResponse infoProClientDay(int dayId) {
        List<ResPro_Client> prClientInfoMonth = productClientRepository.getPrClientInfoDay(dayId);
        List<ResPro_Client> prKgClientInfoMonth = productKgClientRepository.getPrClientKgInfoDay(dayId);
        Object[] objects = {prClientInfoMonth, prKgClientInfoMonth};
        return new ApiResponse("ok", true, objects);
    }
}
