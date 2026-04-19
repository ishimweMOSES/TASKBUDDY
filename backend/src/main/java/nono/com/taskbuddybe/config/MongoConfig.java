package nono.com.taskbuddybe.config;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@Configuration
@EnableMongoAuditing
public class MongoConfig {

    @Value("${spring.data.mongodb.uri:mongodb://localhost:27017/task-buddy}")
    private String mongoUri;

    @Bean
    public MongoClientSettings mongoClientSettings() {
        return MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(mongoUri))
            .applyToSslSettings(builder -> {
                builder.enabled(true);
                builder.invalidHostNameAllowed(true);
            })
            .build();
    }
}
