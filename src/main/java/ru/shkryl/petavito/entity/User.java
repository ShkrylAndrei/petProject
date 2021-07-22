package ru.shkryl.petavito.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "userinfo")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String login;

    private String password;

    private String email;

    @OneToMany(mappedBy = "user")
    private List<Advertisment> advertisment;//advertisement правильно

    @ManyToMany(mappedBy = "userList")
    private List<Subscribe> subscribeList = new ArrayList<>();//ArrayList убрать

    //Конструктор убрать
    public User(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }
}
