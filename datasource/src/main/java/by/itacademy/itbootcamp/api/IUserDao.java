package by.itacademy.itbootcamp.api;

import by.itacademy.itbootcamp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface IUserDao extends JpaRepository<User, Long> {

    List<User> findAllOrderByEmail(String email);

    User findByEmail (String email);


}
