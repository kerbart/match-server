package com.kerbart.match.controller.responses;

import com.kerbart.match.model.Like;

import java.util.List;

public class LikesResponse {

    List<Like> likes;

    public LikesResponse() {

    }

    public List<Like> getLikes() {
        return likes;
    }

    public void setLikes(List<Like> likes) {
        this.likes = likes;
    }
}
