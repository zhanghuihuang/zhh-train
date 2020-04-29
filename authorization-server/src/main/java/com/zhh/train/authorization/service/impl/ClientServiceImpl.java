package com.zhh.train.authorization.service.impl;

import com.zhh.train.authorization.entity.Authority;
import com.zhh.train.authorization.entity.Client;
import com.zhh.train.authorization.entity.Role;
import com.zhh.train.authorization.repository.ClientRepository;
import com.zhh.train.authorization.service.ClientService;
import com.zhh.train.authorization.vo.AuthAuthorityVo;
import com.zhh.train.authorization.vo.AuthClientVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author : page
 * @project : zhh-train
 * @description :
 * @date : 2020/4/27 10:20 上午
 */
@Service
public class ClientServiceImpl implements ClientService {
    @Autowired
    private ClientRepository clientRepository;

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    @Override
    @Transactional
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        System.out.println(atomicInteger.getAndIncrement());
        Client client = clientRepository.findByClientName(clientId).orElseThrow(() -> new ClientRegistrationException("not found client " + clientId));
        Set<AuthAuthorityVo> authAuthorityVos = new HashSet<>();
        AuthClientVo authClientVo = new AuthClientVo(client.getClientName(), client.getPassword(), client.getResourceIds(), client.getScope(), client.getGrantTypes(), client.getRegisteredRedirectUri(), authAuthorityVos, client.getAccessTokenValiditySeconds(), client.getRefreshTokenValiditySeconds(), null);
        List<Role> roles = client.getRoles();
        for (Role role : roles) {
            authAuthorityVos.add(new AuthAuthorityVo(role.getId(), "ROLE_" + role.getName(), role.getDesc()));
            List<Authority> authorities = role.getAuthorities();
            for (Authority authority : authorities) {
                authAuthorityVos.add(new AuthAuthorityVo(authority.getId(), authority.getName(), authority.getDesc()));
            }
        }
        return authClientVo;
    }


}
