package ru.shkryl.petavito.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.shkryl.petavito.dto.ResponceCRUDDTO;
import ru.shkryl.petavito.entity.Advertisment;
import ru.shkryl.petavito.entity.User;
import ru.shkryl.petavito.service.AdvertismentService;
import ru.shkryl.petavito.service.UserService;

import java.util.Date;

@RestController
public class Crud {

    private final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private UserService userService;
    @Autowired
    private AdvertismentService advertismentService;

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
            advertismentService.saveAdvertisment(advertisment);
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
