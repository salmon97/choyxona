package com.example.choyxona.entity;

import com.example.choyxona.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductKgClient extends AbsEntity {

    @ManyToOne
    private Client client;

    @ManyToOne
    private ProductKg productKg;

    private Integer kg;

    private Double sum;

    private Double cost;
}
