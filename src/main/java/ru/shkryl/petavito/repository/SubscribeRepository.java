package ru.shkryl.petavito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shkryl.petavito.entity.Advertisment;
import ru.shkryl.petavito.entity.Subscribe;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public interface SubscribeRepository extends JpaRepository<Subscribe, UUID> {
}
