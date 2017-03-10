package com.kerbart.match.controller.responses;

import com.kerbart.match.model.Like;

public class LikeResponse {

   Like like;

    public LikeResponse() {

    }

    public LikeResponse(Like like) {
        this.like = like;
    }

    public Like getLike() {
        return like;
    }

    public void setLike(Like like) {
        this.like = like;
    }
}
