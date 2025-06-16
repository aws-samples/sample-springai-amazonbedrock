package org.aws.springaiamazonbedrock.service;

import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.embedding.EmbeddingResponse;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RagServiceImpl implements RagService {

    private final EmbeddingModel embeddingModel;
    private final ChatModel chatModel;
    private final SimpleVectorStore vectorStore;

    @Value("classpath:templates/rag-prompt-template.st")
    private Resource promptTemplate;

    public RagServiceImpl(EmbeddingModel embeddingModel, ChatModel chatModel, SimpleVectorStore vectorStore) {
        this.embeddingModel = embeddingModel;
        this.chatModel = chatModel;
        this.vectorStore = vectorStore;
    }

    // This is a placeholder implementation. You can implement the actual logic here.
    @Override
    public Map<String, EmbeddingResponse> embed(String messages) {
        // Logic to embed messages and return their embeddings
        EmbeddingResponse embeddingResponse = this.embeddingModel.embedForResponse(List.of(messages));
        return Map.of("embedding", embeddingResponse); // Replace with actual implementation
    }

    @Override
    public String getResponse(String question) {
        List<Document> documents = vectorStore.similaritySearch(SearchRequest.builder().
                query(question).topK(5).build());
        List<String> contents = documents.stream().map(Document::getFormattedContent).toList();
        PromptTemplate template = new PromptTemplate(promptTemplate);
        Prompt prompt = template.create(
                Map.of("question", question, "documents", String.join("\n", contents))
        );
        ChatResponse response = chatModel.call(prompt);
        return response.getResult().getOutput().getText();
    }
}
