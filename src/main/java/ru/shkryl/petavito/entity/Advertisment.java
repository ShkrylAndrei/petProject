package ru.shkryl.petavito.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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

    private Date datecreate;//createDate - localDate

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid")
    private User user;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Subscribe> subscribeList = new ArrayList<>();//delete ArrayList


//    public Advertisment(String shorttext, String longtext, String type, Date datecreate, User user) {
//        this.shorttext = shorttext;
//        this.longtext = longtext;
//        this.type = type;
//        this.datecreate = datecreate;
//        this.user = user;
//    }+


}
