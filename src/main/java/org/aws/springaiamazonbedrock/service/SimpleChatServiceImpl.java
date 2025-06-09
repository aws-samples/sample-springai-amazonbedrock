package org.aws.springaiamazonbedrock.service;

import org.aws.springaiamazonbedrock.model.CityTemperatureRequest;
import org.aws.springaiamazonbedrock.model.CityTemperatureResponse;
import org.springframework.ai.chat.messages.SystemMessage;
import org.springframework.ai.chat.messages.UserMessage;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.converter.BeanOutputConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SimpleChatServiceImpl implements SimpleChatService {

    private final ChatModel chatModel;
    private static final String SYSTEM_PROMPT = """
            You are a helpful AI assistant.\s
            You will be given a task and you will answer based on the task.
            You will be given a history of previous messages.
            You will be given a current message.
            You will answer based on the current message and the history.\s""";

    @Value("classpath:templates/prompt-templates.st")
    private Resource systemPromptResource;

    @Value("classpath:templates/prompt-template-format.st")
    private Resource systemPromptResourceWithFormat;

    public SimpleChatServiceImpl(ChatModel chatModel) {
        this.chatModel = chatModel;
    }

    public String chat(String user_prompt) {
        Prompt prompt = new Prompt(List.of(
                new SystemMessage(SYSTEM_PROMPT),
                new UserMessage(user_prompt)
        ));
        return chatModel.call(prompt).getResult().getOutput().getText();
    }

    @Override
    public String chatWithTemplate(String city) {
        PromptTemplate promptTemplate = new PromptTemplate(systemPromptResource);
        Prompt prompt = promptTemplate.create(Map.of("city", city));
        return chatModel.call(prompt).getResult().getOutput().getText();
    }

    @Override
    public CityTemperatureResponse chatWithFormat(CityTemperatureRequest cityTemperatureRequest) {
        BeanOutputConverter<CityTemperatureResponse> converter = new BeanOutputConverter<>(CityTemperatureResponse.class);
        PromptTemplate promptTemplate = new PromptTemplate(systemPromptResourceWithFormat);
        Prompt prompt = promptTemplate.create(Map.of("city", cityTemperatureRequest.getCity(), "format", converter.getFormat()));
        return converter.convert(chatModel.call(prompt).getResult().getOutput().getText());
    }
}
