package ru.shkryl.petavito.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shkryl.petavito.entityview.AdvertismentView;
import ru.shkryl.petavito.entityview.SubscribeView;
import ru.shkryl.petavito.service.AdvertismentService;
import ru.shkryl.petavito.service.SubscribeService;
import ru.shkryl.petavito.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/subscribe", produces = MediaType.APPLICATION_JSON_VALUE)
public class SubscribeController {

    private final Logger LOGGER = LoggerFactory.getLogger(SubscribeController.class);

    private final UserService userService;
    private final AdvertismentService advertismentService;
    private final SubscribeService subscribeService;

    public SubscribeController(UserService userService, AdvertismentService advertismentService, SubscribeService subscribeService) {
        this.userService = userService;
        this.advertismentService = advertismentService;
        this.subscribeService = subscribeService;
    }


    @GetMapping
    public List<SubscribeView> getAll() {
        return subscribeService.findAll();
    }

    @GetMapping("{id}")
    public SubscribeView get(@PathVariable String id) {
        return subscribeService.findById(UUID.fromString(id));
    }

//    @PostMapping()
//    public ResponseEntity<SubscribeView> create(@RequestBody AdvertismentView advertismentView) {
//        AdvertismentView adv = advertismentService.save(advertismentView);
//        return new ResponseEntity<>(adv, HttpStatus.CREATED);
//    }
//
//    @PutMapping("{id}")
//    public SubscribeView update(@PathVariable String id,
//                                   @RequestBody AdvertismentView advertismentView) {
//        AdvertismentView byId = advertismentService.findById(UUID.fromString(id));
//        byId.setShorttext(advertismentView.getShorttext());
//        byId.setLongtext(advertismentView.getLongtext());
//        byId.setDatecreate(advertismentView.getDatecreate());
//        byId.setType(advertismentView.getType());
//        advertismentService.save(byId);
//        return byId;
//    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable String id) {
        subscribeService.deleteById(UUID.fromString(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
