package ru.shkryl.petavito.service;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;
import ru.shkryl.petavito.entity.Advertisment;
import ru.shkryl.petavito.entity.User;
import ru.shkryl.petavito.entitydto.AdvertismentDto;

import java.util.UUID;
import java.util.stream.Stream;

public interface AdvertismentService {
    Stream<Advertisment> findAll();

    AdvertismentDto findById(UUID id);

    AdvertismentDto save(AdvertismentDto advertismentDto);

    void deleteById(UUID id);
}
