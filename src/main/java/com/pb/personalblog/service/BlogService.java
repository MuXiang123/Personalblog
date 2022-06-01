package com.pb.personalblog.service;

import com.pb.personalblog.pojo.Blog;
import com.pb.personalblog.vo.BlogQuery;
import org.aspectj.apache.bcel.generic.LineNumberGen;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface BlogService {
    Blog getBlog(Long id);

    Blog getAndConvert(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> listBlog(Long tagId, Pageable pageable);

    Page<Blog> findByQuery(String query, Pageable pageable);

    //年份归档博客
    Map<String, List<Blog>> archiveBlog();

    //获取博客数量
    Long countBlog();

    //推荐博客
    List<Blog> listRecommendBlogTop(Integer size);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);

    Page<Blog> listBlog(Pageable pageable);
}
