package ha.hoclaptrinhweb.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import ha.hoclaptrinhweb.model.AccountModel;
import ha.hoclaptrinhweb.model.ProductModel;
import ha.hoclaptrinhweb.model.ProductOfBillModel;
import ha.hoclaptrinhweb.service.IProductService;
import ha.hoclaptrinhweb.utils.HttpUtil;
import ha.hoclaptrinhweb.utils.SessionUtil;
import ha.hoclaptrinhweb.utils.ShoppingCartUtil;
import ha.hoclaptrinhweb.utils.StackUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/api-shoppingcart"})
public class ShoppingCartAPI extends HttpServlet {

    @Inject
    private IProductService productService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ProductOfBillModel model = HttpUtil.of(request.getReader()).toModel(ProductOfBillModel.class);
        ProductModel productModel = productService.findOne(model.getProductId());
        model.setProductModel(productModel);
        AccountModel accountModel = (AccountModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        if (accountModel == null) {
            String url = "/product?action=chitiet&id=" + model.getProductId();
            StackUtil.getInstance().putValue(request, "STACK", url);
            model = null;
        } else {
            ShoppingCartUtil.getInstance().putValue(request, "SHOPPINGCART", model);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), model);
    }
}
