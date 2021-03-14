
package com.janitha.trafficoffencemanagement.oauthserver.controller;

import com.janitha.trafficoffencemanagement.oauthserver.model.User;
import com.janitha.trafficoffencemanagement.oauthserver.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value="/oauthuser")
public class AuthController {

    Logger logger = LoggerFactory.getLogger(AuthController.class);

    @Autowired
    UserRepository userRepository;

    @GetMapping(value = "greet")
    public String greeting() {
        return "Helloo";

    }

    @PostMapping(value = "/{role}")
    public boolean addUser(@RequestBody User user, @PathVariable int role) {

        try{
            userRepository.addUser(user, role);
            return true;
        }catch(DataAccessException dataAccessException){
            logger.error(dataAccessException.getMessage());
            return false;
        }
    /*    if(userRepository.addUser(user, role)){
            return true;
        }
        else{
            return false;
        }

     */
    }


    @PutMapping
    public boolean updateUser(@RequestBody User user){

        try{
            userRepository.updateUser(user);
            return true;

        }catch(DataAccessException dataAccessException){
            logger.error(dataAccessException.getMessage());
            return false;
        }

     /*   if(userRepository.updateUser(user)){
            return true;
        }
        else{
            return false;
        }

      */
    }

    @DeleteMapping(value = "/{userName}")
    public boolean deleteUser(@PathVariable String userName){

        try{
            userRepository.deleteUser(userName);
            return true;

        }catch(DataAccessException dataAccessException){
            logger.error(dataAccessException.getMessage());
            return false;
        }

        /* if(userRepository.deleteUser(userName)){
            return true;
        }
        else{
            return false;
        }

        */
    }

}


