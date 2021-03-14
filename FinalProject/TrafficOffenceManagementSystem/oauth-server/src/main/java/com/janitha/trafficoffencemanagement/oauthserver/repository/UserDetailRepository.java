package com.janitha.trafficoffencemanagement.oauthserver.repository;
import com.janitha.trafficoffencemanagement.oauthserver.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;



public interface UserDetailRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String name);

}
