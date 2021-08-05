package ru.shkryl.petavito.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import ru.shkryl.petavito.entity.Advertisment;
import ru.shkryl.petavito.entity.User;
import ru.shkryl.petavito.entitydto.AdvertismentDto;
import ru.shkryl.petavito.entitydto.UserDto;

import java.util.Date;
import java.util.UUID;

@Component
public class AdvertismentMapper extends ConfigurableMapper {

    protected void configure(MapperFactory factory) {

        factory.classMap(Advertisment.class, AdvertismentDto.class)
                .field("id", "id")
                .field("shorttext", "shorttext")
                .field("longtext", "longtext")
                .field("type", "type")
                .field("datecreate", "datecreate")
                .field("user.id", "userId")
                .byDefault()
                .register();
    }
}
