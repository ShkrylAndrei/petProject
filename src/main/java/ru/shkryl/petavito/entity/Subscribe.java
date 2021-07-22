package ru.shkryl.petavito.entity;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@Table(name = "subscribe")
public class Subscribe {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne//Не обязательно двухнаправленную связь определиться нужна ли bidirectional
    @JoinColumn(name = "advertismentid")
    private Advertisment advertisment;

    @ManyToMany
    @JoinTable(name="user_subscribe",
            joinColumns = @JoinColumn(name = "subscribeid"),
            inverseJoinColumns = @JoinColumn(name="userid")//user_id snakeCase поменять название в таблице
    )
    private List<User> userList = new ArrayList<>();//лишний new ArrayList


}
