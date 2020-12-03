package com.example.choyxona.entity;

import com.example.choyxona.entity.template.AbsNameEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;


@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductKg extends AbsNameEntity {

    private Double balance;

    private Double cost;
}
