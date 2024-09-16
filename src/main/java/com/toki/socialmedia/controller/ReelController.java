package com.toki.socialmedia.controller;

import com.toki.socialmedia.model.Reels;
import com.toki.socialmedia.model.User;
import com.toki.socialmedia.service.ReelServiceImpl;
import com.toki.socialmedia.service.UserServiceImpl;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("reels")
public class ReelController {
    private final ReelServiceImpl reelServiceImpl;
    private final UserServiceImpl userServiceImpl;

    public ReelController(ReelServiceImpl reelServiceImpl, UserServiceImpl userServiceImpl) {
        this.reelServiceImpl = reelServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("")
    public List<Reels> getAllReels(){
        List<Reels> listReels = reelServiceImpl.getAllReels();
        return listReels;
    }

    @GetMapping("/{userId}")
    public List<Reels> getUserReels(@PathVariable UUID userId) throws Exception {
        List<Reels> listReels = reelServiceImpl.getReelsByUserId(userId);
        return listReels;
    }

    @PostMapping("/create")
    public Reels createReel(@RequestHeader("Authorization") String token, @RequestBody Reels reel) throws Exception {
        User user = userServiceImpl.findUserByToken(token);
        Reels newReel = reelServiceImpl.createReel(user, reel);
        return newReel;
    }
}
