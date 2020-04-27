package com.zhh.train.authorization.service.impl;

import com.zhh.train.authorization.dto.UserSaveDto;
import com.zhh.train.authorization.entity.Authority;
import com.zhh.train.authorization.entity.Role;
import com.zhh.train.authorization.entity.User;
import com.zhh.train.authorization.enums.StatusEnum;
import com.zhh.train.authorization.repository.AuthorityRepository;
import com.zhh.train.authorization.repository.RoleRepository;
import com.zhh.train.authorization.repository.UserRepository;
import com.zhh.train.authorization.service.UserService;
import com.zhh.train.authorization.vo.AuthAuthorityVo;
import com.zhh.train.authorization.vo.AuthUserVo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/25 7:35 下午
 */
@Service
public class UserServiceImpl implements UserService {

    private static final Long EXPIRE_TIME = 60000L;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AuthorityRepository authorityRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("not found user " + username));
        Set<AuthAuthorityVo> authAuthorityVos = new HashSet<>();
        if (user.getUsername().equalsIgnoreCase("sadmin")) {
            List<Authority> authorities = authorityRepository.findAll();
            for (Authority authority : authorities) {
                AuthAuthorityVo authorityVo = new AuthAuthorityVo(authority.getId(), authority.getName(), authority.getName());
                authAuthorityVos.add(authorityVo);
            }
            List<Role> roles = roleRepository.findAll();
            for (Role role : roles) {
                AuthAuthorityVo authorityVo = new AuthAuthorityVo(role.getId(), "ROLE_" + role.getName(), role.getName());
                authAuthorityVos.add(authorityVo);
            }
            user.setLastLoginTime(new Date());
            userRepository.save(user);
            return new AuthUserVo(user.getId(), user.getUsername(), user.getPassword(), user.getStatus(), authAuthorityVos);
        }
        if (user.getLastLoginTime() != null && (System.currentTimeMillis() - user.getLastLoginTime().getTime()) > EXPIRE_TIME) {
            user.setStatus(StatusEnum.EXPIRED);
            userRepository.save(user);
        } else if (user.getStatus() == StatusEnum.ENABLED) {
            user.setLastLoginTime(new Date());
            userRepository.save(user);
        }
        List<Authority> userAuthorities = user.getAuthorities();
        for (Authority authority : userAuthorities) {
            AuthAuthorityVo authorityVo = new AuthAuthorityVo(authority.getId(), authority.getName(), authority.getName());
            authAuthorityVos.add(authorityVo);
        }
        List<Role> userRoles = user.getRoles();
        for (Role role : userRoles) {
            List<Authority> roleAuthorities = role.getAuthorities();
            for (Authority authority : roleAuthorities) {
                AuthAuthorityVo authorityVo = new AuthAuthorityVo(authority.getId(), authority.getName(), authority.getName());
                authAuthorityVos.add(authorityVo);
            }
            AuthAuthorityVo authorityVo = new AuthAuthorityVo(role.getId(), "ROLE_" + role.getName(), role.getName());
            authAuthorityVos.add(authorityVo);
        }
        AuthUserVo vo = new AuthUserVo(user.getId(), user.getUsername(), user.getPassword(), user.getStatus(), authAuthorityVos);
        return vo;
    }

    @Override
    @Transactional
    public Long createUser(UserSaveDto dto) {
        User user = modelMapper.map(dto, User.class);
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(user);
        return user.getId();
    }
}
