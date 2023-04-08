package org.example.configuration;

import org.example.client.VkNewsfeedClient;
import org.example.service.ValidationInputService;
import org.example.service.VkNewsfeedService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class VkNewsfeedConfiguration {

    @Bean
    public RestTemplate vkNewsfeedRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public VkNewsfeedClient vkNewsfeedClient(
            @Value("${vk.newsfeed.host}") String vkNewsfeedApiUrl,
            @Value("${vk.newsfeed.api.access.token}") String accessToken,
            @Value("${vk.newsfeed.api.version}") String apiVersion,
            RestTemplate vkNewsfeedRestTemplate
    ) {
        return new VkNewsfeedClient(vkNewsfeedApiUrl, accessToken, apiVersion, vkNewsfeedRestTemplate);
    }

    @Bean
    public ValidationInputService validationInputService() {
        return new ValidationInputService();
    }

    @Bean
    public VkNewsfeedService vkNewsfeedService(VkNewsfeedClient vkNewsfeedClient) {
        return new VkNewsfeedService(vkNewsfeedClient);
    }
}
