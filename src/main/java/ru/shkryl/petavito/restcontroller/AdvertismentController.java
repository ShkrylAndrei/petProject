package ru.shkryl.petavito.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shkryl.petavito.dto.ResponceCRUDDTO;
import ru.shkryl.petavito.entity.Advertisment;
import ru.shkryl.petavito.entity.User;
import ru.shkryl.petavito.entityview.AdvertismentView;
import ru.shkryl.petavito.service.AdvertismentService;
import ru.shkryl.petavito.service.UserService;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/advert", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdvertismentController {

    private final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    private final UserService userService;
    private final AdvertismentService advertismentService;

    public AdvertismentController(UserService userService, AdvertismentService advertismentService) {
        this.userService = userService;
        this.advertismentService = advertismentService;
    }


    @GetMapping
    public List<AdvertismentView> getAll(){
         return advertismentService.findAll();
    }

    @GetMapping("{id}")
    public AdvertismentView get(@PathVariable String id){
         return advertismentService.findById(UUID.fromString(id));
    }

    @PostMapping()
    public ResponseEntity<AdvertismentView> create(@RequestBody AdvertismentView advertismentView){
        AdvertismentView adv = advertismentService.save(advertismentView);
        return new ResponseEntity<>(adv, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public AdvertismentView update(@PathVariable String id,
                                                   @RequestBody AdvertismentView advertismentView){
        AdvertismentView byId = advertismentService.findById(UUID.fromString(id));
        byId.setShorttext(advertismentView.getShorttext());
        byId.setLongtext(advertismentView.getLongtext());
        byId.setDatecreate(advertismentView.getDatecreate());
        byId.setType(advertismentView.getType());
        advertismentService.save(byId);
        return byId;
    }



    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable String id){
        advertismentService.deleteById(UUID.fromString(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }





    //TODO сделать через POST
    //TODO объявление передавать в теле запроса, оно может быть длинным
    //TODO и заголовок объявления тоже передавать в теле
    @GetMapping("/create")
    public ResponseEntity<ResponceCRUDDTO> create(@RequestParam(value = "shorttext", required = true) String shorttext,
                                                  @RequestParam(value = "type", required = true) Long type) {
        LOGGER.debug("Поступил запрос на добавление объявления");
        LOGGER.debug("Параметр shorttext = " + shorttext);
        LOGGER.debug("Параметр type = " + type);

        //TODO поставить текущую дату
        Date datecreate = null;
        ResponceCRUDDTO responceCRUDDTO = new ResponceCRUDDTO();

        //TODO переделать в UUID и чтобы брался извне
        //и сначала происходила проверка, авторизован ли пользователь
        if (userService.checkLoginAndPasswordOfUser("Andrei", "1")) {
            User user = userService.returnUserAfterAuthorithation("Andrei", "1");
            //Создаем сущность объявление
            Advertisment advertisment = new Advertisment(shorttext,
                    null,
                    "Продажа автомобилей",
                    datecreate,
                    user
            );
            //Сохраняем ее в базе
            //TODO раскомментировать и изменить
            //advertismentService.saveAdvertisment(advertisment);
            //Если успешно то логируем успех, если неудача, то перехватываем adviceController

            responceCRUDDTO.message = "Объявление успешно добавлено в базу";
            LOGGER.debug("Объявление успешно добавлено в базу");
        } else {
            responceCRUDDTO.message = "Вы не авторизованы, добавление невозможно";
            LOGGER.debug("Вы не авторизованы, добавление невозможно");
        }

        return new ResponseEntity<>(responceCRUDDTO, HttpStatus.OK);
    }
}
