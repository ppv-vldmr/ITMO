package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.dao.ProductDAO;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static ru.akirakozov.sd.refactoring.utils.HtmlBuilder.addBody;
import static ru.akirakozov.sd.refactoring.utils.HtmlBuilder.addBr;
import static ru.akirakozov.sd.refactoring.utils.HtmlBuilder.addH1;
import static ru.akirakozov.sd.refactoring.utils.HtmlBuilder.addHtml;

/**
 * @author akirakozov
 */
public class QueryServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html";

    private final ProductDAO productDao;

    public QueryServlet(ProductDAO productDao) {
        this.productDao = productDao;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String command = request.getParameter("command");

        StringBuilder innerBody = new StringBuilder();
        switch (command) {
            case "max" -> {
                innerBody.append(addH1("Product with max price: "));
                productDao.getProductWithMaxPrice().ifPresent(p ->
                        innerBody.append(addBr(p.getName() + "\t" + p.getPrice()))
                );
            }
            case "min" -> {
                innerBody.append(addH1("Product with min price: "));
                productDao.getProductWithMinPrice().ifPresent(p ->
                        innerBody.append(addBr(p.getName() + "\t" + p.getPrice()))
                );
            }
            case "sum" -> innerBody.append(
                    String.format(
                            "Summary price: \n%s\n",
                            productDao.getProductsSummaryPrice()
                    )
            );
            case "count" -> innerBody.append(
                    String.format(
                            "Number of products: \n%s\n",
                            productDao.getProductsCount()
                    )
            );
            default -> response.getWriter().println("Unknown command: " + command);
        }

        if (!innerBody.isEmpty()) {
            response.getWriter().println(addHtml(addBody(innerBody.toString())));
        }

        response.setContentType(CONTENT_TYPE);
        response.setStatus(HttpServletResponse.SC_OK);
    }

}