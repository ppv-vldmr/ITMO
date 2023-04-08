package ru.akirakozov.sd.refactoring;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.akirakozov.sd.refactoring.dao.ProductDAO;
import ru.akirakozov.sd.refactoring.servlet.AddProductServlet;
import ru.akirakozov.sd.refactoring.servlet.GetProductsServlet;
import ru.akirakozov.sd.refactoring.servlet.QueryServlet;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author akirakozov
 */
public class Main {
    private static final String CONNECTION_URL = "jdbc:sqlite:prod.db";
    private static final int PORT = 8081;

    public static final String ADD_PRODUCT_ENDPOINT = "add-product";
    public static final String GET_PRODUCTS_ENDPOINT = "get-products";
    public static final String QUERY_ENDPOINT = "query";


    public static void main(String[] args) throws Exception {
        Server server = createServer(CONNECTION_URL, PORT);
        server.start();
        server.join();
    }

    public static Server createServer(String connectionUrl, int port) {
        try {
            Connection connection = DriverManager.getConnection(connectionUrl);

            ProductDAO productDao = new ProductDAO(connection);

            Server server = new Server(port);

            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.setContextPath("/");
            server.setHandler(context);

            context.addServlet(new ServletHolder(new AddProductServlet(productDao)), "/" + ADD_PRODUCT_ENDPOINT);
            context.addServlet(new ServletHolder(new GetProductsServlet(productDao)), "/" + GET_PRODUCTS_ENDPOINT);
            context.addServlet(new ServletHolder(new QueryServlet(productDao)), "/" + QUERY_ENDPOINT);

            return server;
        } catch (Exception e) {
            throw new RuntimeException("Can not start server", e);
        }
    }
}