package org.aws.springaiamazonbedrock.service;


import org.aws.springaiamazonbedrock.model.CityTemperatureRequest;
import org.aws.springaiamazonbedrock.model.CityTemperatureResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SimpleChatServiceImpl implements SimpleChatService {

    @Override
    public String chat(String prompt) {
        return "";
    }

    @Override
    public String chatWithTemplate(String city) {
        return "";
    }

    @Override
    public CityTemperatureResponse chatWithFormat(CityTemperatureRequest cityTemperatureRequest) {
        return null;
    }
}
