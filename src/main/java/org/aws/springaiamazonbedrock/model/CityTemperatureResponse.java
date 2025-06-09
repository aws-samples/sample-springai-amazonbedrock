package org.aws.springaiamazonbedrock.model;

import com.fasterxml.jackson.annotation.JsonPropertyDescription;

public record CityTemperatureResponse(@JsonPropertyDescription("This is the temperature of the city") String temperature) {
}
