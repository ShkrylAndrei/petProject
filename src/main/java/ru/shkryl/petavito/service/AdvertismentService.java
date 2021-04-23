package ru.shkryl.petavito.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.shkryl.petavito.entity.Advertisment;
import ru.shkryl.petavito.entity.User;
import ru.shkryl.petavito.entityview.AdvertismentView;
import ru.shkryl.petavito.repository.AdvertismentRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AdvertismentService {

    private final AdvertismentRepository advertismentRepository;
    private final UserService userService;

    public AdvertismentService(AdvertismentRepository advertismentRepository, UserService userService) {
        this.advertismentRepository = advertismentRepository;
        this.userService = userService;
    }

    public List<AdvertismentView> findAll() {
        List<Advertisment> list = advertismentRepository.findAll();
        return list.stream()
                .map(adv -> new AdvertismentView(adv))
                .collect(Collectors.toList());
    }

    public AdvertismentView findById(UUID id) {
        Advertisment adv = advertismentRepository
                .findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new AdvertismentView(adv);
    }

    public AdvertismentView save(AdvertismentView advertismentView) {
        User user = userService.getById(advertismentView.getUserId());
        Advertisment adv = advertismentView.convertToAdvertisment(user);
        adv = advertismentRepository.save(adv);
        return new AdvertismentView(adv);
    }

    public void deleteById(UUID id){
        advertismentRepository.deleteById(id);
    }
}
