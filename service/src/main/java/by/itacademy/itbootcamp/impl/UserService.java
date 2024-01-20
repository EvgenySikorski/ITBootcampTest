package by.itacademy.itbootcamp.impl;

import by.itacademy.itbootcamp.core.dto.UserCreateDTO;
import by.itacademy.itbootcamp.core.enums.EUserRole;
import by.itacademy.itbootcamp.api.IUserDao;
import by.itacademy.itbootcamp.entity.User;
import by.itacademy.itbootcamp.api.IUserService;
import by.itacademy.itbootcamp.exception.exceptions.IncorrectDataException;
import by.itacademy.itbootcamp.exception.exceptions.MailAlreadyExistsException;
import by.itacademy.itbootcamp.exception.exceptions.NotValidBodyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserService implements IUserService {

    private static final String FIELD_NAME_FIRSTNAME = "firstname";
    private static final String FIELD_NAME_LASTNAME = "lastname";
    private static final String FIELD_NAME_SURNAME = "surname";
    private static final String FIELD_NAME_EMAIL = "email";
    private static final String FIELD_NAME_ROLE = "role";
    private static final String INCORRECT_DATA = "Неверные данные. Попробуйте еще раз";


    private final IUserDao userDao;

    public UserService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public User save(UserCreateDTO item) {

        validate(item);

        if(this.userDao.findByEmail(item.getEmail()) != null){
            throw new MailAlreadyExistsException(item.getEmail());
        }

        User userCreat = new User();

        if (item != null){
            userCreat.setFirstname(item.getFirstname());
            userCreat.setLastname(item.getLastname());
            userCreat.setSurname(item.getSurname());
            userCreat.setEmail(item.getEmail());

            String roleFromUser = item.getRole();
            String[] splitRole = roleFromUser.split(" ");

            String roleToEnum;
            StringBuilder builder = new StringBuilder();

            for (int i = 0; i < splitRole.length; i++) {
                builder.append(splitRole[i]);
                if (i < splitRole.length - 1){
                    builder.append("_");
                }
            }
            roleToEnum = builder.toString().toUpperCase();

            userCreat.setRole(EUserRole.valueOf(roleToEnum));

        } else {
            throw new NullPointerException(" UserCreateDTO null");
        }
        return this.userDao.save(userCreat);
    }

    @Override
    public Page<User> getPage(PageRequest pageRequest) {
        if (pageRequest.getPageNumber() < 0){
            throw new IncorrectDataException(INCORRECT_DATA);
        }

        return this.userDao.findAll(pageRequest);
    }

    @Override
    public List<User> getSortList() {
        Sort.TypedSort<User> user = Sort.sort(User.class);
        Sort sort = user.by(User::getEmail).ascending();

        return this.userDao.findAll(sort);
    }

    private void validate(UserCreateDTO item){
        Map<String, String> errors = new HashMap<>();

        Pattern pattern = null;
        Matcher matcher = null;
        boolean matches;

        String firstName = item.getFirstname();
        if (firstName == null || "".equals(firstName)){
            errors.put(FIELD_NAME_FIRSTNAME, "Имя не указано");
        } else{
            pattern = Pattern.compile("[A-Za-z]{1,}");
            matcher = pattern.matcher(firstName);
            matches = matcher.matches();
            if (!matches){
                errors.put(FIELD_NAME_FIRSTNAME, "Имя должно содержать только латинские буквы");
            }
        }

        String lastName = item.getLastname();
        if (lastName == null || "".equals(lastName)){
            errors.put(FIELD_NAME_LASTNAME, "Фамилия не указана");
        } else{
            pattern = Pattern.compile("[A-Za-z]{1,}");
            matcher = pattern.matcher(lastName);
            matches = matcher.matches();
            if (!matches){
                errors.put(FIELD_NAME_LASTNAME, "Фамилия должна содержать только латинские буквы");
            }
        }

        String surname = item.getSurname();
        if (surname == null || "".equals(surname)){
            errors.put(FIELD_NAME_SURNAME, "Отчество не указана");
        } else{
            pattern = Pattern.compile("[A-Za-z]{1,}");
            matcher = pattern.matcher(surname);
            matches = matcher.matches();
            if (!matches){
                errors.put(FIELD_NAME_SURNAME, "Отчество должно содержать только латинские буквы");
            }
        }

        String email = item.getEmail();
        if (email == null || "".equals(email)){
            errors.put(FIELD_NAME_EMAIL, "email не указан");
        }

        String role = item.getRole();
        if (role == null || "".equals(role)){
            errors.put(FIELD_NAME_ROLE, "роль не указана");
        } else{
            EUserRole[] arrEnumRole = EUserRole.values();
            String[] arrRole = new String[arrEnumRole.length];

            for (int i = 0; i < arrEnumRole.length; i++) {
                arrRole[i] = arrEnumRole[i].getUserRole();
            }

            if (!Arrays.asList(arrRole).contains(role)){
                errors.put(FIELD_NAME_ROLE, "Роль указана не верно");

            }
        }




//        String role = item.getRole();
//        if (role == null || "".equals(role)){
//            errors.put(FIELD_NAME_ROLE, "Роль не указана");
//        } else {
//            EUserRole[] arrUserRole = EUserRole.values();
//            String[] arrRole = new String[arrUserRole.length];
//            for (int i = 0; i < arrUserRole.length; i++) {
//                arrRole[i] = arrUserRole[i].name();
//            }
//
//            if (!Arrays.asList(arrRole).contains(role)){
//                errors.put(FIELD_NAME_ROLE, "Роль указана не верно");
//            }
//        }
//

        if (!errors.isEmpty()){
            throw new NotValidBodyException(errors);
        }
    }
}
