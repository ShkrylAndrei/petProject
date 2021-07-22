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
//вместо конструктора использовать Lombok RequiredConstruct
//по хорошему классы сервиса должны реализовывать интерфейсы, должен быть контракт
public class AdvertismentService {

    private final AdvertismentRepository advertismentRepository;
    private final UserService userService;

    public AdvertismentService(AdvertismentRepository advertismentRepository, UserService userService) {
        this.advertismentRepository = advertismentRepository;
        this.userService = userService;
    }

    //Это не правильно, List<Advertisment> лишняя, можно просто вызвать findall и вернуть stream.
    //нужно ли возращать stream если я не буду его маппить, рефактор
    //findAll должен вернуть stream и должна быть ленивая загрузка
    //
    public List<AdvertismentView> findAll() {
        List<Advertisment> list = advertismentRepository.findAll();
        return list.stream()
                .map(adv -> new AdvertismentView(adv))
                .collect(Collectors.toList());
    }

    //не нужно findById метод называть, назвать его find
    //почитать про аннотацию ResponseStatus(RescponseStatusException)
    //AdvertismentView в DTO
    //ассоциируется что View передается в контроллер подумать над названием и логикой
    //с DTO должен работать только контроллер, сервис не должен
    public AdvertismentView findById(UUID id) {
        Advertisment adv = advertismentRepository
                .findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return new AdvertismentView(adv);
    }

    //View переименовать в DTO, перетащить в пакет DTO
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
