package forum.message.controller;

import forum.message.dto.TestMessageDto;
import forum.message.service.FirebaseService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@Slf4j
public class FcmTestController {
    private FirebaseService firebaseService;

    @PostMapping("/fcm")
    public void createMessage(@RequestBody TestMessageDto testMessageDto){
        String response = firebaseService.sendNotification(testMessageDto);
        log.info("response={}", response);
    }
}
