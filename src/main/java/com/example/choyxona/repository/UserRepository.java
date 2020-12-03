package com.example.choyxona.repository;

import com.example.choyxona.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);


    User findByChatId(Long chatId);

    @Query(value = "select * from users u inner join user_role ur on u.id = ur.user_id and ur.role_id = 'ROLE_DIRECTOR'", nativeQuery = true)
    User getDirector();


    @Query(value = "select * from users u inner join user_role ur on u.id = ur.user_id and ur.role_id = 'ROLE_STAFF'", nativeQuery = true)
    List<User> getStaffs();
}
