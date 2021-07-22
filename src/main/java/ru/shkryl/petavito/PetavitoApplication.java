package ru.shkryl.petavito;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.UUID;

@SpringBootApplication
public class PetavitoApplication {

    public static void main(String[] args) {
        SpringApplication.run(PetavitoApplication.class, args);
	}

}

//Spring Security
//Validation в DTO
//И запросы в репозитории поинтересней, нативные запросы и JPQL можно CRITERIAAPI
