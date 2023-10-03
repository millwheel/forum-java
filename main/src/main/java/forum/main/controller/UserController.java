package forum.main.controller;

import forum.main.dto.TokenRequestDto;
import forum.main.dto.UserRequestDto;
import forum.main.dto.UserResponseDto;
import forum.main.entity.User;
import forum.main.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


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
    public List<UserResponseDto> getUsers(){
        List<User> users = userService.readUsers();
        List<UserResponseDto> userResponseDtoList = new ArrayList<>();
        for (User user: users){
            UserResponseDto userResponseDto = new UserResponseDto(user);
            userResponseDtoList.add(userResponseDto);
        }
        return userResponseDtoList;
    }

    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.OK)
    public UserResponseDto getUser(@PathVariable(name = "userId") Long userId){
        User user = userService.readUser(userId);
        return new UserResponseDto(user);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long createUser(@RequestBody UserRequestDto userRequestDto){
        return userService.createUser(userRequestDto.getUsername(), userRequestDto.getKeywordList());
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public String addDeviceToken(@RequestBody TokenRequestDto tokenRequestDto){
        return userService.addToken(tokenRequestDto.getUserId(), tokenRequestDto.getToken());
    }
}
