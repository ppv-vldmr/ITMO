package org.example.service;

import org.example.client.VkNewsfeedClient;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;


public class VkNewsfeedService {
    private final VkNewsfeedClient vkNewsfeedClient;

    public VkNewsfeedService(VkNewsfeedClient vkNewsfeedClient) {
        this.vkNewsfeedClient = vkNewsfeedClient;
    }

    public List<Integer> findFrequencyHashtag(String query, int hourNumbers) {
        long startTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        return IntStream.range(1, hourNumbers + 1)
                .boxed()
                .map(i ->
                        vkNewsfeedClient.retrieveNewsfeed(
                                query,
                                startTime - TimeUnit.HOURS.toSeconds(i),
                                startTime - TimeUnit.HOURS.toSeconds(i - 1)
                        )
                )
                .map(e -> e.orElse(-1))
                .toList();
    }
}
