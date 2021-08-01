package ru.shkryl.petavito.restcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.shkryl.petavito.entitydto.UserDto;
import ru.shkryl.petavito.security.MyUserDetailService;
import ru.shkryl.petavito.service.implementation.UserService;

import java.util.List;
import java.util.UUID;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
@AutoConfigureMockMvc(addFilters = false)
class UserControllerTest {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;


    @MockBean
    private UserService userService;

    @MockBean
    private MyUserDetailService myUserDetailService;

    @Test
    void getAllTest() throws Exception {
        List<UserDto> list = createListUserDto();
        Mockito.when(userService.findAll()).thenReturn(list);
        String url = "/user";

        this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(list)));
    }

    @Test
    void getByIdTest() throws Exception {
        UserDto userDto = createUserDto();
        Mockito.when(userService.findById(Mockito.any(UUID.class))).thenReturn(userDto);
        String url = "/user/" + userDto.getId();

        this.mockMvc.perform(get(url))
                .andExpect(status().isOk())
//                .andExpect(jsonPath("id").value(user.getId()))
//                .andExpect(jsonPath("name").value(user.getName()))
//                .andExpect(jsonPath("age").value(user.getAge()))
                .andExpect(content().json(objectMapper.writeValueAsString(userDto)));
    }

    @Test
    void create() throws Exception {
        UserDto userDto = createUserDto();
        Mockito.when(userService.save(userDto)).thenReturn(userDto);
        String url = "/user";
        this.mockMvc.perform(post(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andExpect(content().json(objectMapper.writeValueAsString(userDto)));
    }

    @SneakyThrows
    @Test
    void updateTest() {
        UserDto userDto = createUserDto();
        Mockito.when(userService.findById(Mockito.any(UUID.class))).thenReturn(userDto);
        String url = "/user/" + userDto.getId();
        this.mockMvc.perform(put(url)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isOk())
                .andExpect(content().json(objectMapper.writeValueAsString(userDto)));
    }

    @SneakyThrows
    @Test
    void deleteByIdTest() {
        UserDto userDto = createUserDto();
        Mockito.doNothing().when(userService).deleteById(Mockito.any(UUID.class));
        String url = "/user/" + userDto.getId();
        this.mockMvc.perform(delete(url))
                .andExpect(status().isNoContent());
    }

    private UserDto createUserDto() {
        UserDto userDto = new UserDto("user1", "b", "user1@mail.ru", "USER");
        userDto.setId(UUID.fromString("acd17901-a26f-4d3d-9364-05f3c9c604dc"));
        return userDto;
    }

    private List<UserDto> createListUserDto() {
        UserDto userDto = new UserDto("user2", "b", "user2@mail.ru", "USER");
        userDto.setId(UUID.fromString("a66d1f8e-0ef1-47aa-8fc9-081a6b6b221a"));
        List<UserDto> list = List.of(createUserDto(), userDto);
        return list;
    }


}
