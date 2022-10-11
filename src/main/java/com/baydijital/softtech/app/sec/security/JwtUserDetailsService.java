package com.baydijital.softtech.app.sec.security;

import com.baydijital.softtech.app.cus.entity.CusCustomer;
import com.baydijital.softtech.app.cus.service.entityservice.CusCustomerEntityService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author Gokalp on 9/2/22
 */
@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {
    private final CusCustomerEntityService cusCustomerEntityService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Long identityNo = Long.valueOf(username);
        CusCustomer cusCustomer = cusCustomerEntityService.findByIdentityNo(identityNo);
        return JwtUserDetails.create(cusCustomer);
    }

    public UserDetails loadUserById(Long id) {
        CusCustomer cusCustomer = cusCustomerEntityService.getByIdWithControl(id);
        return JwtUserDetails.create(cusCustomer);
    }
}
