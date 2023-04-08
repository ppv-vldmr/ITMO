package ru.akirakozov.sd.refactoring;

import org.eclipse.jetty.server.Server;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ServerTest {
    private static final String CONNECTION_URL = "jdbc:sqlite:test.db";

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8081;
    private static final String SERVER_URL = String.format("http://%s:%s", SERVER_HOST, SERVER_PORT);

    private static final HttpClient client = HttpClient.newHttpClient();

    private static Server server;

    @BeforeAll
    public static void init() throws Exception {
        server = Main.createServer(CONNECTION_URL, SERVER_PORT);
        server.start();
    }

    @AfterAll
    public static void destroy() throws Exception {
        try (Connection c = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "DROP TABLE IF EXISTS PRODUCT";
            Statement stmt = c.createStatement();

            stmt.executeUpdate(sql);
            stmt.close();
        }
        server.stop();
    }

    @AfterEach
    public void clearTable() {
        try (Connection c = DriverManager.getConnection(CONNECTION_URL)) {
            String sql = "DELETE FROM PRODUCT";
            Statement stmt = c.createStatement();

            stmt.executeUpdate(sql);
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void addProductTest() {
        assertResponse(
                buildUri(
                        List.of(Main.ADD_PRODUCT_ENDPOINT),
                        Map.of(
                                "name", "test-product",
                                "price", String.valueOf(Long.MAX_VALUE)
                        )
                ),
                "OK\n",
                200
        );
    }

    @Test
    public void addProductWithNegativePriceTest() {
        assertResponse(
                buildUri(
                        List.of(Main.ADD_PRODUCT_ENDPOINT),
                        Map.of(
                                "name", "test-product",
                                "price", "-42"
                        )
                ),
                "OK\n",
                200
        );
    }

    @Test
    public void addProductWithoutPriceTest() {
        assertResponse(
                buildUri(
                        List.of(Main.ADD_PRODUCT_ENDPOINT),
                        Map.of(
                                "name", "test-product"
                        )
                ),
                500
        );
    }

    @Test
    public void addProductWithoutNameTest() {
        assertResponse(
                buildUri(
                        List.of(Main.ADD_PRODUCT_ENDPOINT),
                        Map.of(
                                "price", "1000"
                        )
                ),
                "OK\n",
                200
        );

        try (Connection c = DriverManager.getConnection(CONNECTION_URL)) {
            Statement stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM PRODUCT WHERE NAME = ''");

            while (rs.next()) {
                assertEquals("", rs.getString("name"));
                assertEquals(1000, rs.getInt("price"));
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void addProductWithInvalidPriceTest() {
        assertResponse(
                buildUri(
                        List.of(Main.ADD_PRODUCT_ENDPOINT),
                        Map.of(
                                "name", "test-product",
                                "price", "invalid"
                        )
                ),
                500
        );

        assertResponse(
                buildUri(
                        List.of(Main.ADD_PRODUCT_ENDPOINT),
                        Map.of(
                                "name", "test-product",
                                "price", "42.13"
                        )
                ),
                500
        );
    }

    @Test
    public void getProductTest() {
        Map<String, Long> products = createTestProducts();
        fillProductTable(products);
        assertResponse(
                buildUri(
                        List.of(Main.GET_PRODUCTS_ENDPOINT),
                        Map.of()
                ),
                createGetProductsExpectedHtml(products),
                200
        );
    }

    @Test
    public void queryMaxTest() {
        Map<String, Long> products = createTestProducts();
        fillProductTable(products);

        long maxPrice = Long.MIN_VALUE;
        String maxName = "";
        for (var entry : products.entrySet()) {
            if (entry.getValue() > maxPrice) {
                maxPrice = entry.getValue();
                maxName = entry.getKey();
            }
        }

        assertResponse(
                buildUri(
                        List.of(Main.QUERY_ENDPOINT),
                        Map.of("command", "max")
                ),
                String.format(
                        """
                                <html><body>
                                <h1>Product with max price: </h1>
                                %s\t%s</br>
                                </body></html>
                                """,
                        maxName,
                        maxPrice
                ),
                200
        );

    }

    @Test
    public void queryMinTest() {
        Map<String, Long> products = createTestProducts();
        fillProductTable(products);

        long minPrice = Long.MAX_VALUE;
        String minName = "";
        for (var entry : products.entrySet()) {
            if (entry.getValue() < minPrice) {
                minPrice = entry.getValue();
                minName = entry.getKey();
            }
        }

        assertResponse(
                buildUri(
                        List.of(Main.QUERY_ENDPOINT),
                        Map.of("command", "min")
                ),
                String.format(
                        """
                                <html><body>
                                <h1>Product with min price: </h1>
                                %s\t%s</br>
                                </body></html>
                                """,
                        minName,
                        minPrice
                ),
                200
        );
    }

    @Test
    public void querySumTest() {
        Map<String, Long> products = createTestProducts();
        fillProductTable(products);

        long sum = products.values().stream().mapToLong(Long::longValue).sum();

        assertResponse(
                buildUri(
                        List.of(Main.QUERY_ENDPOINT),
                        Map.of("command", "sum")
                ),
                String.format(
                        """
                                <html><body>
                                Summary price:\s
                                %s
                                </body></html>
                                """,
                        sum
                ),
                200
        );
    }

    @Test
    public void queryCountTest() {
        Map<String, Long> products = createTestProducts();
        fillProductTable(products);

        int count = products.size();

        assertResponse(
                buildUri(
                        List.of(Main.QUERY_ENDPOINT),
                        Map.of("command", "count")
                ),
                String.format(
                        """
                                <html><body>
                                Number of products:\s
                                %s
                                </body></html>
                                """,
                        count
                ),
                200
        );
    }

    @Test
    public void queryUnknownCommandTest() {
        assertResponse(
                buildUri(
                        List.of(Main.QUERY_ENDPOINT),
                        Map.of("command", "unknown")
                ),
                "Unknown command: unknown\n",
                200
        );
    }

    @Test
    public void queryMaxWithEmptyTableTest() {
        assertResponse(
                buildUri(
                        List.of(Main.QUERY_ENDPOINT),
                        Map.of("command", "max")
                ),
                """
                        <html><body>
                        <h1>Product with max price: </h1>
                        </body></html>
                        """,
                200
        );
    }

    @Test
    public void queryMinWithEmptyTableTest() {
        assertResponse(
                buildUri(
                        List.of(Main.QUERY_ENDPOINT),
                        Map.of("command", "min")
                ),
                """
                        <html><body>
                        <h1>Product with min price: </h1>
                        </body></html>
                        """,
                200
        );
    }

    @Test
    public void querySumWithEmptyTableTest() {
        assertResponse(
                buildUri(
                        List.of(Main.QUERY_ENDPOINT),
                        Map.of("command", "sum")
                ),
                String.format(
                        """
                                <html><body>
                                Summary price:\s
                                %s
                                </body></html>
                                """,
                        0
                ),
                200
        );
    }

    @Test
    public void queryCountWithEmptyTableTest() {
        assertResponse(
                buildUri(
                        List.of(Main.QUERY_ENDPOINT),
                        Map.of("command", "count")
                ),
                String.format(
                        """
                                <html><body>
                                Number of products:\s
                                %s
                                </body></html>
                                """,
                        0
                ),
                200
        );
    }

    private static Map<String, Long> createTestProducts() {
        Map<String, Long> products = new LinkedHashMap<>();
        products.put("I", 100L);
        products.put("LOVE", 200L);
        products.put("SOFTWARE", 300L);
        products.put("DESIGN", 400L);
        return products;
    }

    private static void fillProductTable(Map<String, Long> products) {
        try (Connection c = DriverManager.getConnection(CONNECTION_URL)) {
            Statement stmt = c.createStatement();

            StringJoiner sj = new StringJoiner(",");
            products.forEach((k, v) -> sj.add(String.format("(\"%s\", %s)", k, v)));
            stmt.executeUpdate(String.format("INSERT INTO PRODUCT (NAME, PRICE) VALUES %s", sj));

            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private HttpResponse<String> sendRequest(String uri) {
        try {
            return client.send(
                    HttpRequest
                            .newBuilder(new URI(uri))
                            .GET()
                            .build(),
                    HttpResponse.BodyHandlers.ofString()
            );
        } catch (IOException | InterruptedException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    private String buildUri(List<String> path, Map<String, String> params) {
        StringJoiner uri = new StringJoiner("/");
        uri.add(SERVER_URL);
        path.forEach(uri::add);
        if (params.isEmpty()) {
            return uri.toString();
        }

        StringJoiner paramsUri = new StringJoiner("&", "?", "");
        params.forEach((k, v) -> paramsUri.add(String.format("%s=%s", k, v)));

        return uri.toString() + paramsUri;
    }

    private void assertResponse(String url, String expectedBody, int expectedCode) {
        HttpResponse<String> response = sendRequest(url);
        assertEquals(expectedCode, response.statusCode());
        assertEquals(expectedBody, response.body());
    }

    private void assertResponse(String url, int expectedCode) {
        HttpResponse<String> response = sendRequest(url);
        assertEquals(expectedCode, response.statusCode());
    }

    private static String createGetProductsExpectedHtml(Map<String, Long> products) {
        StringBuilder sb = new StringBuilder();
        sb.append("<html><body>\n");
        products.forEach((k, v) -> sb.append(String.format("%s\t%d</br>\n", k, v)));
        sb.append("</body></html>\n");
        return sb.toString();
    }
}