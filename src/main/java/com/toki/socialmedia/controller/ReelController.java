package com.toki.socialmedia.controller;

import com.toki.socialmedia.model.Reels;
import com.toki.socialmedia.model.User;
import com.toki.socialmedia.service.ReelServiceImpl;
import com.toki.socialmedia.service.UserServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
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

    @Operation(summary = "Get all reels")
    @GetMapping("")
    public List<Reels> getAllReels(){
        List<Reels> listReels = reelServiceImpl.getAllReels();
        return listReels;
    }

    @Operation(summary = "Get reels by user")
    @GetMapping("/{userId}")
    public List<Reels> getUserReels(@PathVariable UUID userId) throws Exception {
        List<Reels> listReels = reelServiceImpl.getReelsByUserId(userId);
        return listReels;
    }

    @Operation(summary = "Create reel")
    @PostMapping("/create")
    public Reels createReel(@RequestHeader("Authorization") String token, @RequestBody Reels reel) throws Exception {
        User user = userServiceImpl.getUserByToken(token);
        Reels newReel = reelServiceImpl.createReel(user, reel);
        return newReel;
    }
}
