package ru.shkryl.petavito.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shkryl.petavito.entityview.AdvertismentView;
import ru.shkryl.petavito.service.AdvertismentService;
import ru.shkryl.petavito.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/advert", produces = MediaType.APPLICATION_JSON_VALUE)
public class AdvertismentController {

    private final Logger LOGGER = LoggerFactory.getLogger(AdvertismentController.class);

    private final UserService userService;
    private final AdvertismentService advertismentService;

    public AdvertismentController(UserService userService, AdvertismentService advertismentService) {
        this.userService = userService;
        this.advertismentService = advertismentService;
    }


    @GetMapping
    public List<AdvertismentView> getAll() {
        return advertismentService.findAll();
    }

    @GetMapping("{id}")
    public AdvertismentView get(@PathVariable String id) {
        return advertismentService.findById(UUID.fromString(id));
    }

    @PostMapping()
    public ResponseEntity<AdvertismentView> create(@RequestBody AdvertismentView advertismentView) {
        AdvertismentView adv = advertismentService.save(advertismentView);
        return new ResponseEntity<>(adv, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public AdvertismentView update(@PathVariable String id,
                                   @RequestBody AdvertismentView advertismentView) {
        AdvertismentView byId = advertismentService.findById(UUID.fromString(id));
        byId.setShorttext(advertismentView.getShorttext());
        byId.setLongtext(advertismentView.getLongtext());
        byId.setDatecreate(advertismentView.getDatecreate());
        byId.setType(advertismentView.getType());
        advertismentService.save(byId);
        return byId;
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable String id) {
        advertismentService.deleteById(UUID.fromString(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
