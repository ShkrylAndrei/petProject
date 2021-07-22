package ru.shkryl.petavito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shkryl.petavito.entity.Advertisment;
import ru.shkryl.petavito.entity.User;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional//Для чего повесил @Transactional уметь объяснить, может CRUD использовать репозиторий
public interface AdvertismentRepository extends JpaRepository<Advertisment, UUID> {
}
