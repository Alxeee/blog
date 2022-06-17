package com.wusl.service.impl;

import com.wusl.dao.CommentRepository;
import com.wusl.pojo.Comment;
import com.wusl.service.CommentService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class CommentServiceImpl implements CommentService {


    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> listCommentByBlogId(Long blogId) {
        Sort sort = Sort.by(Sort.Direction.ASC,"creteTime");
        List<Comment> comments = commentRepository.findByBlogIdAndParentCommentNull(blogId,sort);
        return eachComment(comments);
    }


    @Transactional
    public Comment saveComment(Comment comment) {
        Long parentCommentId = comment.getParentComment().getId();
        if (parentCommentId != -1) {
            comment.setParentComment(commentRepository.findById(parentCommentId).orElse(null));
        } else {
            comment.setParentComment(null);
        }
        comment.setCreteTime(new Date());
        return commentRepository.save(comment);
    }

    /*循环每个顶级的评论节点*/
    private List<Comment> eachComment(List<Comment> comments) {
        List<Comment> commentsView = new ArrayList<>();
        for (Comment comment : comments) {
            Comment c = new Comment();
            BeanUtils.copyProperties(comment, c);
            commentsView.add(c);
        }
        //合并评论的各层子代到第一级子代集合中
        combineChildren(commentsView);
        return commentsView;
    }

    /*root跟节点，blog不为空的对象集合*/
    private void combineChildren(List<Comment> comments) {
        for (Comment comment : comments) {
            List<Comment> replys1 = comment.getReplyComment();
            for (Comment comment1 : replys1) {
                //循环迭代，找出子代，存放在tempReplys中
                recursively(comment1);
            }
            //修改顶级节点的reply集合为迭代处理后的集合
            comment.setReplyComment(temReplys);
            //清除临时存放区
            temReplys = new ArrayList<>();
        }
    }

    /*存放迭代找出的所有子代的集合*/
    private List<Comment> temReplys = new ArrayList<>();

    /*递归迭代*/
    private void recursively(Comment comment) {
        //顶节点添加到临时存放集合
        temReplys.add(comment);
        if (comment.getReplyComment().size()>0){
            List<Comment> replys = comment.getReplyComment();
            for (Comment reply : replys) {
                temReplys.add(reply);
                if (reply.getReplyComment().size()>0){
                    recursively(reply);
                }
            }
        }
    }
}
