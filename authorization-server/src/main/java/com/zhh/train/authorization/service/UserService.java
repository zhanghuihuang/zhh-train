package com.zhh.train.authorization.service;

import com.zhh.train.authorization.dto.UserSaveDto;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/25 7:35 下午
 */
public interface UserService extends UserDetailsService {
    @Transactional
    Long createUser(UserSaveDto dto);
}
