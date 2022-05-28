package com.pb.personalblog.repository;

import com.pb.personalblog.pojo.Type;
import com.pb.personalblog.pojo.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author zhk
 * @date 2022/5/28 10:40
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);
}
