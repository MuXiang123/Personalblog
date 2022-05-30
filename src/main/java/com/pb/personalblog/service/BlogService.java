package com.pb.personalblog.service;

import com.pb.personalblog.pojo.Blog;
import com.pb.personalblog.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BlogService {
    Blog getBlog(Long id);

    /**
     * 编辑器转换为html
     *
     * @param id
     * @return
     */
    Blog getAndConvert(Long id);

    Page<Blog> listBlog(Pageable pageable, BlogQuery blog);

    Page<Blog> findByQuery(String query, Pageable pageable);

    //推荐博客
    List<Blog> listRecommendBlogTop(Integer size);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id, Blog blog);

    void deleteBlog(Long id);

    Page<Blog> listBlog(Pageable pageable);
}
