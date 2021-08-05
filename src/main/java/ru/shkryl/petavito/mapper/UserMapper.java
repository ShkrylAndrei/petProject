package ru.shkryl.petavito.mapper;

import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.ConfigurableMapper;
import org.springframework.stereotype.Component;
import ru.shkryl.petavito.entity.User;
import ru.shkryl.petavito.entitydto.UserDto;

@Component
public class UserMapper extends ConfigurableMapper {

    protected void configure(MapperFactory factory) {

        factory.classMap(User.class, UserDto.class)
                .field("id", "id")
                .field("login", "login")
                .field("password", "password")
                .field("email", "email")
                .field("role", "role")
                .byDefault()
                .register();
    }
}
