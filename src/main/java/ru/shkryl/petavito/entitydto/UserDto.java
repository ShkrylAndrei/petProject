package ru.shkryl.petavito.entitydto;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.shkryl.petavito.entity.User;

import java.util.UUID;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class UserDto {
    private UUID id;

    private String login;

    private String password;

    private String email;

    private String role;

    public UserDto(String login, String password, String email, String role) {
        this.login = login;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public UserDto(User usr) {
        this.login = usr.getLogin();
        this.password = usr.getPassword();
        this.email = usr.getEmail();
        this.role = usr.getRole();
    }

    public User convertToUser(UserDto userDto) {
        return new User(userDto.getLogin(),
                userDto.getPassword(),
                userDto.getEmail(),
                userDto.getRole());
    }
}
