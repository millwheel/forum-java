package forum.message.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FirebaseService {

    @Value("${firebase.message.test.token}")
    private String testToken;

    public String sendNotification(String title) {
        try {
            Message message = Message.builder()
                    .setToken(testToken)
                    .putData("title", title)
                    .build();

            String response = FirebaseMessaging.getInstance().send(message);
            return response;

        } catch (FirebaseMessagingException e) {
            e.printStackTrace();
            return "Failed";
        }
    }
}
