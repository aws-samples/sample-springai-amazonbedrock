package org.aws.springaiamazonbedrock.model;

import java.util.List;

public class ChatRequest {
    private String prompt;
    private List<ChatMessage> history;

    public ChatRequest() {
    }

    public ChatRequest(String prompt, List<ChatMessage> history) {
        this.prompt = prompt;
        this.history = history;
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public List<ChatMessage> getHistory() {
        return history;
    }

    public void setHistory(List<ChatMessage> history) {
        this.history = history;
    }
}
