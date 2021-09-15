package com.blog.api.command;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blog.api.dto.CommentDTO;
import com.blog.daoservice.dao.CommentDao;
import com.blog.daoservice.entry.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.TransformationUtil;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author pengli
 * @create 2021-09-15 14:48
    评论操作指令
 */
@Service
public class CommentExe {

    @Autowired
    private CommentDao commentDao;

    /**
     * 添加评论
     * @return
     */
    public CommentDTO addComment(CommentDTO commentDTO){
        commentDTO.setCreateTime(LocalDateTime.now());
        commentDTO.setGoods(0);
        Comment comment = TransformationUtil.copyToDTO(commentDTO, Comment.class);
        boolean save = commentDao.save(comment);
        if(save){
            return TransformationUtil.copyToDTO(comment,commentDTO.getClass());
        }
        return null;
    }

    /**
     * 分页查询指定博客下的最新评论
     * @return
     */
    public Page<CommentDTO> queryComment(Integer index ,Integer size,Integer blogId){
        IPage<Comment> commentIPage = commentDao.selectNewCommentByBlogId(index,size,blogId);

        Page<CommentDTO> result=new Page<>(index,size);
        commentIPage.getTotal();
        result.setTotal(commentIPage.getTotal());
        List<CommentDTO> commentDTOS = TransformationUtil.copyToLists(commentIPage.getRecords(), CommentDTO.class);
        commentDTOS.forEach(c->{
            //父类编号
            Integer fId=c.getId();
            //查询该评论的回复
            List<Comment> subComment = commentDao.selectSubComment(fId);
            c.setSubComment(TransformationUtil.copyToLists(subComment,CommentDTO.class));
        });
        result.setRecords(commentDTOS);
        return result;
    }

    /**
     * 分页查询指定博客下的最新最热评论
     * @return
     */
    public Page<CommentDTO> queryNewGoodsComment(Integer index ,Integer size,Integer blogId){
        IPage<Comment> commentIPage = commentDao.selectNewAndGoodsCommentByBlogId(index,size,blogId);

        Page<CommentDTO> result=new Page<>(index,size);
        result.setTotal(commentIPage.getTotal());
        result.setRecords(TransformationUtil.copyToLists(commentIPage.getRecords(),CommentDTO.class));
        return result;
    }
}