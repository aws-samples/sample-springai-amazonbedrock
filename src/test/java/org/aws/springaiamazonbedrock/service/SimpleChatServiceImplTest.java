package org.aws.springaiamazonbedrock.service;

import org.aws.springaiamazonbedrock.model.CityTemperatureRequest;
import org.aws.springaiamazonbedrock.model.CityTemperatureResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SimpleChatServiceImplTest {

    @Autowired
    private  SimpleChatService chatService;

    //@Test
    void chat() {
        // Given
        String prompt = "Tell me a dad joke";

        // When
        String response = chatService.chat(prompt);

        // Then
        System.out.println("Response: " + response);
        assertNotNull(response);
    }

    //@Test
    void chatWithTemplate() {
        // Given
        String prompt = "Kolkata";

        // When
        String response = chatService.chatWithTemplate(prompt);

        // Then
        System.out.println("Response: " + response);
        assertNotNull(response);
    }

    //@Test
    void chatWithFormat() {
        // Given
        String prompt = "Kolkata";

        // When
        CityTemperatureResponse response= chatService.chatWithFormat(new CityTemperatureRequest(prompt));

        // Then
        System.out.println("Response: " + response.temperature());
        assertNotNull(response);
    }
}