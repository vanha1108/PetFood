package ha.hoclaptrinhweb.controller.admin;

import ha.hoclaptrinhweb.model.ProducerModel;
import ha.hoclaptrinhweb.model.ProductModel;
import ha.hoclaptrinhweb.paging.PageRequest;
import ha.hoclaptrinhweb.paging.Pageble;
import ha.hoclaptrinhweb.service.IProducerService;
import ha.hoclaptrinhweb.sort.Sorter;
import ha.hoclaptrinhweb.utils.FormUtil;
import ha.hoclaptrinhweb.utils.MessageUtil;

import javax.enterprise.inject.spi.Producer;
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

@WebServlet(urlPatterns = {"/admin-producer"})
public class ProducerController extends HttpServlet {

    @Inject
    private IProducerService producerService;

    protected void  doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProducerModel model = FormUtil.toModel(ProducerModel.class, request);
        String view = "";
        List<ProducerModel> producerModels = new ArrayList<>();
        if ( request.getParameter("action") != null) {
            if (model.getAction() != null && model.getAction().equals("danhsach")) {
                Pageble pageble = new PageRequest(model.getPage(), model.getLimit(), new Sorter(model.getSortName(), model.getSortBy()));
                model.setTotalProducer(producerService.getTotalProducer());
                model.setTotalPage((int) Math.ceil((double) model.getTotalProducer() / model.getLimit()));
                view = "views/admin/producer/list.jsp";
                request.setAttribute("producerModels", producerService.findAll(pageble));
            } else if (model.getAction() != null && model.getAction().equals("chinhsua")) {
                if (model.getId() != null) {
                   model = producerService.findOne(model.getId());
                }
                view = "views/admin/producer/edit.jsp";
            }
        }
        MessageUtil.showMessage(request);
        request.setAttribute("allcode", producerService.findAllCode());
        request.setAttribute("producers", producerService.findAll());
        request.setAttribute("model", model);
        RequestDispatcher rd = request.getRequestDispatcher(view);
        rd.forward(request, response);
    }

    protected void  doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
