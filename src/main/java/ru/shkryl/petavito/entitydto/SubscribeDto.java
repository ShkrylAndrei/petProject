package ru.shkryl.petavito.entitydto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.shkryl.petavito.entity.Subscribe;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor

public class SubscribeDto {

    private UUID id;

    private UUID advertismentId;

    private List<UUID> userList = new ArrayList<>();

    public SubscribeDto(Subscribe subscribe) {
        this.id = subscribe.getId();
        this.advertismentId = subscribe.getAdvertisment().getId();
        subscribe.getUserList().stream()
                .forEach(usr->this.userList.add(usr.getId()));
    }

//    public Advertisment convertToAdvertisment(User user){
//        return new Advertisment(getId(),
//                getShorttext(),
//                getLongtext(),
//                getType(),
//                getDatecreate(),
//                user, null);
//    }
}
