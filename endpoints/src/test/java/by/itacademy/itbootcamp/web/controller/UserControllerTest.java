package by.itacademy.itbootcamp.web.controller;

import by.itacademy.itbootcamp.core.dto.UserCreateDTO;
import by.itacademy.itbootcamp.core.enums.EUserRole;
import by.itacademy.itbootcamp.entity.User;
import by.itacademy.itbootcamp.impl.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;
    @InjectMocks
    private UserController userController;
    @Autowired
    private MockMvc mockMvc;
    private List<User> users;
    private UserCreateDTO userCreateDTO;
    private User user;


    @Before
    public void setup() {
        users = buildListUsers();
        userCreateDTO = buildUserCreatDTO();
        user = buildUser();
    }

    @Test
    public void findAllShouldReturnAllUsers() throws Exception {
        //given
        Mockito.when(userService.getSortList()).thenReturn(users);
        //when
        mockMvc.perform(get("/users").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void saveUserShouldReturnUser() throws Exception {
        //given
        Mockito.when(userService.save(userCreateDTO)).thenReturn(user);
        String body = new ObjectMapper().writeValueAsString(userCreateDTO);

        //when
        mockMvc.perform(post("/users" ).contentType(MediaType.APPLICATION_JSON).content(body))
                .andExpect(status().isOk());
    }

    private List<User> buildListUsers() {
        User one = new User(
                1L,
                "Ivan",
                "Ivanov",
                "Ivanovich",
                "ivan@mail.ru",
                EUserRole.SALE_USER);

        User two = new User(
                1L,
                "Petr",
                "Petrov",
                "Petrovich",
                "petr@mail.ru",
                EUserRole.ADMINISTRATOR);

        return List.of(one, two);
    }

    private UserCreateDTO buildUserCreatDTO() {
        return new UserCreateDTO(
                "Ivan",
                "Ivanov",
                "Ivanovich",
                "ivan@mail.ru",
                "Administrator"
        );
    }

    private User buildUser() {
        return new User(
                1L,
                "Petr",
                "Petrov",
                "Petrovich",
                "petr@mail.ru",
                EUserRole.ADMINISTRATOR);
    }
}
