package by.itacademy.itbootcamp.web.controller;

import by.itacademy.itbootcamp.api.IUserService;
import by.itacademy.itbootcamp.core.dto.PageDTO;
import by.itacademy.itbootcamp.core.dto.UserCreateDTO;
import by.itacademy.itbootcamp.core.dto.UserDTO;
import by.itacademy.itbootcamp.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<UserDTO> save(@RequestBody UserCreateDTO userCreateDTO){

        User createUser = this.userService.save(userCreateDTO);
        String fio = createUser.getFirstname() + createUser.getLastname() + createUser.getSurname();
        UserDTO userDTO = new UserDTO(fio, createUser.getEmail(), createUser.getRole().getUserRole().toString());
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/page", consumes = "application/json", produces = "application/json" )
    public ResponseEntity<PageDTO<UserDTO>> page(@RequestParam(defaultValue = "0") int page){

        int size = 10;

        Sort.TypedSort<User> user = Sort.sort(User.class);
        Sort sort = user.by(User::getEmail).ascending();

        PageRequest pageRequest = PageRequest.of(page, size, sort);
        Page<User> users = userService.getPage(pageRequest);

        PageDTO<UserDTO> pageOfUserDTO = new PageDTO<>(users.getNumber(), users.getSize(),
                users.getTotalPages(), users.getTotalElements(), users.isFirst(),
                users.getNumberOfElements(), users.isLast(),
                users.get().map(u -> new UserDTO((u.getFirstname() + " " + u.getLastname() + " " + u.getSurname()), u.getEmail(), u.getRole().toString())).toList());

        return new ResponseEntity<>(pageOfUserDTO, HttpStatus.OK);
    }

    @GetMapping(consumes = "application/json", produces = "application/json" )
    public List<UserDTO> list(){
        List<User> all = this.userService.getSortList();
        List<UserDTO> listDTO = new ArrayList<>();
        for (User user : all) {
            listDTO.add(new UserDTO(
                    user.getFirstname() + " " + user.getLastname() + " " + user.getSurname(),
                    user.getEmail(),
                    user.getRole().toString()));
        }
        return listDTO;
    }
}
