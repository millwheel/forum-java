package forum.message.configuration;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import jakarta.annotation.PostConstruct;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

@Configuration
public class FirebaseConfig {

    @Value("${firebase.config}")
    private String firebaseConfig;
    @Value("${firebase.project.id}")
    private String firebaseProjectId;

    @PostConstruct
    public void initialize() {
        try {
            InputStream serviceAccount;
            // Check if firebaseConfig is a file path or JSON content
            if (new File(firebaseConfig).exists()) {
                // Treat as a path to a file
                serviceAccount = new FileInputStream(firebaseConfig);
            } else {
                // Treat as the JSON content itself
                JSONObject json = new JSONObject(firebaseConfig);
                JSONObject firebaseConfigJson = json.getJSONObject("firebase_config");
                String firebaseConfigString = firebaseConfigJson.toString();
                serviceAccount = new ByteArrayInputStream(firebaseConfigString.getBytes(StandardCharsets.UTF_8));
            }

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setProjectId(firebaseProjectId)
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}