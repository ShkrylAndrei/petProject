package ru.shkryl.petavito.service;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.shkryl.petavito.PetavitoApplication;
import ru.shkryl.petavito.entity.User;
import ru.shkryl.petavito.repository.UserRepository;
import ru.shkryl.petavito.service.implementation.UserService;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PetavitoApplication.class)
class UserDtoServiceTest {

    @MockBean
    private UserRepository userRepository;

    @InjectMocks
    @Autowired
    private UserService userService;

    @Test
    void checkLoginAndPasswordOfUser() {
        String login = "Andrei";
        String password = "1";

        User legalUser = new User("Andrei", "1", "ShkrylAndrei@mail.ru", "USER");

        when(userRepository.findByLoginAndPassword(login, password)).
                thenReturn(legalUser);
        boolean actualResult = userService.checkLoginAndPasswordOfUser(login, password);

        verify(userService).checkLoginAndPasswordOfUser(login, password);
        verify(userRepository).findByLoginAndPassword(login, password);

        assertEquals(true, actualResult);
    }
}
