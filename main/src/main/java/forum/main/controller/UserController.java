package forum.main.controller;

import forum.main.dto.UserRequestDto;
import forum.main.dto.UserResponseDto;
import forum.main.entity.User;
import forum.main.service.UserService;
import org.springframework.web.bind.annotation.*;


@RestController("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserResponseDto getUser(@RequestParam long id){
        User user = userService.readUser(id).orElseGet(null);
        return new UserResponseDto(user.getUsername(), user.getTagList());
    }


    @PostMapping
    public long createUser(@RequestBody UserRequestDto userRequestDto){
        return userService.createUser(userRequestDto.getUsername(), userRequestDto.getKeywordList());
    }
}
