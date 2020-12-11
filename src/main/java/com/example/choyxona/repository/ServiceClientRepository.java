package com.example.choyxona.repository;

import com.example.choyxona.entity.ServiceClient;
import com.example.choyxona.payload.ResPro_Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ServiceClientRepository extends JpaRepository<ServiceClient, UUID> {

    List<ServiceClient> findAllByClient_Id(UUID client_id);

    @Query(value = "select sum(price) from service_client where client_id = :clientId", nativeQuery = true)
    Double getTotalSumService(UUID clientId);

    @Query(value = "select sum(sc.price) as summa, sum(sc.cost) as costs from service_client sc, day d, client cl where sc.client_id = cl.id and d.id = cl.day_id and d.month_id = :monthId", nativeQuery = true)
    ResPro_Client totalSumServiceMonth(long monthId);

    @Query(value = "select sum(sc.price) as summa, sum(sc.cost) as costs from service_client sc, day d, client cl where sc.client_id = cl.id and d.id = cl.day_id and d.id = :dayId", nativeQuery = true)
    ResPro_Client totalSumServiceDay(long dayId);
}
