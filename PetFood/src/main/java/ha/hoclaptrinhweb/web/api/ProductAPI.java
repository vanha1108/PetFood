package ha.hoclaptrinhweb.web.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import ha.hoclaptrinhweb.model.AccountModel;
import ha.hoclaptrinhweb.model.ProductModel;
import ha.hoclaptrinhweb.utils.HttpUtil;
import ha.hoclaptrinhweb.utils.SessionUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ProductAPI extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ProductModel model = HttpUtil.of(request.getReader()).toModel(ProductModel.class);
        AccountModel accountModel = (AccountModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        model.setCreatedBy(accountModel.getUserName());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), model);
    }
}
