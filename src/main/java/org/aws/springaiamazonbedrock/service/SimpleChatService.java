package org.aws.springaiamazonbedrock.service;

import org.aws.springaiamazonbedrock.model.CityTemperatureRequest;
import org.aws.springaiamazonbedrock.model.CityTemperatureResponse;

public interface SimpleChatService {

    /**
     * This method is used to send a chat message to the AI model and get a response.
     *
     * @param prompt  The prompt message to be sent to the AI model.
     * @return The response from the AI model.
     */
    String chat(String prompt);

    /**
     * This method is used to send a chat message to the AI model with a template and get a response.
     *
     * @param city The city name to be used in the template.
     * @return The response from the AI model.
     */
    String chatWithTemplate(String city);

    /**
     * This method is used to send a chat message to the AI model with a request object and get a response.
     *
     * @param cityTemperatureRequest The request object containing the city name.
     * @return The response from the AI model.
     */
    CityTemperatureResponse chatWithFormat(CityTemperatureRequest cityTemperatureRequest);
}
