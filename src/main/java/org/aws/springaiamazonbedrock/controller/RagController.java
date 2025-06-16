package org.aws.springaiamazonbedrock.controller;

import org.aws.springaiamazonbedrock.model.ChatResponse;
import org.aws.springaiamazonbedrock.service.RagService;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST Controller for RAG (Retrieval-Augmented Generation) operations
 */
@RestController
@RequestMapping("/api/rag")
public class RagController {

    private final RagService ragService;

    @Autowired
    public RagController(RagService ragService) {
        this.ragService = ragService;
    }

    /**
     * Endpoint to embed messages
     *
     * @param messages The messages to embed
     * @return A map of message identifiers to embedding responses
     */
    @PostMapping("/embed")
    public ResponseEntity<Map<String, EmbeddingResponse>> embed(@RequestBody String messages) {
        Map<String, EmbeddingResponse> embeddings = ragService.embed(messages);
        return ResponseEntity.ok(embeddings);
    }

    /**
     * Simple endpoint to get a response for a question using path variable
     *
     * @param question The question to be answered
     * @return A response entity with the answer
     */
    @PostMapping("/ask")
    public ResponseEntity<ChatResponse> askQuestion(@RequestBody String question) {
        String response = ragService.getResponse(question);
        return ResponseEntity.ok(new ChatResponse(response));
    }
}
