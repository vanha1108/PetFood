package ha.hoclaptrinhweb.controller.admin;

import ha.hoclaptrinhweb.model.CustomerModel;
import ha.hoclaptrinhweb.paging.PageRequest;
import ha.hoclaptrinhweb.paging.Pageble;
import ha.hoclaptrinhweb.service.ICustomerService;
import ha.hoclaptrinhweb.sort.Sorter;
import ha.hoclaptrinhweb.utils.FormUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin-customer"})
public class CustomerController extends HttpServlet {

    @Inject
    private ICustomerService customerService;

    protected void  doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CustomerModel model = FormUtil.toModel(CustomerModel.class, request);
        String views= "";
        if (model.getAction() != null && model.getAction().equals("danhsach")) {
            Pageble pageble = new PageRequest(model.getPage(), model.getLimit(), new Sorter(model.getSortName(), model.getSortBy()));
            model.setTotalCategory(customerService.getTotalCustomer());
            model.setTotalPage((int) Math.ceil((double) model.getTotalCategory() / model.getLimit()));
            views = "/views/admin/customer/list.jsp";
            request.setAttribute("customers", customerService.findAll(pageble));
        }
        request.setAttribute("model", model);
        RequestDispatcher rd = request.getRequestDispatcher(views);
        rd.forward(request,response);
    }

    protected void  doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
