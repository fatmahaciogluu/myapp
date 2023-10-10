package com.project.myapp.responses;

import com.project.myapp.entities.Like;
import lombok.Data;

@Data
public class LikeResponse {

    Long id;
    Long postId;
    Long userId;

    public LikeResponse(Like entity) {
        this.id = entity.getId();
        this.postId = entity.getPost().getId();
        this.userId = entity.getUser().getId();
    }
}
