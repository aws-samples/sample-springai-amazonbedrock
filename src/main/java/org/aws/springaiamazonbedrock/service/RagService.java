package org.aws.springaiamazonbedrock.service;

import org.springframework.ai.embedding.EmbeddingResponse;

import java.util.Map;

public interface RagService {

    /**
     * This method is used to embed a list of messages and return their embeddings.
     *
     * @param messages A map where the key is the message identifier and the value is the message content.
     * @return A map where the key is the message identifier and the value is the embedding response.
     */
    Map<String, EmbeddingResponse> embed(String messages);

    /**
     * This method is used to retrieve a response for a given question.
     *
     * @param question The question to be answered.
     * @return The response to the question.
     */
    String getResponse(String question);
}
