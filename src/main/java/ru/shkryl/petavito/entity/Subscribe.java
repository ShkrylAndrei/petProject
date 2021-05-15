package ru.shkryl.petavito.entity;


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
@Table(name = "subscribe")
public class Subscribe {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "advertismentid")
    private Advertisment advertisment;

    @ManyToMany
    @JoinTable(name="user_subscribe",
            joinColumns = @JoinColumn(name = "subscribeid"),
            inverseJoinColumns = @JoinColumn(name="userid")
    )
    private List<User> userList = new ArrayList<>();


}
