package ha.hoclaptrinhweb.controller.web;

import ha.hoclaptrinhweb.model.ProductModel;
import ha.hoclaptrinhweb.model.ProductOfBillModel;
import ha.hoclaptrinhweb.service.ICategoryService;
import ha.hoclaptrinhweb.service.IProducerService;
import ha.hoclaptrinhweb.service.IProductOfBillService;
import ha.hoclaptrinhweb.utils.FormUtil;
import ha.hoclaptrinhweb.utils.ShoppingCartUtil;

import javax.inject.Inject;
import javax.jws.WebService;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = {"/shoppingcart", "/thanh-toan"})
public class ShoppingCartController extends HttpServlet {

    @Inject
    private IProductOfBillService productOfBillService;

    protected void  doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductOfBillModel model = FormUtil.toModel(ProductOfBillModel.class, request);
        String views = "";
        if (model.getAction() != null && model.getAction().equals("giohang")) {
            views = "/views/web/shoppingcart/cart.jsp";
        } else if(model.getAction() != null && model.getAction().equals("thanhtoan")) {
               views = "/views/web/shoppingcart/checkout.jsp";
        }
        List<ProductOfBillModel> results =  ShoppingCartUtil.getInstance().getValue(request, "SHOPPINGCART");
        request.setAttribute("total", productOfBillService.getTotalPrice(results));
        request.setAttribute("model", results);
        RequestDispatcher rd = request.getRequestDispatcher(views);
        rd.forward(request, response);
    }

    protected void  doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
