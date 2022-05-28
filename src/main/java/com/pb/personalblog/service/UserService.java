package com.pb.personalblog.service;

import com.pb.personalblog.pojo.User;
import org.springframework.stereotype.Service;

/**
 * @author zhk
 * @date 2022/5/28 9:44
 * 用户登录service
 */
public interface UserService {
    /**
     * 根据用户名密码检查用户
     *
     * @param username
     * @param password
     * @return
     */
    User checkUser(String username, String password);

    /**
     * 使用用户名查询用户信息和权限
     *
     * @param username
     * @return
     */
    User getUser(String username);
}
