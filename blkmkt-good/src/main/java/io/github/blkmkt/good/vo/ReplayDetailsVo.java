package io.github.blkmkt.good.vo;

import io.github.blkmkt.good.entity.CommentReplayEntity;
import lombok.Data;

@Data
public class ReplayDetailsVo {
    private Integer userId;

    private String username;

    private String headImgUrl;

    private CommentReplayEntity commentReplayEntity;
}
