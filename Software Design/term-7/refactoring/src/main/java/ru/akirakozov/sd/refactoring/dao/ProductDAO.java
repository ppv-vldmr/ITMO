package ru.akirakozov.sd.refactoring.dao;

import ru.akirakozov.sd.refactoring.model.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductDAO {
    private static final String INIT_PRODUCT_TABLE_QUERY =
            """
            CREATE TABLE IF NOT EXISTS PRODUCT
            ( ID    INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,
              NAME  TEXT                              NOT NULL,
              PRICE INT                               NOT NULL)
            """;
    private final Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(INIT_PRODUCT_TABLE_QUERY);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Product> getAllProducts() {
        return executeQueryWithResultSet(
                "SELECT * FROM PRODUCT",
                resultSet -> {
                    List<Product> products = new ArrayList<>();
                    while (resultSet.next()) {
                        products.add(new Product(
                                resultSet.getString("name"),
                                resultSet.getInt("price")
                        ));
                    }

                    return products;
                }
        );
    }

    public void addProduct(String name, long price) {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("INSERT INTO PRODUCT (NAME, PRICE) VALUES (\"" + name + "\"," + price + ")");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Product> getProductWithMaxPrice() {
        return executeQueryWithResultSet(
                "SELECT * FROM PRODUCT ORDER BY PRICE DESC LIMIT 1",
                resultSet -> {
                    List<Product> products = new ArrayList<>();
                    while (resultSet.next()) {
                        products.add(new Product(
                                resultSet.getString("name"),
                                resultSet.getInt("price")
                        ));
                    }

                    return products.stream().findFirst();
                }
        );
    }

    public Optional<Product> getProductWithMinPrice() {
        return executeQueryWithResultSet(
                "SELECT * FROM PRODUCT ORDER BY PRICE LIMIT 1",
                resultSet -> {
                    List<Product> products = new ArrayList<>();
                    while (resultSet.next()) {
                        products.add(new Product(
                                resultSet.getString("name"),
                                resultSet.getInt("price")
                        ));
                    }

                    return products.stream().findFirst();
                }
        );
    }

    public int getProductsCount() {
        return executeQueryWithResultSet(
                "SELECT COUNT(*) FROM PRODUCT",
                resultSet -> {
                    List<Integer> counts = new ArrayList<>();
                    while (resultSet.next()) {
                        counts.add(resultSet.getInt(1));
                    }

                    return counts.stream()
                            .findFirst()
                            .orElseThrow(() -> new IllegalStateException("add error message"));
                }
        );
    }

    public long getProductsSummaryPrice() {
        return executeQueryWithResultSet(
                "SELECT SUM(price) FROM PRODUCT",
                resultSet -> {
                    List<Long> counts = new ArrayList<>();
                    while (resultSet.next()) {
                        counts.add(resultSet.getLong(1));
                    }

                    return counts.stream()
                            .findFirst()
                            .orElseThrow(() -> new IllegalStateException("add error message"));
                }
        );
    }

    private <T> T executeQueryWithResultSet(
            String sqlQuery,
            CheckedResultSetFunction<T> function
    ) {
        T result;
        try (Statement statement = connection.createStatement()) {
            try (ResultSet rs = statement.executeQuery(sqlQuery)) {
                result = function.apply(rs);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return result;
    }

    @FunctionalInterface
    private interface CheckedResultSetFunction<T> {
        T apply(ResultSet t) throws SQLException;
    }
}