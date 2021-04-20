package ru.shkryl.petavito.service;

import org.springframework.stereotype.Service;
import ru.shkryl.petavito.entity.User;
import ru.shkryl.petavito.repository.UserRepository;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public boolean checkLoginAndPasswordOfUser(String login,String password){
        User user = userRepository.findByLoginAndPassword(login,password);
        if (user!=null){
            return true;
        }else {
            return false;
        }
    }
}
