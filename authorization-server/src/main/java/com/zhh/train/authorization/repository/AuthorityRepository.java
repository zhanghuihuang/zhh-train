package com.zhh.train.authorization.repository;

import com.zhh.train.authorization.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/25 7:34 下午
 */
@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
