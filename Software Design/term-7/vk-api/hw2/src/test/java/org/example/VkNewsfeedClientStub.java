package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.glassfish.grizzly.http.Method;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;
import org.example.client.VkNewsfeedClient;

import com.xebialabs.restito.server.StubServer;
import org.example.exception.VkNewsfeedException;

import java.util.function.Consumer;

import static com.xebialabs.restito.builder.stub.StubHttp.whenHttp;
import static com.xebialabs.restito.semantics.Action.contentType;
import static com.xebialabs.restito.semantics.Action.stringContent;
import static com.xebialabs.restito.semantics.Condition.method;
import static com.xebialabs.restito.semantics.Condition.startsWithUri;
import static org.junit.Assert.assertEquals;


public class VkNewsfeedClientStub {
    private final VkNewsfeedClient vkNewsfeedClient;
    private final static String HOST = "http://localhost";
    private final static int PORT = 25565;

    public VkNewsfeedClientStub() {
        this.vkNewsfeedClient = new VkNewsfeedClient(String.format("%s:%s", HOST, PORT), "", "", new RestTemplate());
    }

    @Test
    public void successTest() {
        Integer expected = 42;
        withStubServer(s -> {
            whenHttp(s)
                    .match(method(Method.GET), startsWithUri("/newsfeed.search"))
                    .then(stringContent(createSuccessResponse(expected)), contentType("application/json"));

            Integer actual = vkNewsfeedClient.retrieveNewsfeed("", 0, 0).orElse(-1);

            assertEquals(expected, actual);
        });
    }

    @Test(expected = VkNewsfeedException.class)
    public void errorTest() {
        withStubServer(s -> {
            whenHttp(s)
                    .match(method(Method.GET), startsWithUri("/newsfeed.search"))
                    .then(
                            stringContent(createErrorResponse(500, "Server error")),
                            contentType("application/json")
                    );

            vkNewsfeedClient.retrieveNewsfeed("", 0, 0);
        });
    }

    private String createSuccessResponse(int totalCount) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            ObjectNode success = mapper.createObjectNode();
            success.put("count", 0);
            success.put("total_count", totalCount);

            ObjectNode response = mapper.createObjectNode();
            response.set("response", success);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private String createErrorResponse(int errorCode, String errorMessage) {
        try {
            ObjectMapper mapper = new ObjectMapper();

            ObjectNode error = mapper.createObjectNode();
            error.put("error_code", errorCode);
            error.put("error_msg", errorMessage);

            ObjectNode response = mapper.createObjectNode();
            response.set("error", error);
            return mapper.writerWithDefaultPrettyPrinter().writeValueAsString(response);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void withStubServer(Consumer<StubServer> callback) {
        StubServer stubServer = null;
        try {
            stubServer = new StubServer(PORT).run();
            callback.accept(stubServer);
        } finally {
            if (stubServer != null) {
                stubServer.stop();
            }
        }
    }
}
