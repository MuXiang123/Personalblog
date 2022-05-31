package com.pb.personalblog.repository;

import com.pb.personalblog.pojo.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @author zhk
 * @date 2022/5/31 16:58
 * 博客dao
 */
public interface BlogRepository extends JpaRepository<Blog, Long>, JpaSpecificationExecutor<Blog> {
    /**
     * 添加浏览次数
     *
     * @param id
     * @return
     */
    @Transactional
    @Modifying
    @Query("update Blog b set b.views = b.views+1 where b.id = ?1")
    int updateViews(Long id);

    @Override
    Blog getOne(Long id);

    /**
     * 查询被推荐的博客
     *
     * @param pageable
     * @return
     */
    @Query("select b from Blog b where b.recommend = true")
    List<Blog> findTop(Pageable pageable);

    /**
     * 根据博客id查询
     *
     * @param query
     * @param pageable
     * @return
     */
    @Query("select b from Blog b where b.title like ?1 or b.content like ?1")
    Page<Blog> findByQuery(String query, Pageable pageable);

    /**
     * 博客总数
     *
     * @return
     */
    @Query("select count(id) from Blog")
    int blogCount();

    /**
     * 浏览总数
     *
     * @return
     */
    @Query("select sum(views) from Blog")
    int viewSum();
}
