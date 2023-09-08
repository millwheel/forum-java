package forum.message.service;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import org.springframework.stereotype.Service;

@Service
public class FirebaseService {

    public String sendNotification(String title, String token) {
        try {
            Message message = Message.builder()
                    .setToken(token)
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
