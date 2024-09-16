package com.toki.socialmedia.service;

import com.toki.socialmedia.model.Reels;
import com.toki.socialmedia.model.User;
import com.toki.socialmedia.repository.ReelRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReelServiceImpl implements ReelService{
    private final ReelRepository reelRepository;
    private final UserServiceImpl userServiceImpl;

    public ReelServiceImpl(ReelRepository reelRepository, UserServiceImpl userServiceImpl) {
        this.reelRepository = reelRepository;
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public List<Reels> getAllReels() {
        List<Reels> listReels= new ArrayList<>();
        listReels = reelRepository.findAll();
        return listReels;
    }

    @Override
    public Reels getReelById(UUID reelId) throws Exception {
        Optional<Reels> reel = reelRepository.findById(reelId);
        if(reel.isPresent()){
            return reel.get();
        }
        throw new Exception("Reel not found");
    }

    @Override
    public Reels createReel(User user, Reels reel) {
        Reels newReel = new Reels();
        newReel.setTitle(reel.getTitle());
        newReel.setVideoUrl(reel.getVideoUrl());
        newReel.setUser(user);
        Reels saveReel= reelRepository.save(newReel);
        return saveReel;
    }

    @Override
    public List<Reels> getReelsByUserId(UUID userId) throws Exception {
        User user = userServiceImpl.findUserById(userId);
        List<Reels> listReels = reelRepository.findByUser(user);
        return listReels;
    }
}
