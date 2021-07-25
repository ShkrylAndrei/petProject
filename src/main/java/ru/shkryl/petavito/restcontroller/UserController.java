package ru.shkryl.petavito.restcontroller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shkryl.petavito.entitydto.UserDto;
import ru.shkryl.petavito.service.UserService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final Logger LOGGER = LoggerFactory.getLogger(AdvertismentController.class);

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserDto> getAll(){
        return userService.findAll();
    }

    @GetMapping("{id}")
    public UserDto get(@PathVariable String id){
        return userService.findById(UUID.fromString(id));
    }

    @PostMapping()
    public ResponseEntity<UserDto> create(@RequestBody UserDto userDto){
        UserDto usr = userService.save(userDto);
        return new ResponseEntity<>(usr, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public UserDto update(@PathVariable String id,
                          @RequestBody UserDto userDto){
        UserDto byId = userService.findById(UUID.fromString(id));
        byId.setLogin(userDto.getLogin());
        byId.setPassword(userDto.getPassword());
        byId.setEmail(userDto.getEmail());
        userService.save(byId);
        return byId;
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable String id) {
        userService.deleteById(UUID.fromString(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
