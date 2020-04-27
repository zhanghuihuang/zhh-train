package com.zhh.train.authorization.repository;

import com.zhh.train.authorization.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/25 7:34 下午
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
