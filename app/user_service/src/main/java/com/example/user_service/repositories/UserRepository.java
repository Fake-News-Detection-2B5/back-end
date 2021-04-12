package com.example.user_service.repositories;

import com.example.user_service.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Interface that represents the users database.
 * It contains UserEntity as rows and has a key of type Long.
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    @Modifying
    @Query("UPDATE UserEntity u SET u.name = ?1, u.password = ?2 WHERE u.id = ?3")
    void update(String name, String password, Long id);
}
