package ru.shkryl.petavito.security;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.shkryl.petavito.entity.User;
import ru.shkryl.petavito.repository.UserRepository;

import javax.swing.text.IconView;


@AllArgsConstructor
@Service
public class MyUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    ApplicationContext context;


    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(s);
        PasswordEncoder encoder = (PasswordEncoder) context.getBean("encoder");
        UserDetails userDetails = org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getLogin())
//                .password(user.getPassword())
                .password(encoder.encode(user.getPassword()))
                .roles(user.getRole())
                .build();
        return userDetails;
    }


}
