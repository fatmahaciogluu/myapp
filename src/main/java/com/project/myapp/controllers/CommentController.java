package com.project.myapp.controllers;

import com.project.myapp.entities.Comment;
import com.project.myapp.requests.CommentCreateRequest;
import com.project.myapp.requests.CommentUpdateRequest;
import com.project.myapp.services.CommentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping
    public List<Comment> getAllComments(@RequestParam Optional<Long> userId,
                                        @RequestParam Optional<Long> postId){
        return commentService.getAllCommentsWithParam(userId, postId);
    }

    @GetMapping("/{commentId}")
    public Comment getOneComment(@PathVariable Long commentId){
        return commentService.getOneCommentByd(commentId);
    }

    @PostMapping
    public Comment createOneComment(@RequestBody CommentCreateRequest newCommentRequest){
        return commentService.createOneComment(newCommentRequest);
    }

    @PutMapping("/{commentId}")
    public Comment updateOneComment(@PathVariable Long commentId, @RequestBody CommentUpdateRequest updateCommentRequest){
        return commentService.updateOneCommentById(commentId, updateCommentRequest);
    }

    @DeleteMapping("/{commentId}")
    public void deleteOneComment(@PathVariable Long commentId){
        commentService.deleteOneCommentById(commentId);
    }
}
