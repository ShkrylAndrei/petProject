package ru.shkryl.petavito.service;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.shkryl.petavito.entity.Advertisment;
import ru.shkryl.petavito.entity.Subscribe;
import ru.shkryl.petavito.entity.User;
import ru.shkryl.petavito.entityview.AdvertismentView;
import ru.shkryl.petavito.entityview.SubscribeView;
import ru.shkryl.petavito.repository.SubscribeRepository;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SubscribeService {

    private final AdvertismentService advertismentService;
    private final SubscribeRepository subscribeRepository;

    private final UserService userService;

    public SubscribeService(AdvertismentService advertismentService, SubscribeRepository subscribeRepository, UserService userService) {
        this.advertismentService = advertismentService;
        this.subscribeRepository = subscribeRepository;
        this.userService = userService;
    }


    public List<SubscribeView> findAll() {
        List<Subscribe> list = subscribeRepository.findAll();
        return list.stream()
                .map(subs -> new SubscribeView(subs))
                .collect(Collectors.toList());
    }

    public SubscribeView findById(UUID id) {
        Subscribe subs = subscribeRepository
                .findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new SubscribeView(subs);
    }

//    public SubscribeView save(SubscribeView subscribeView) {
//        User user = userService.getById(advertismentView.getUserId());
//        Advertisment adv = advertismentView.convertToAdvertisment(user);
//        adv = subscribeRepository.save(adv);
//        return new AdvertismentView(adv);
//    }

    public void deleteById(UUID id){
        subscribeRepository.deleteById(id);
    }
}
