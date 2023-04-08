package org.example;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.example.client.VkNewsfeedClient;
import org.example.service.VkNewsfeedService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;


public class VkNewsfeedServiceTest {
    private final static List<Integer> ANSWER_24_HOUR = IntStream.range(1, 25)
            .boxed()
            .toList();
    private final static int HOURS_IN_DAY = 24;
    private VkNewsfeedService vkNewsfeedService;

    @Mock
    private VkNewsfeedClient vkNewsfeedClient;


    @Before
    public void init() {
        vkNewsfeedClient = Mockito.mock(VkNewsfeedClient.class);
        vkNewsfeedService = new VkNewsfeedService(vkNewsfeedClient);
        when(vkNewsfeedClient.retrieveNewsfeed(anyString(), anyLong(), anyLong())).thenAnswer(
                invocation -> {
                    String hashtag = invocation.getArgument(0, String.class);
                    Long startTime = invocation.getArgument(1, Long.class);
                    Long endTime = invocation.getArgument(2, Long.class);

                    long currentTime = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
                    int startDuration = (int) TimeUnit.SECONDS.toHours(currentTime - startTime);
                    int endDuration = (int) TimeUnit.SECONDS.toHours(currentTime - endTime);


                    return Optional.of(
                            ANSWER_24_HOUR.subList(
                                            HOURS_IN_DAY - startDuration,
                                            HOURS_IN_DAY - endDuration
                                    ).stream()
                                    .mapToInt(Integer::intValue)
                                    .sum()
                    );
                }
        );
    }

    @Test
    public void commonTest() {
        for (int i = 1; i < ANSWER_24_HOUR.size() + 1; i++) {
            List<Integer> result = vkNewsfeedService.findFrequencyHashtag("#test", i);
            List<Integer> expected = new ArrayList<>(
                    ANSWER_24_HOUR.subList(
                            ANSWER_24_HOUR.size() - i,
                            ANSWER_24_HOUR.size()
                    )
            );
            Collections.reverse(expected);

            assertEquals(expected, result);
        }
    }
}
