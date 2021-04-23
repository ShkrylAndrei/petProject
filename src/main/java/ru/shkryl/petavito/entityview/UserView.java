package ru.shkryl.petavito.entityview;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
public class UserView {
    private UUID id;

    private String login;

    private String password;

    private String email;

    public UserView(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }
}