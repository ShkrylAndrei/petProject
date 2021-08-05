package ru.shkryl.petavito.service.implementation;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.shkryl.petavito.entity.Advertisment;
import ru.shkryl.petavito.entity.User;
import ru.shkryl.petavito.entitydto.AdvertismentDto;
import ru.shkryl.petavito.mapper.AdvertismentMapper;
import ru.shkryl.petavito.repository.AdvertismentRepository;
import ru.shkryl.petavito.service.AdvertismentService;

import java.util.UUID;
import java.util.stream.Stream;

@Service
//вместо конструктора использовать Lombok RequiredConstruct
//по хорошему классы сервиса должны реализовывать интерфейсы, должен быть контракт
public class AdvertismentServiceImpl implements AdvertismentService {

    private final AdvertismentRepository advertismentRepository;
    private final UserService userService;
    private final AdvertismentMapper advertismentMapper;

    public AdvertismentServiceImpl(AdvertismentRepository advertismentRepository, UserService userService, AdvertismentMapper advertismentMapper) {
        this.advertismentRepository = advertismentRepository;
        this.userService = userService;
        this.advertismentMapper = advertismentMapper;
    }

    //Это не правильно, List<Advertisment> лишняя, можно просто вызвать findall и вернуть stream.
    //нужно ли возращать stream если я не буду его маппить, рефактор
    //findAll должен вернуть stream и должна быть ленивая загрузка
    //
    public Stream<Advertisment> findAll() {
        return advertismentRepository.findAll()
                .stream();

    }

    //не нужно findById метод называть, назвать его find
    //почитать про аннотацию ResponseStatus(RescponseStatusException)
    //AdvertismentView в DTO
    //ассоциируется что View передается в контроллер подумать над названием и логикой
    //с DTO должен работать только контроллер, сервис не должен

    public AdvertismentDto findById(UUID id) {
        Advertisment adv = advertismentRepository
                .findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        AdvertismentDto advertismentDto = new AdvertismentDto();
        advertismentMapper.map(adv,advertismentDto);
        return  advertismentDto;

        //        return new AdvertismentDto(adv);

    }

    //View переименовать в DTO, перетащить в пакет DTO
    public AdvertismentDto save(AdvertismentDto advertismentDto) {
        User user = userService.getById(advertismentDto.getUserId());
        Advertisment adv = advertismentDto.convertToAdvertisment(user);
        adv = advertismentRepository.save(adv);
        return new AdvertismentDto(adv);
    }

    public void deleteById(UUID id){
        advertismentRepository.deleteById(id);
    }
}
