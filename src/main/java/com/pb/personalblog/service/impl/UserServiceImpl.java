package com.pb.personalblog.service.impl;

import com.pb.personalblog.pojo.User;
import com.pb.personalblog.repository.UserRepository;
import com.pb.personalblog.service.UserService;
import com.pb.personalblog.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.soap.SOAPBinding;

/**
 * @author zhk
 * @date 2022/5/28 9:45
 * 后台用户登录是实现类
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 登录时检查用户是否存在
     * 先走缓存再走数据库
     *
     * @param username
     * @param password
     * @return
     */
    @Transactional
    @Override
    public User checkUser(String username, String password) {
        User user = null;
        Object o = redisTemplate.opsForValue().get("user_" + username);
        if (o != null) {
            user = (User) o;
//            String md5Pass = MD5Utils.code(password);
////            if (!username.equals(user.getUsername()) || !user.getPassword().equals(md5Pass)) {
////                return null;
////            }
            System.out.println(user.toString());
        } else {
            user = userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
            System.out.println(user.toString());
            if (user != null) {
                redisTemplate.opsForValue().set("user_" + username, user);
            }
        }
        return user;
    }

    /**
     * 通过用户名得到用户
     * 先走缓存再走数据库
     *
     * @param username
     * @return
     */
    @Transactional
    @Override
    public User getUser(String username) {
        User user = null;
        Object o = redisTemplate.opsForValue().get("user_" + username);
        if (o != null) {
            user = (User) o;
        } else {
            user = userRepository.findByUsername(username);
            if (user != null) {
                redisTemplate.opsForValue().set("user_" + username, user);
            } else {
                return null;
            }
        }
        return user;
    }
}
