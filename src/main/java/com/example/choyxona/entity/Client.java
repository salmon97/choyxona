package com.example.choyxona.entity;

import com.example.choyxona.entity.enums.Status;
import com.example.choyxona.entity.template.AbsEntity;
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
public class Client extends AbsEntity {

    @ManyToOne
    private Room room;

    @ManyToOne
    private Day day;

    @Enumerated(EnumType.STRING)
    private Status status;
    private String name;
    private String telName;
    private Long chatId;
    private Double totalSum;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductClient> productClients;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductKgClient> productKgClients;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ServiceClient> serviceClients;
}