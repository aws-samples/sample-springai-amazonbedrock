package org.aws.springaiamazonbedrock.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "app.ai.vectorstore")
public class VectorStoreProperties {

    private String path;
    private List<Resource> documents;

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<Resource> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Resource> documents) {
        this.documents = documents;
    }
}
