package com.example.choyxona.entity;

import com.example.choyxona.entity.template.AbsNameEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

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

    @OneToMany(mappedBy = "day", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    List<Client> clients;
}