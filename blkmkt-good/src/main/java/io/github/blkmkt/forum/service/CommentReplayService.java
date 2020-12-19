package io.github.blkmkt.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import io.github.common.entity.PageParam;
import io.github.common.utils.PageUtils;
import io.github.blkmkt.forum.entity.CommentReplayEntity;

import java.util.List;


/**
 * 对评价的回复
 *
 * @author Zhihao Shen
 * @email zhihaoshen7@qq.com
 * @date 2020-12-13 19:36:24
 */
public interface CommentReplayService extends IService<CommentReplayEntity> {

    PageUtils<CommentReplayEntity> queryPage(PageParam params);

    void deleteAllReplayByCommentId(Long id);

    List<CommentReplayEntity> getReplaysByCommentId(Integer id);
}

