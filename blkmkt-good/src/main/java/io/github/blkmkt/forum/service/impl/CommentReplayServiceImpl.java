package io.github.blkmkt.forum.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.github.common.utils.PageUtils;
import io.github.common.utils.Query;
import io.github.common.entity.PageParam;

import io.github.blkmkt.forum.dao.CommentReplayDao;
import io.github.blkmkt.forum.entity.CommentReplayEntity;
import io.github.blkmkt.forum.service.CommentReplayService;

import java.util.List;
import java.util.stream.Collectors;


@Service("commentReplayService")
public class CommentReplayServiceImpl extends ServiceImpl<CommentReplayDao, CommentReplayEntity> implements CommentReplayService {

    @Override
    public PageUtils<CommentReplayEntity> queryPage(PageParam params) {
        IPage<CommentReplayEntity> page = this.page(
                new Query<CommentReplayEntity>().getPage(params),
                new QueryWrapper<>()
        );

        return new PageUtils<>(page);
    }

    @Override
    public void deleteAllReplayByCommentId(Long id) {
        List<CommentReplayEntity> commentReplayEntities = this.baseMapper.selectList(new QueryWrapper<CommentReplayEntity>().eq("comment_id", id));
        List<Integer> idList = commentReplayEntities.stream().map(CommentReplayEntity::getId).collect(Collectors.toList());
        this.removeByIds(idList);
    }

    @Override
    public List<CommentReplayEntity> getReplaysByCommentId(Integer id) {
        return this.baseMapper.selectList(new QueryWrapper<CommentReplayEntity>().eq("comment_id", id));
    }

}
