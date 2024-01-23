package by.itacademy.itbootcamp.api;

import by.itacademy.itbootcamp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends JpaRepository<User, Long> {
    User findByEmail (String email);








}
