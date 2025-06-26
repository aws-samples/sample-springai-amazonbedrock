package org.aws.springaiamazonbedrock.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.List;

@Configuration
@ConfigurationProperties(prefix = "app.ai.vectorstore")
public class VectorStoreProperties {


}
