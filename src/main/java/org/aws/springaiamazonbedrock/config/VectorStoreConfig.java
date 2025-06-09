package org.aws.springaiamazonbedrock.config;

import org.springframework.ai.document.Document;
import org.springframework.ai.embedding.EmbeddingModel;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.util.List;

@Configuration
public class VectorStoreConfig {

    /**
     * Configures a SimpleVectorStore bean that uses an EmbeddingModel to process documents.
     * It loads existing vector data from a file or creates a new vector store from provided documents.
     *
     * @param embeddingModel the embedding model used for vectorization
     * @param vectorStoreProperties properties containing the path and documents for the vector store
     * @return a SimpleVectorStore instance
     */
    @Bean
    public SimpleVectorStore  simpleVectorStore(EmbeddingModel embeddingModel,
                                                VectorStoreProperties vectorStoreProperties) {
        SimpleVectorStore  vectorStore = SimpleVectorStore.builder(embeddingModel).build();
        File vectorStoreFile = new File(vectorStoreProperties.getPath());
        if (vectorStoreFile.exists()) {
            vectorStore.load(vectorStoreFile);
        } else {
            vectorStoreProperties.getDocuments().forEach(document -> {
                try {
                    TikaDocumentReader documentReader = new TikaDocumentReader(document);
                    List<Document> documents = documentReader.get();
                    TextSplitter textSplitter = new TokenTextSplitter(500, 200, 5, 10000, true);
                    List<Document> splitDocuments = textSplitter.apply(documents);
                    splitDocuments.forEach(System.out::println);
                    vectorStore.add(splitDocuments);
                } catch (Exception e) {
                    throw new RuntimeException("Failed to add document: " + document.getFilename(), e);
                }
            });
            vectorStore.save(vectorStoreFile);
        }
        return vectorStore;
    }
}
