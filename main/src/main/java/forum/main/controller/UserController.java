package forum.main.controller;

import forum.main.dto.UserRequestDto;
import forum.main.dto.UserResponseDto;
import forum.main.entity.User;
import forum.main.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserResponseDto getUser(@RequestParam Long userId){
        User user = userService.readUser(userId).orElse(null);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found");
        }
        return new UserResponseDto(user);
    }

    @PostMapping
    public Long createUser(@RequestBody UserRequestDto userRequestDto){
        return userService.createUser(userRequestDto.getUsername(), userRequestDto.getKeywordList());
    }
}
