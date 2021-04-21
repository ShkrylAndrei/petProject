package ru.shkryl.petavito.service;

import org.springframework.stereotype.Service;
import ru.shkryl.petavito.entity.Advertisment;
import ru.shkryl.petavito.entity.User;
import ru.shkryl.petavito.repository.AdvertismentRepository;
import ru.shkryl.petavito.repository.UserRepository;

@Service
public class AdvertismentService {

    private final AdvertismentRepository advertismentRepository;

    public AdvertismentService(AdvertismentRepository advertismentRepository) {
        this.advertismentRepository = advertismentRepository;
    }

    public void saveAdvertisment(Advertisment newAdvertisment){
       advertismentRepository.save(newAdvertisment);
    }
}
