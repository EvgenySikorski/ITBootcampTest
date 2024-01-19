package by.itacademy.itbootcamp.impl;

import by.itacademy.itbootcamp.core.dto.UserCreateDTO;
import by.itacademy.itbootcamp.core.enums.EUserRole;
import by.itacademy.itbootcamp.api.IUserDao;
import by.itacademy.itbootcamp.entity.User;
import by.itacademy.itbootcamp.api.IUserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService implements IUserService {

    private static final String FIELD_NAME_FIRSTNAME = "firstname";
    private static final String FIELD_NAME_LASTNAME = "lastname";
    private static final String FIELD_NAME_SURNAME = "surname";
    private static final String FIELD_NAME_EMAIL = "email";
    private static final String FIELD_NAME_ROLE = "role";

    private final IUserDao userDao;

    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public User save(UserCreateDTO item) {
        User userCreat = new User();
        if (item != null){
            userCreat.setFirstname(item.getFirstname());
            userCreat.setLastname(item.getLastname());
            userCreat.setSurname(item.getSurname());
            userCreat.setEmail(item.getEmail());
            userCreat.setRole(EUserRole.valueOf(item.getRole()));

        } else {
            throw new NullPointerException("LocationCreateDTO is null");
        }
        return this.userDao.save(userCreat);
    }

    @Override
    public Page<User> getPage(PageRequest pageRequest) {
        return this.userDao.findAll(pageRequest);
    }

    @Override
    public List<User> getList() {
        return this.userDao.findAll();
    }
}
