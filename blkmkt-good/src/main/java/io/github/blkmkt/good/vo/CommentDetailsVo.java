package io.github.blkmkt.good.vo;

import io.github.blkmkt.good.entity.GoodCommentEntity;
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
