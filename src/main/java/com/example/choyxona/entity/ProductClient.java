package com.example.choyxona.entity;

import com.example.choyxona.entity.template.AbsEntity;
import lombok.*;

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

    private Integer piece;

    private Double sum;
    private Double cost;
}
