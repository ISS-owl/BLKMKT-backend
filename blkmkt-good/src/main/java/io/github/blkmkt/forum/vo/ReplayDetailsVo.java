package io.github.blkmkt.forum.vo;

import io.github.blkmkt.forum.entity.CommentReplayEntity;
import lombok.Data;

@Data
public class ReplayDetailsVo {
    private Integer userId;

    private String username;

    private String headImgUrl;

    private CommentReplayEntity commentReplayEntity;
}
