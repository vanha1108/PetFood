package ha.hoclaptrinhweb.controller.admin;

import ha.hoclaptrinhweb.model.CategoryModel;
import ha.hoclaptrinhweb.model.ProducerModel;
import ha.hoclaptrinhweb.paging.PageRequest;
import ha.hoclaptrinhweb.paging.Pageble;
import ha.hoclaptrinhweb.service.ICategoryService;
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
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin-category"})
public class CategoryController extends HttpServlet {

    @Inject
    private ICategoryService categoryService;

    protected void  doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryModel model = FormUtil.toModel(CategoryModel.class, request);
        String views= "";
        if (model.getAction() != null && model.getAction().equals("danhsach")) {
            Pageble pageble = new PageRequest(model.getPage(), model.getLimit(), new Sorter(model.getSortName(), model.getSortBy()));
            model.setTotalCategory(categoryService.getTotalCategory());
            model.setTotalPage((int) Math.ceil((double) model.getTotalCategory() / model.getLimit()));
            views = "/views/admin/category/list.jsp";
            request.setAttribute("categories", categoryService.findAll(pageble));
        } else if (model.getAction() != null && model.getAction().equals("chinhsua")) {
            if (model.getId() != null) {
                // CHinh sua
                model = categoryService.findOne(model.getId());
            }
            views = "views/admin/category/edit.jsp";
        }
        MessageUtil.showMessage(request);
        request.setAttribute("allcode", categoryService.findAllCode());
        request.setAttribute("model", model);
        RequestDispatcher rd = request.getRequestDispatcher(views);
        rd.forward(request, response);
     }

    protected void  doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
