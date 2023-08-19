package forum.main.configuration;

import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.rds.AmazonRDS;
import com.amazonaws.services.rds.AmazonRDSClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import com.amazonaws.auth.AWSCredentials;

@Configuration
public class AwsConfig {

    @Value("${aws.accessKey}")
    private String awsAccessKey;
    @Value("${aws.secretKey}")
    private String awsSecretKey;
    @Value("${aws.region}")
    private String awsRegion;


    public AWSCredentials awsCredentials() {
        return new BasicAWSCredentials(awsAccessKey, awsSecretKey);
    }

    public AWSCredentialsProvider awsCredentialsProvider() {
        return new AWSStaticCredentialsProvider(awsCredentials());
    }

    public AmazonRDS amazonRDS(){
        return AmazonRDSClientBuilder.standard().withCredentials(awsCredentialsProvider())
                .withRegion(awsRegion).build();
    }
}
