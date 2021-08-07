package ha.hoclaptrinhweb.admin.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import ha.hoclaptrinhweb.model.AccountModel;
import ha.hoclaptrinhweb.model.ProducerModel;
import ha.hoclaptrinhweb.service.IProducerService;
import ha.hoclaptrinhweb.utils.HttpUtil;
import ha.hoclaptrinhweb.utils.SessionUtil;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/api-admin-producer"})
public class ProducerAPI extends HttpServlet {

    @Inject
    IProducerService producerService;

    protected void  doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ProducerModel producerModel = HttpUtil.of(request.getReader()).toModel(ProducerModel.class);
        producerModel = producerService.save(producerModel);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), producerModel);
    }

    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ProducerModel updateModel = HttpUtil.of(request.getReader()).toModel(ProducerModel.class);
        updateModel = producerService.update(updateModel);
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), updateModel);
    }

    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        ProducerModel producerModel = HttpUtil.of(request.getReader()).toModel(ProducerModel.class);
        producerService.delete(producerModel.getIds());
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(response.getOutputStream(), "{}");
    }

}

