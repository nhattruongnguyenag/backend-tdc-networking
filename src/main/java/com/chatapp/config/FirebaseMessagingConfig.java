package com.chatapp.config;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;

@Configuration
public class FirebaseMessagingConfig {
    @Bean
    public FirebaseMessaging firebaseMessaging() throws IOException {
        InputStream credentialStream = new ClassPathResource("tdcsocialnetwork-firebase-adminsdk-drcvj-d6ec902025.json").getInputStream();
        GoogleCredentials googleCredentials = GoogleCredentials.fromStream(credentialStream);
        FirebaseOptions firebaseOptions = FirebaseOptions.builder()
                .setCredentials(googleCredentials).build();
        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "tdc-social-network");
        return FirebaseMessaging.getInstance(app);
    }
}
