package com.bunakov.controller;

import com.bunakov.BunakovApplication;
import com.bunakov.service.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = BunakovApplication.class)
@WebAppConfiguration
public class UserControllerTest {
    @Mock
    private UserServiceImpl userRepository;
    @InjectMocks
    private UserController userController;
    RestTemplate restTemplate = new RestTemplate();
    ObjectMapper mapper = new ObjectMapper();


    @Test
    public void saveU() throws IOException {
        String testUser = "{\n" +
                "   \"firstName\": \"Some first name\",\n" +
                "   \"lastName\": \"The last name\",\n" +
                "   \"userName\": \"TheUserName\",\n" +
                "   \"password\": \"ThePassword\"\n" +
                "}";
        String url = "http://localhost:8080/api/user";


        ResponseEntity<String> responseEntity1 = restTemplate.exchange(url, HttpMethod.POST, null,
                new ParameterizedTypeReference<String>() {
                });
        String responseEntity = mapper.writeValueAsString(responseEntity1);
        System.out.println(responseEntity);

//        assertThat( containsInAnyOrder("TheUserName", "ThePassword"));
    assertThat((T) responseEntity,containsInAnyOrder("TheUserName","ThePassword"))
    }
}