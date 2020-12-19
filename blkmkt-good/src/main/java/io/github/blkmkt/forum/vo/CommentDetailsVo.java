package io.github.blkmkt.forum.vo;

import io.github.blkmkt.forum.entity.GoodCommentEntity;
import lombok.Data;

import java.util.List;

@Data
public class CommentDetailsVo {
    private Integer userId;

    private String username;

    private String headImgUrl;

    private GoodCommentEntity commentEntity;

    private List<ReplayDetailsVo> replayEntities;
}
