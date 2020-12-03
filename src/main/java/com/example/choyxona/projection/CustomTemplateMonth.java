package com.example.choyxona.projection;

import com.example.choyxona.entity.TemplateMonth;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "CustomTemplateMonth", types = TemplateMonth.class)
public interface CustomTemplateMonth {
    Integer getId();

    String getName();

    Integer getMonthNum();

    String getNameUz();
}
