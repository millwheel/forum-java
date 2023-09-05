package forum.message.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import forum.message.dto.TestMessageDto;
import org.springframework.stereotype.Service;

@Service
public class FirebaseService {

    public String sendNotification(TestMessageDto testMessageDto) {
        try {
            Message message = Message.builder()
                    .setToken(testMessageDto.getToken())
                    .putData("title", testMessageDto.getTitle())
                    .putData("body", testMessageDto.getBody())
                    .build();

            String response = FirebaseMessaging.getInstance().send(message);
            return response;

        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            return "Failed";
        }
    }
}
