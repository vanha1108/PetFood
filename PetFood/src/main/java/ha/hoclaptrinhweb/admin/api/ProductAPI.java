package ha.hoclaptrinhweb.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import ha.hoclaptrinhweb.dao.IProductDAO;
import ha.hoclaptrinhweb.model.AccountModel;
import ha.hoclaptrinhweb.model.CategoryModel;
import ha.hoclaptrinhweb.model.ProducerModel;
import ha.hoclaptrinhweb.model.ProductModel;
import ha.hoclaptrinhweb.service.IProductService;
import ha.hoclaptrinhweb.utils.HttpUtil;
import ha.hoclaptrinhweb.utils.SessionUtil;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@WebServlet(urlPatterns = {"/api-admin-product"})
@MultipartConfig(fileSizeThreshold = 1024 * 1024,
        maxFileSize = 1024 * 1024 * 5,
        maxRequestSize = 1024 * 1024 * 5 * 5)
public class ProductAPI extends HttpServlet {

    @Inject
    private IProductService productService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ProductModel model = HttpUtil.of(request.getReader()).toModel(ProductModel.class);
        AccountModel accountModel = (AccountModel) SessionUtil.getInstance().getValue(request, "USERMODEL");
        model.setCreatedBy(accountModel.getUserName());
        model = productService.save(request, model);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), model);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ProductModel productModel = HttpUtil.of(request.getReader()).toModel(ProductModel.class);
        productModel = productService.update(productModel);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), productModel);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ProductModel productModel = HttpUtil.of(request.getReader()).toModel(ProductModel.class);
        productService.delete(productModel.getIds());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), "{}");
    }
}
