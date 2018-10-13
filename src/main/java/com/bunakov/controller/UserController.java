package com.bunakov.controller;

import com.bunakov.dto.UserWithoutPass;
import com.bunakov.exeptions.UserAlreadyExistsExeption;
import com.bunakov.model.User;
import com.bunakov.repository.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;
import java.util.UUID;


@RestController
public class UserController {
    @Autowired
    UserRepository userRepository;
//    @Autowired
    private  final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    ObjectMapper mapper = new ObjectMapper();

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



    @PostMapping("/api/user")
    public String saveUser(@RequestBody String userjson) throws JsonProcessingException {
//    public String saveUser(@RequestBody String userjson) throws JsonProcessingException {
        User user = new User();
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        try {
            user  = mapper.readValue(userjson,User.class);
        } catch (IOException e) {
            e.printStackTrace();
            return "Its was hard, but you did that. its all gone wrong!";
        }
        try {
            if (!(user.getUserName().equals("")) & !userRepository.existsByUserName(user.getUserName())) {
                user.setId(UUID.randomUUID().toString());
                user.setHashedPassword(passwordEncoder.encode(user.getPlainTextPassword()));
                System.out.println(user);
                userRepository.save(user);
                UserWithoutPass userWithoutPass =new UserWithoutPass(user);
                return mapper.writeValueAsString(userWithoutPass);

            } else throw new UserAlreadyExistsExeption(user.getUserName());
        } catch (UserAlreadyExistsExeption userAlreadyExistsExeption) {
            userAlreadyExistsExeption.printStackTrace();
            String errData =
                    "{\"code\":\"USER_ALREADY_EXISTS\","+
                            "\"description\":\"A user with the given username already exists\" } ";
            String errJson = mapper.writeValueAsString(errData);
            return Response.status(Response.Status.CONFLICT).entity(errJson).build();

            return "fuck!  ";
        }

    }


}
