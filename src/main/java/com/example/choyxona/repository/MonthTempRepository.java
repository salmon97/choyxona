package com.example.choyxona.repository;

import com.example.choyxona.entity.TemplateMonth;
import com.example.choyxona.projection.CustomTemplateMonth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin
@RepositoryRestResource(path = "templateMonth", collectionResourceRel = "list", excerptProjection = CustomTemplateMonth.class)
public interface MonthTempRepository extends JpaRepository<TemplateMonth, Long> {
}