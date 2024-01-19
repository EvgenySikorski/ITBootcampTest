package by.itacademy.itbootcamp.api;

import by.itacademy.itbootcamp.core.dto.UserCreateDTO;
import by.itacademy.itbootcamp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IUserService {

    User save(UserCreateDTO item);

    Page<User> getPage(PageRequest pageRequest);

    List<User> getList();


}
