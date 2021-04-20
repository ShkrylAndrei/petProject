package ru.shkryl.petavito.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shkryl.petavito.entity.User;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,String> {
    User findByLoginAndPassword(String login,String password);

}