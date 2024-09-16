package com.toki.socialmedia.service;

import com.toki.socialmedia.model.Reels;
import com.toki.socialmedia.model.User;

import java.util.List;
import java.util.UUID;

public interface ReelService {
    List<Reels> getAllReels();
    Reels getReelById(UUID reelId) throws Exception;
    Reels createReel(User user, Reels reel);
    List<Reels> getReelsByUserId(UUID userId) throws Exception;
}
