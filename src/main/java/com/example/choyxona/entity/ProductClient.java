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
@AllArgsConstructor
@NoArgsConstructor
public class ProductClient extends AbsEntity {

    @ManyToOne
    private Client client;

    @ManyToOne
    private Product product;

    private String name;
    private Integer piece;
    private Double sum;
    private Double cost;
}
