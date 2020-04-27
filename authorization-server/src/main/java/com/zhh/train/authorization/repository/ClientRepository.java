package com.zhh.train.authorization.repository;

import com.zhh.train.authorization.entity.Client;
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
public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findByClientName(String clientName);
}
