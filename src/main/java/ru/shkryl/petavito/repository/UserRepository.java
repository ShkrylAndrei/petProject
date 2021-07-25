package ru.shkryl.petavito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shkryl.petavito.entity.User;

import javax.transaction.Transactional;
import java.util.UUID;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByLoginAndPassword(String login,String password);
    User findByLogin(String login);

}
