package com.example.choyxona.repository;

import com.example.choyxona.entity.Client;
import com.example.choyxona.entity.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    List<Client> findAllByDay_Id(Long day_id);

    List<Client> findAllByDay_IdAndRoom_Id(Long day_id, Long room_id);

    boolean existsByChatIdAndStatus(Long chatId, Status status);

    @Query(value = "select * from client where chat_id = :chatId and status = :statusT order by register_at desc limit 1", nativeQuery = true)
    Client getByChatIdLastAndStatus(long chatId, String statusT);

    @Query(value = "select * from client where chat_id = :chatId order by register_at desc limit 1", nativeQuery = true)
    Client getByChatIdLast(long chatId);

    @Query(value = "select c.* from client c, month m, day d where c.day_id = d.id" +
            " and m.id = d.month_id and m.year = :yearNum and m.month_num = :monthNum" +
            " and d.day_num = :dayNum", nativeQuery = true)
    List<Client> getByGivenDate(int yearNum, int monthNum, int dayNum);

    List<Client> findAllByStatus(Status status);
}