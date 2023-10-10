package com.project.myapp.controllers;

import com.project.myapp.entities.Like;
import com.project.myapp.requests.LikeCreateRequest;
import com.project.myapp.responses.LikeResponse;
import com.project.myapp.services.LikeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/likes")
public class LikeController {

    LikeService likeService;
    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @GetMapping
    public List<LikeResponse> getAllLikes(@RequestParam Optional<Long> userId,
                                          @RequestParam Optional<Long> postId){
        return likeService.getAllLikesWithParam(userId, postId);
    }

    @GetMapping("/{likeId}")
    public Like getOneLike(@RequestParam Long likeId){
        return likeService.getOneLikeById(likeId);
    }
    @PostMapping
    public Like createOnelike(@RequestBody LikeCreateRequest likeCreateRequest){
        return likeService.createOnelike(likeCreateRequest);
    }
    @DeleteMapping("/{likeId}")
    public void deleteOneLike(@PathVariable Long likeId){
        likeService.deleteOneLikeById(likeId);
    }

}
