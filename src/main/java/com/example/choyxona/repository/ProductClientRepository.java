package com.example.choyxona.repository;

import com.example.choyxona.entity.ProductClient;
import com.example.choyxona.payload.ResPro_Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ProductClientRepository extends JpaRepository<ProductClient, UUID> {

    List<ProductClient> findAllByClient_Id(UUID client_id);

    @Query(value = "select sum(sum) from product_client where client_id = :clientId", nativeQuery = true)
    Double getTotalSum(UUID clientId);

    @Query(value = "select pr.name , sum(pc.piece) as amount , sum(pc.sum) as summa from product_client pc, product pr where pr.id = pc.product_id and pc.client_id = :clientId group by pr.name", nativeQuery = true)
    List<ResPro_Client> getByGroupedPro(UUID clientId);


    @Query(value = "select pr.name , sum(pc.piece) as amount , sum(pc.sum) as summa, sum(pc.cost) as costs from day d, client cl, product pr, product_client pc " +
            "where d.month_id = :monthId and d.id = cl.day_id and pr.id = pc.product_id and pc.client_id = cl.id group by pr.name", nativeQuery = true)
    List<ResPro_Client> getPrClientInfoMonth(int monthId);

    @Query(value = "select pr.name , sum(pc.piece) as amount , sum(pc.sum) as summa, sum(pc.cost) as costs from day d, client cl, product pr, product_client pc " +
            "where d.id = :dayId and d.id = cl.day_id and pr.id = pc.product_id and pc.client_id = cl.id group by pr.name", nativeQuery = true)
    List<ResPro_Client> getPrClientInfoDay(int dayId);
}