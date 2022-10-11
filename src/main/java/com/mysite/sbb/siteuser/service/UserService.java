package com.mysite.sbb.siteuser.service;

import com.mysite.sbb.siteuser.dao.UserReository;
import com.mysite.sbb.siteuser.domain.SiteUser;
import com.mysite.sbb.util.DataNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserReository userReository;

    private final PasswordEncoder passwordEncoder;

    public SiteUser create(String username, String email, String password) {
                SiteUser user = SiteUser.builder()
                .username(username)
                .email(email)
                .password(passwordEncoder.encode(password))
                .build();


        userReository.save(user);
        return user;
    }

    public SiteUser getUser(String username) {
        Optional<SiteUser> siteUser = userReository.findByUsername(username);

        return siteUser.orElseThrow(()->new DataNotFoundException("siteuser not found"));

    }
}
