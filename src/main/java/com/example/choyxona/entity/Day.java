package com.example.choyxona.entity;

import com.example.choyxona.entity.template.AbsNameEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Day extends AbsNameEntity {

    @ManyToOne
    @JsonBackReference
    private Month month;

    private String nameUz;

    private Integer dayNum;
}