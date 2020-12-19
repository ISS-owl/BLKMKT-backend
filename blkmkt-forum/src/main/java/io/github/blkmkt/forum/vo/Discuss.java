package io.github.blkmkt.forum.vo;

import io.github.blkmkt.forum.entity.ReplyEntity;
import lombok.Data;

import java.util.List;

@Data
public class Discuss
{
    List<Discuss> next;
    List<ReplyEntity> replyEntities;
}
