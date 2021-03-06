package org.softuni.finalpoject.config;

import com.cloudinary.Cloudinary;
import org.softuni.finalpoject.constants.Constants;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Configuration
public class ApplicationCloudConfiguration {

    @Value("${cloudinary.cloud-name}")
    private String cloudApiName;
    @Value("${cloudinary.api-key}")
    private String cloudApiKey;
    @Value("${cloudinary.api-secret}")
    private String cloudApiSecret;

    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(new HashMap<String, Object>(){{
            put(Constants.CLOUD_NAME, cloudApiName);
            put(Constants.API_KEY, cloudApiKey);
            put(Constants.API_SECRET, cloudApiSecret);
        }});
    }
}
