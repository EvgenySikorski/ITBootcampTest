package by.itacademy.itbootcamp.web;

import by.itacademy.itbootcamp.api.IUserService;
import by.itacademy.itbootcamp.core.dto.UserCreateDTO;
import by.itacademy.itbootcamp.core.dto.UserDTO;
import by.itacademy.itbootcamp.entity.User;
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
        UserDTO userDTO = new UserDTO(fio, createUser.getEmail(), createUser.getRole().toString());
        return new ResponseEntity<>(userDTO, HttpStatus.CREATED);
    }

//    @GetMapping(consumes = "application/json", produces = "application/json" )
//    public ResponseEntity<PageDTO<UserDTO>> page(@RequestParam(defaultValue = "0") int page,
//                                                 @RequestParam(defaultValue = "20") int size) {
//        PageRequest pageRequest = PageRequest.of(page, size);
//        Page<User> users = userService.getPage(pageRequest);
//
//        PageDTO<UserDTO> pageOfUserDTO = new PageDTO<>(users.getNumber(), users.getSize(),
//                users.getTotalPages(), users.getTotalElements(), users.isFirst(),
//                users.getNumberOfElements(), users.isLast(),
//                users.get().map(u -> conversionService.convert(u, UserDTO.class)).toList());
//
//        return new ResponseEntity<>(pageOfUserDTO, HttpStatus.OK);
//    }

    @GetMapping(consumes = "application/json", produces = "application/json" )
    public List<UserDTO> list(){
        List<User> all = this.userService.getList();
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
