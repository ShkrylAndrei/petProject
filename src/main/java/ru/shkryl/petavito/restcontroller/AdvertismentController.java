package ru.shkryl.petavito.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.shkryl.petavito.entitydto.AdvertismentDto;
import ru.shkryl.petavito.service.implementation.AdvertismentServiceImpl;
import ru.shkryl.petavito.service.implementation.UserService;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

//конструктор Lombok
@RestController
@RequestMapping(value = "/advert", produces = MediaType.APPLICATION_JSON_VALUE)
@Validated
public class AdvertismentController {

    //Логер зачем, используем SLF4j
    private final Logger LOGGER = LoggerFactory.getLogger(AdvertismentController.class);

    private final UserService userService;
    private final AdvertismentServiceImpl advertismentService;

    public AdvertismentController(UserService userService, AdvertismentServiceImpl advertismentService) {
        this.userService = userService;
        this.advertismentService = advertismentService;
    }

    //Лучше сущности в DTO маппить здесь, использовать ковертер через ORIKA
    @GetMapping()
    public List<AdvertismentDto> getAll() {
//        Stream<Advertisment> advertismentStream = advertismentService.findAll();
//        List<Advertisment> advertismentList = advertismentStream
//                .collect(Collectors.toList());
//        List<AdvertismentDto> advertismentDtoList = advertismentList
//                .forEach(adv -> advertismentMapper.map(adv, new AdvertismentDto()));
//        return advertismentDtoList;

        return advertismentService.findAll()
                .map(adv -> new AdvertismentDto(adv))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    //public AdvertismentDto get(@PathVariable @Valid @Max(2) String id) {
    public AdvertismentDto get(@PathVariable
                               @Valid
                               @Pattern(regexp = "[0-9a-f]{8}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{4}-[0-9a-f]{12}", message = "must be of 6 char/digit")
                                       String id) {
        return advertismentService.findById(UUID.fromString(id));
    }

    @PostMapping()
    public ResponseEntity<AdvertismentDto> create(@RequestBody AdvertismentDto advertismentDto) {
        AdvertismentDto adv = advertismentService.save(advertismentDto);
        return new ResponseEntity<>(adv, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public AdvertismentDto update(@PathVariable String id,
                                  @RequestBody AdvertismentDto advertismentDto) {
        AdvertismentDto byId = advertismentService.findById(UUID.fromString(id));
        byId.setShorttext(advertismentDto.getShorttext());
        byId.setLongtext(advertismentDto.getLongtext());
        byId.setDatecreate(advertismentDto.getDatecreate());
        byId.setType(advertismentDto.getType());
        advertismentService.save(byId);
        return byId;
    }

    //ResponceEntitty параметризировать
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable String id) {
        advertismentService.deleteById(UUID.fromString(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
