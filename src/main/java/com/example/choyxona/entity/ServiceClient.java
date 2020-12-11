package com.example.choyxona.entity;

import com.example.choyxona.entity.template.AbsEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceClient extends AbsEntity {

    @ManyToOne
    private Client client;

    private String definition;

    private double price;

    private double cost;

}