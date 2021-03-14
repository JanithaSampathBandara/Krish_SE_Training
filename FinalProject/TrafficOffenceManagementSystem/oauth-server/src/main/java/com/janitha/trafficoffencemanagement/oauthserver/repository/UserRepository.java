package com.janitha.trafficoffencemanagement.oauthserver.repository;

import com.janitha.trafficoffencemanagement.dto.Response;
import com.janitha.trafficoffencemanagement.oauthserver.controller.AuthController;
import com.janitha.trafficoffencemanagement.oauthserver.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Repository
public class UserRepository {

    Logger logger = LoggerFactory.getLogger(UserRepository.class);

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @LoadBalanced
    @Bean
    RestTemplate getRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.build();
    }

    @Autowired
    RestTemplate restTemplate;



    public void addUser(User user, int role) throws DataAccessException {

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        System.out.println(encodedPassword);
        System.out.println(user.toString());
        System.out.println(role);

        jdbcTemplate.execute("INSERT into user (username, password, email, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked) VALUES ('" + user.getUsername() + "','" + encodedPassword + "','" + user.getEmail() + "'," + user.isEnabled() + "," + user.isAccountNonExpired() + "," + user.isCredentialsNonExpired() + "," + user.isAccountNonLocked() + ")");

        int exitingUser = jdbcTemplate.queryForObject("SELECT id from user WHERE username = '" + user.getUsername() + "'", Integer.class);
        System.out.println(exitingUser);

        jdbcTemplate.execute("INSERT into role_user (role_id, user_id) VALUES (" + role + "," + exitingUser + ")");

        try{
            HttpEntity entity = new HttpEntity<>(user);
            Response response = restTemplate.exchange("http://localhost:5656/services/emails/", HttpMethod.POST, entity, Response.class).getBody();
        }catch(RestClientException restClientException){
            logger.error(restClientException.getMessage());
        }


    }

    public void updateUser(User user) throws DataAccessException{

        String encodedPassword = passwordEncoder.encode(user.getPassword());
        jdbcTemplate.execute("UPDATE user SET username = '" + user.getUsername() + "', password = '" +  encodedPassword + "', email = '" + user.getEmail() + "' WHERE username = '" + user.getUsername() + "'");
     //   return true;

    }

    public void deleteUser(String userName) throws DataAccessException{

        int exitingUser = jdbcTemplate.queryForObject("SELECT id from user WHERE username = '" + userName + "'", Integer.class);
        jdbcTemplate.execute("DELETE FROM role_user WHERE user_id = " + exitingUser );
        jdbcTemplate.execute("DELETE FROM user WHERE username = '" + userName + "'");
      //  return true;
    }
}
