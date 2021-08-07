package ha.hoclaptrinhweb.controller.admin;

import com.fasterxml.jackson.annotation.JacksonInject;
import ha.hoclaptrinhweb.model.ProductModel;
import ha.hoclaptrinhweb.paging.PageRequest;
import ha.hoclaptrinhweb.paging.Pageble;
import ha.hoclaptrinhweb.service.ICategoryService;
import ha.hoclaptrinhweb.service.IProducerService;
import ha.hoclaptrinhweb.service.IProductService;
import ha.hoclaptrinhweb.sort.Sorter;
import ha.hoclaptrinhweb.utils.FormUtil;
import ha.hoclaptrinhweb.utils.HttpUtil;
import ha.hoclaptrinhweb.utils.MessageUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin-product"})
public class ProductController extends HttpServlet {

    @Inject
    private IProducerService producerService;

    @Inject
    private IProductService productService;

    @Inject
    private ICategoryService categoryService;

    protected void  doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductModel model = FormUtil.toModel(ProductModel.class, request);
        String view = "";
        if (model.getAction() != null&& model.getAction().equals("danhsach")) {
            Pageble pageble = new PageRequest(model.getPage(), model.getLimit(), new Sorter(model.getSortName(), model.getSortBy()));
            model.setTotalProduct(productService.getTotalProduct());
            model.setTotalPage((int) Math.ceil((double) model.getTotalProduct() / model.getLimit()));

            view = "views/admin/product/list.jsp";
            model.setListResult(productService.findAll(pageble));
        } else if (model.getAction() != null&& model.getAction().equals("chinhsua")) {
            if (model.getId() != null) {
               // Chỉnh sửa sản phẩm
                model = productService.findOne(model.getId());
            }
            view = "views/admin/product/edit.jsp";
        }
        MessageUtil.showMessage(request);
        request.setAttribute("allcode", productService.findAllCode());
        request.setAttribute("categories", categoryService.findAll());
        request.setAttribute("producers", producerService.findAll());
        request.setAttribute("model", model);
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }

    protected void  doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }


}
