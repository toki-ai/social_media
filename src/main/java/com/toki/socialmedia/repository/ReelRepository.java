package com.toki.socialmedia.repository;

import com.toki.socialmedia.model.Reels;
import com.toki.socialmedia.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ReelRepository extends JpaRepository<Reels, UUID> {
    List<Reels> findByUser(User user);
}
