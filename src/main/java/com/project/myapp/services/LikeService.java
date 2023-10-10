package com.project.myapp.services;


import com.project.myapp.entities.Like;
import com.project.myapp.entities.Post;
import com.project.myapp.entities.User;
import com.project.myapp.repos.LikeRepository;
import com.project.myapp.requests.LikeCreateRequest;
import com.project.myapp.responses.LikeResponse;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class LikeService {

    LikeRepository likeRepository;
    UserService userService;
    PostService postService;
    public LikeService(LikeRepository likeRepository, UserService userService, PostService postService) {
        this.likeRepository = likeRepository;
        this.postService=postService;
        this.userService=userService;
    }


    public List<LikeResponse> getAllLikesWithParam(Optional<Long> userId, Optional<Long> postId) {
        List<Like> list;
        if (userId.isPresent() && postId.isPresent()){
            list = likeRepository.findByUserIdAndPostId(userId.get(), postId.get());
        }if (userId.isPresent()){
            list= likeRepository.findByUserId(userId.get());
        }if (postId.isPresent()){
            list= likeRepository.findByPostId(postId.get());
        }else
            list= likeRepository.findAll();
        return list.stream().map(like -> new LikeResponse(like)).collect(Collectors.toList());
    }


    public Like getOneLikeById(Long likeId) {
        return likeRepository.findById(likeId).orElse(null);
    }

    public Like createOnelike(LikeCreateRequest likeCreateRequest) {
        User user= userService.getOneUserById(likeCreateRequest.getUserId());
        Post post =postService.getOnePostById(likeCreateRequest.getPostId());

        if (user != null && post != null){
            Like likeToSave = new Like();
            likeToSave.setId(likeCreateRequest.getId());
            likeToSave.setUser(user);
            likeToSave.setPost(post);
            return likeRepository.save(likeToSave);
        }else
            return null;
    }
    public void deleteOneLikeById(Long likeId) {
        likeRepository.deleteById(likeId);
    }
}
