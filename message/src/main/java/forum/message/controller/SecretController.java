package forum.message.controller;

import forum.message.configuration.AwsSecretValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;

@RestController
@RequestMapping("/secret")
@Slf4j
public class SecretController {

    @Autowired
    private SecretsManagerClient secretsManagerClient;

    @GetMapping()
    public String fetchSecret(){
        AwsSecretValue awsSecretValue = new AwsSecretValue(secretsManagerClient);
        String secretValue = awsSecretValue.fetchSecretValue("forum-message-firebase-secret");
        log.info("secret value={}", secretValue);
        return secretValue;
    }
}
