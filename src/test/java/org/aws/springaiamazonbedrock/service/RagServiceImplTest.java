package org.aws.springaiamazonbedrock.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest
class RagServiceImplTest {

    @Autowired
    private RagService ragService;

    //@Test
    void embed() {
        // Given
        String messages = "This is a test message for embedding";

        // When
        var response = ragService.embed(messages);

        // Then
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertTrue(response.containsKey("embedding"));
        assertNotNull(response.get("embedding").getResult());
        System.out.println("Embedding Response: " + Arrays.toString(response.get("embedding").getResult().getOutput()));
    }
}