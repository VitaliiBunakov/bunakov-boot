package com.bunakov.controller;

import com.bunakov.dto.UserWithoutPass;
import com.bunakov.exeptions.UserAlreadyExistsExeption;
import com.bunakov.model.User;
import com.bunakov.service.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;


@RestController
public class UserController {
    @Autowired
    UserServiceImpl userRepository;
    private  final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private ObjectMapper mapper = new ObjectMapper();

    @PostMapping("/api/user")
    public ResponseEntity<String> saveU(@RequestBody String userinp) throws IOException {

        User user = mapper.readValue(userinp, User.class);
        if (!(user.getUserName().isEmpty()) & !(user.getPlainTextPassword().isEmpty())) {
            user.setHashedPassword(passwordEncoder.encode(user.getPlainTextPassword()));
            user.setId(UUID.randomUUID().toString());
            System.out.println(user);
            try {
                User serviceUser = userRepository.createUser(user);
                UserWithoutPass userWithoutPass = new UserWithoutPass(serviceUser);
                String uWPJson = mapper.writeValueAsString(userWithoutPass);
                return new ResponseEntity<String>(uWPJson, HttpStatus.OK);

            } catch (UserAlreadyExistsExeption userAlreadyExistsExeption) {
                userAlreadyExistsExeption.printStackTrace();
                Map<String,String > errResponseBody = new HashMap<>();
                errResponseBody.put("code","USER_ALREADY_EXISTS");
                errResponseBody.put("description", "A user with the given username already exists");
                String jERBody = mapper.writeValueAsString(errResponseBody);
                return new ResponseEntity<String>( jERBody,HttpStatus.CONFLICT);
            }
        }
        return new ResponseEntity<String>("User data is unreadable!", HttpStatus.BAD_REQUEST);

    }

    @GetMapping("/api/user")
    String getAll() throws JsonProcessingException {
        List<User> allusers = userRepository.findAll();
        if (userRepository.findAll() ==null){
            return "no one here!";
        }else{
            List<User> all = userRepository.findAll();
            return mapper.writeValueAsString(all);
        }
    };


}
