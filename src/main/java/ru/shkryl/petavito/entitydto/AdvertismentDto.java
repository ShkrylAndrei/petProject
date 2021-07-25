package ru.shkryl.petavito.entitydto;

import lombok.*;
import ru.shkryl.petavito.entity.Advertisment;
import ru.shkryl.petavito.entity.User;

import java.util.Date;
import java.util.UUID;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor

public class AdvertismentDto {

    private UUID id;

    private String shorttext;

    private String longtext;

    private String type;

    private Date datecreate;


    private UUID userId;

    public AdvertismentDto(Advertisment advertisment) {
        this.id = advertisment.getId();
        this.shorttext = advertisment.getShorttext();
        this.longtext = advertisment.getLongtext();
        this.type = advertisment.getType();
        this.datecreate = advertisment.getDatecreate();
        this.userId = advertisment.getUser().getId();
    }

    public Advertisment convertToAdvertisment(User user){
        return new Advertisment(getId(),
                getShorttext(),
                getLongtext(),
                getType(),
                getDatecreate(),
                user, null);
    }
}
