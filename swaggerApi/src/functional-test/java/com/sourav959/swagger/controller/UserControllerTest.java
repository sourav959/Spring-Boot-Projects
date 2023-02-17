package com.sourav959.swagger.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sourav959.swagger.SwaggerApiApplication;
import com.sourav959.swagger.config.WireMockInitializer;
import com.sourav959.swagger.entity.User;
import com.sourav959.swagger.repository.UserRepository;
import org.json.JSONArray;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, classes = {SwaggerApiApplication.class})
@AutoConfigureMockMvc
@ContextConfiguration(initializers = {WireMockInitializer.class})
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private static User user1, user2;

    private static int userId1, userId2;

    public static final String USER_URI = "/users/";

    @BeforeAll
    static void init() {
        user1 = User.builder()
                .name("Sourav Gupta")
                .email("souravgupta959@gmail.com")
                .gender("m")
                .status("Y")
                .build();
        user2 = User.builder()
                .name("Payal Baldaniya")
                .email("payalbaldaniya@gmail.com")
                .gender("f")
                .status("Y")
                .build();
    }

    @Test
    @Order(1)
    void findAllTest() throws Exception {
        var userDb1 = userRepository.save(user1);
        var userDb2 = userRepository.save(user2);
        userId1 = userDb1.getId();
        userId2 = userDb2.getId();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(USER_URI))
                .andDo(print())
                .andExpect(status().isOk())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        JSONArray jsonArray = new JSONArray(content);
        assertEquals(2, jsonArray.length());
    }

    @Test
    @Order(2)
    void getUserThrowsUserNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(USER_URI + "100"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"status\":400,\"message\":\"User with id::100 not found\"}"));
    }

    @Test
    @Order(3)
    void getUserThrowsInvalidUserIdException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(USER_URI + "0"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"message\":\"getUser.id: must be greater than or equal to 1\"}"));
    }

    @Test
    @Order(4)
    void getUserSuccessfulTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(USER_URI + userId1))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().json("{\"name\":\"Sourav Gupta\",\"email\":\"souravgupta959@gmail.com\",\"gender\":\"m\",\"status\":\"Y\"}"));
    }

    @Test
    @Order(5)
    void deleteUserThrowsUserNotFoundException() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(USER_URI + "100"))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"status\":400,\"message\":\"User with id::100 not found\"}"));
    }

    @Test
    @Order(6)
    void deleteUserSuccessfulTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete(USER_URI + userId1))
                .andDo(print())
                .andExpect(status().isNoContent());
    }

    @Test
    @Order(7)
    void saveUserSuccessfulTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post(USER_URI)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(user1))
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

}
