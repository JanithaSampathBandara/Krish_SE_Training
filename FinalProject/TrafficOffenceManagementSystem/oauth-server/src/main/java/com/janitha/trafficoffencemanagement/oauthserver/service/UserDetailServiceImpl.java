package com.janitha.trafficoffencemanagement.oauthserver.service;

import com.janitha.trafficoffencemanagement.oauthserver.model.AuthUserDetail;
import com.janitha.trafficoffencemanagement.oauthserver.model.User;
import com.janitha.trafficoffencemanagement.oauthserver.repository.UserDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service()
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserDetailRepository userDetailsRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

        System.out.println(userName);
        Optional<User> user = userDetailsRepository.findByUsername(userName);
        user.orElseThrow(() -> new UsernameNotFoundException("Username Or Password Is Wrong"));

        UserDetails userDetails = new AuthUserDetail(user.get());
        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;
    }

}
