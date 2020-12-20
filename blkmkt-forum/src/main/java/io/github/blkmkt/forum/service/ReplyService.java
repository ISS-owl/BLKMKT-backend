package io.github.blkmkt.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.blkmkt.forum.vo.Discuss;
import io.github.common.entity.PageParam;
import io.github.common.utils.PageUtils;
import io.github.blkmkt.forum.entity.ReplyEntity;

import java.util.List;

/**
 * 
 *
 * @author YuNing Wu
 * @email 414085716@qq.com
 * @date 2020-12-19 12:53:38
 */
public interface ReplyService extends IService<ReplyEntity> {

    PageUtils<ReplyEntity> queryPage(PageParam params);

    PageUtils<ReplyEntity> getReplyEntityByArticleId(PageParam param, Integer id);

    public List<ReplyEntity> getDiscuss(Integer id);
}

