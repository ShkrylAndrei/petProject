package ru.shkryl.petavito.service.implementation;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.shkryl.petavito.entity.User;
import ru.shkryl.petavito.entitydto.UserDto;
import ru.shkryl.petavito.repository.UserRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserDto> findAll() {
        List<User> list = userRepository.findAll();
        return list.stream()
                .map(usr -> new UserDto(usr))
                .collect(Collectors.toList());
    }

    public User getById(UUID id){
        return userRepository.findById(id)
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));
    }


    public boolean checkLoginAndPasswordOfUser(String login, String password) {
        User user = userRepository.findByLoginAndPassword(login, password);
        if (user != null) {
            return true;
        } else {
            return false;
        }
    }

    public User returnUserAfterAuthorithation(String login, String password) {
        User user = userRepository.findByLoginAndPassword(login, password);
        if (user == null) {
            new Exception("Авторизация не подтверждена, пользователь под которым вы пытаетесь работать, отсутсвует в базе");
        }

        return user;
    }

    public UserDto findById(UUID id) {
        User usr = userRepository
                .findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new UserDto(usr);
    }

    public UserDto save(UserDto userDto) {
        User usr = userDto.convertToUser(userDto);
        usr = userRepository.save(usr);
        return new UserDto(usr);
    }

    public void deleteById(UUID id) {
        userRepository.deleteById(id);
    }
}
