package com.pb.personalblog.repository;

import com.pb.personalblog.pojo.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
/**
 * @author zhk
 * @date 2022/5/21 16:58
 * 后台管理功能controller
 */
public interface CommentRepository extends JpaRepository<Comment, Long> {


    /**
     * 查询评论总数
     *
     * @return
     */
    @Query("select count(id) from Comment ")
    int commentCount();

    List<Comment> findByBlogIdAndParentCommentNull(Long blogId, Sort sort);
}
