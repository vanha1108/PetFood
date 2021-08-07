package ha.hoclaptrinhweb.controller.web;

import ha.hoclaptrinhweb.model.AccountModel;
import ha.hoclaptrinhweb.model.ProductOfBillModel;
import ha.hoclaptrinhweb.service.IAccountService;
import ha.hoclaptrinhweb.service.ICategoryService;
import ha.hoclaptrinhweb.service.IProducerService;
import ha.hoclaptrinhweb.service.IProductService;
import ha.hoclaptrinhweb.utils.MessageUtil;
import ha.hoclaptrinhweb.utils.SessionUtil;
import ha.hoclaptrinhweb.utils.ShoppingCartUtil;
import ha.hoclaptrinhweb.utils.StackUtil;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/trang-chu", "/dang-nhap", "/thoat", "/phanloai"})
public class HomeController extends HttpServlet {

    @Inject
    private IAccountService accountService;

    @Inject
    private IProductService productService;

    @Inject
    private ICategoryService categoryService;

    @Inject
    private IProducerService producerService;

    protected void  doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        String id = request.getParameter("id");
        MessageUtil.showMessage(request);
        request.setAttribute("categories", categoryService.findAll());
        request.setAttribute("producers", producerService.findAll());
        if ( action != null && action.equals("dangnhap")) {
            RequestDispatcher rd = request.getRequestDispatcher("views/login/login.jsp");
            rd.forward(request, response);
        } else if (action != null && action.equals("thoat")) {
            SessionUtil.getInstance().removeValue(request, "USERMODEL");
            ShoppingCartUtil.getInstance().removeValue(request, "SHOPPINGCART");
            StackUtil.getInstance().removeValue(request, "STACK");
            response.sendRedirect(request.getContextPath() + "/trang-chu");
        } else if(action != null && action.equals("lsp")) {
            request.setAttribute("model", productService.findByCategoryId(Long.parseLong(id)));
            RequestDispatcher rd = request.getRequestDispatcher("views/web/home.jsp");
            rd.forward(request, response);
        } else if (action != null && action.equals("nsx")) {
            request.setAttribute("model", productService.findByProducerId( Long.parseLong(id)));
            RequestDispatcher rd = request.getRequestDispatcher("views/web/home.jsp");
            rd.forward(request, response);
        } else {
                request.setAttribute("model", productService.findAll());
                RequestDispatcher rd = request.getRequestDispatcher("views/web/home.jsp");
                rd.forward(request, response);
        }
    }

    protected void  doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if ( action != null && action.equals("dangnhap")) {
            String username = request.getParameter("userName");
            String password = request.getParameter("password");
            AccountModel model = new AccountModel();
            model.setUserName(username);
            model.setPassword(password);
            model = accountService.findByUsernameAndPasswordAndStatus(model.getUserName(), model.getPassword(), 1);
            String path ="";
            if (model != null) {
                ProductOfBillModel productOfBillModel = new ProductOfBillModel();
                SessionUtil.getInstance().putValue(request, "USERMODEL", model);
               if (model.getRoleId() == 1){
                 response.sendRedirect(request.getContextPath()+"/admin-home");
               } else {
                   String x = (String) StackUtil.getInstance().getValue(request, "STACK");
                   if (x == null) {
                       response.sendRedirect(request.getContextPath()+"/trang-chu");
                   } else {
                       StackUtil.getInstance().removeValue(request, "STACK");
                       ShoppingCartUtil.getInstance().removeValue(request, "SHOPPINGCART");
                       response.sendRedirect(request.getContextPath() + x);
                   }
               }
            } else {
                response.sendRedirect(request.getContextPath() +"/dang-nhap?action=dangnhap&message=login_invalid");
            }
        }
    }

}
