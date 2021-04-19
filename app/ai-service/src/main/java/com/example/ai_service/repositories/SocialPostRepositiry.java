package com.example.ai_service.repository;

import com.example.ai_service.entities.SocialPostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Interface that represents the social posts database.
 * It contains SocialPostEnitty as rows and has a key of type Long.
 */
@Repository
public interface SocialPostRepository extends JpaRepository<SocialPostEntity, Long> {

    @Modifying
    @Query("UPDATE SocialPostEntity sp SET sp.url = ?1, sp.score = ?2 WHERE sp.id = ?3")
    void update(String url, String score, Long id);
}
