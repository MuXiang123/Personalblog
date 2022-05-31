package com.pb.personalblog.service.impl;


import com.pb.personalblog.repository.BlogRepository;
import com.pb.personalblog.repository.CommentRepository;
import com.pb.personalblog.service.CommonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @author zhk
 * @date 2022/5/31 16:36
 * 后台管理底部栏impl
 * 都是先查缓存，再查数据库。缓存12小时过期
 */
@Service
public class CommonServiceImpl implements CommonService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public int getArticle() {
        int count = 0;
        Object o = redisTemplate.opsForValue().get("article");
        if (o != null) {
            count = (int) o;
        } else {
            count = blogRepository.blogCount();
            redisTemplate.opsForValue().set("article", count, 60 * 60 * 12, TimeUnit.SECONDS);
        }
        return count;
    }

    @Override
    public int getVisit() {
        int count = 0;
        Object o = redisTemplate.opsForValue().get("visit");
        if (o != null) {
            count = (int) o;
        } else {
            count = blogRepository.viewSum();
            redisTemplate.opsForValue().set("visit", count, 60 * 60 * 12, TimeUnit.SECONDS);
        }
        return count;
    }

    @Override
    public int getComment() {
        int count = 0;
        Object o = redisTemplate.opsForValue().get("comment");
        if (o != null) {
            count = (int) o;
        } else {
            count = commentRepository.commentCount();
            redisTemplate.opsForValue().set("comment", count, 60 * 60 * 12, TimeUnit.SECONDS);
        }
        return count;
    }
}
