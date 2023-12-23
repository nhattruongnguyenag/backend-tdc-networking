package com.chatapp.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseMessagingConfig {
    @Value("${app.firebase-configuration-file}")
    private String firebaseConfigPath;
    private Logger logger = LoggerFactory.getLogger(FirebaseMessagingConfig.class);

    @Bean
    public FirebaseMessaging firebaseMessaging() throws IOException {
        InputStream credentialStream = new ClassPathResource(firebaseConfigPath).getInputStream();
        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(credentialStream);
        FirebaseOptions firebaseOptions = FirebaseOptions.builder()
                .setCredentials(googleCredentials).build();
        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "tdc-social-network");
        logger.info("Firebase application has been initialized");
        return FirebaseMessaging.getInstance(app);
    }
}
