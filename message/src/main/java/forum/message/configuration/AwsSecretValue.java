package forum.message.configuration;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.secretsmanager.SecretsManagerClient;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueRequest;
import software.amazon.awssdk.services.secretsmanager.model.GetSecretValueResponse;

public class AwsSecretValue {

    private final SecretsManagerClient secretsManager;

    public AwsSecretValue(SecretsManagerClient secretsManager) {
        this.secretsManager = secretsManager;
    }
    public String fetchSecretValue(String secretName) {
        SecretsManagerClient secretsManager = SecretsManagerClient.builder()
                .region(Region.AP_NORTHEAST_2)
                .build();

        GetSecretValueRequest getSecretValueRequest = GetSecretValueRequest.builder()
                .secretId(secretName)
                .build();

        GetSecretValueResponse getSecretValueResponse = null;
        try {
            getSecretValueResponse = secretsManager.getSecretValue(getSecretValueRequest);
        } catch (Exception e) {
            // Handle exceptions
            e.printStackTrace();
        }

        // Depending on whether the secret is a string or binary, one of these will be populated
        if (getSecretValueResponse.secretString() != null) {
            return getSecretValueResponse.secretString();
        }
        return null;  // or handle binary case if relevant
    }
}
