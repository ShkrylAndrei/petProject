package ru.shkryl.petavito.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.DateTimeException;
import java.util.Date;
import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@Table(name = "advertisment")
public class Advertisment {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    private String shorttext;

    private String longtext;

    private String type;

    private Date datecreate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id")
    private User user;

    public Advertisment(String shorttext, String longtext, String type, Date datecreate, User user) {
        this.shorttext = shorttext;
        this.longtext = longtext;
        this.type = type;
        this.datecreate = datecreate;
        this.user = user;
    }
}