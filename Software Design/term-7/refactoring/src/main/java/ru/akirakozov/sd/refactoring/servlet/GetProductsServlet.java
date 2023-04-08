package ru.akirakozov.sd.refactoring.servlet;

import ru.akirakozov.sd.refactoring.dao.ProductDAO;
import ru.akirakozov.sd.refactoring.model.Product;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static ru.akirakozov.sd.refactoring.utils.HtmlBuilder.addBody;
import static ru.akirakozov.sd.refactoring.utils.HtmlBuilder.addBr;
import static ru.akirakozov.sd.refactoring.utils.HtmlBuilder.addHtml;

/**
 * @author akirakozov
 */
public class GetProductsServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html";

    ProductDAO productDao;

    public GetProductsServlet(ProductDAO productDao) {
        this.productDao = productDao;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            StringBuilder productList = new StringBuilder();
            for (Product product : productDao.getAllProducts()) {
                productList.append(addBr(product.getName() + "\t" + product.getPrice()));
            }

            response.getWriter().println(addHtml(addBody(productList.toString())));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        response.setContentType(CONTENT_TYPE);
        response.setStatus(HttpServletResponse.SC_OK);
    }
}