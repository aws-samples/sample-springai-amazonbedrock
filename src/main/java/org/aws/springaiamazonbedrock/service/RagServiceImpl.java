package org.aws.springaiamazonbedrock.service;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.ai.embedding.EmbeddingResponse;

import java.util.List;
import java.util.Map;

@Service
public class RagServiceImpl implements RagService {


    @Override
    public Map<String, EmbeddingResponse> embed(String messages) {
        return Map.of();
    }

    @Override
    public String getResponse(String question) {
        return "";
    }
}
