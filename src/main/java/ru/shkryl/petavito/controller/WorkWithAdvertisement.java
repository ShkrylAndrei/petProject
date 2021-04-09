package ru.shkryl.petavito.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController("WorkWithAdvertisement")
public class WorkWithAdvertisement {

    @GetMapping(value = "/getadv/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getadv(@PathVariable(required = true) int id){
        return "Вы запросили объявление с номером "+id;
    }
}
