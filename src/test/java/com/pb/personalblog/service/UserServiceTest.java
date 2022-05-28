package com.pb.personalblog.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * @author zhk
 * @date 2022/5/28 14:30
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    public void contextLoads() {
//        ValueOperations<String, String> opsForValue = this.stringRedisTemplate.opsForValue();
//        opsForValue.set("name", "lisi"); // 缓存数据
//        String value = opsForValue.get("name"); // 获取缓存数据
//        System.out.println(value);
        System.out.println(userService.checkUser("zhk", "123456").toString());
    }
}
