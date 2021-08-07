package ha.hoclaptrinhweb.controller.web;

import ha.hoclaptrinhweb.model.ProductModel;
import ha.hoclaptrinhweb.service.ICategoryService;
import ha.hoclaptrinhweb.service.IProducerService;
import ha.hoclaptrinhweb.service.IProductService;
import ha.hoclaptrinhweb.utils.FormUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/product"})
public class ProductController extends HttpServlet {

    @Inject
    private ICategoryService categoryService;

    @Inject
    private IProducerService producerService;

    @Inject
    private IProductService productService;

    protected void  doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductModel model = FormUtil.toModel(ProductModel.class, request);
        String views = "";
        if (model.getAction() != null && model.getAction().equals("chitiet")) {
            model = productService.findOne(model.getId());
            views = "views/web/product/detail.jsp";
        }
        request.setAttribute("model", model);
        request.setAttribute("categories", categoryService.findAll());
        request.setAttribute("producers", producerService.findAll());
        RequestDispatcher rd = request.getRequestDispatcher(views);
        rd.forward(request, response);
    }

    protected void  doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
