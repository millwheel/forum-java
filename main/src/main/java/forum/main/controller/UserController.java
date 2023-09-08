package forum.main.controller;

import forum.main.dto.TokenRequestDto;
import forum.main.dto.UserRequestDto;
import forum.main.dto.UserResponseDto;
import forum.main.entity.User;
import forum.main.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto getUser(@RequestParam Long userId){
        User user = userService.readUser(userId);
        return new UserResponseDto(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createUser(@RequestBody UserRequestDto userRequestDto){
        return userService.createUser(userRequestDto.getUsername(), userRequestDto.getKeywordList());
    }

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public String addDeviceToken(@RequestBody TokenRequestDto tokenRequestDto){
        return userService.addToken(tokenRequestDto.getUserId(), tokenRequestDto.getToken());
    }
}
