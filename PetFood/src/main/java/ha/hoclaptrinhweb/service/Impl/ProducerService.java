package ha.hoclaptrinhweb.service.Impl;

import ha.hoclaptrinhweb.dao.IProducerDAO;
import ha.hoclaptrinhweb.dao.IProductDAO;
import ha.hoclaptrinhweb.model.CategoryModel;
import ha.hoclaptrinhweb.model.ProducerModel;
import ha.hoclaptrinhweb.model.ProductModel;
import ha.hoclaptrinhweb.paging.Pageble;
import ha.hoclaptrinhweb.service.IProducerService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class ProducerService implements IProducerService {

    @Inject
    private IProducerDAO producerDAO;

    @Inject
    private IProductDAO productDAO;

    @Override
    public ProducerModel findOne(Long id) {
        return producerDAO.findOne(id);
    }

    @Override
    public ProducerModel save(ProducerModel producerModel) {
        Long id  = producerDAO.save(producerModel);
        return producerDAO.findOne(id);
    }

    @Override
    public ProducerModel update(ProducerModel updateModel) {
        producerDAO.update(updateModel);
        return producerDAO.findOne(updateModel.getId());
    }

    @Override
    public List<ProducerModel> findAll(Pageble pageble) {
        List<ProducerModel> producerModels = new ArrayList<>();
        producerModels = producerDAO.findAll(pageble);
        for (ProducerModel item : producerModels) {
            if (productDAO.findByProducerId(item.getId()).isEmpty()) {
                item.setDelFlag("1");
            } else {
                item.setDelFlag("0");
            }
        }
        return producerModels;
    }

    @Override
    public List<ProducerModel> findAll() {
        return producerDAO.findAll();
    }

    @Override
    public void delete(long[] ids) {
        for (Long item: ids) {
            producerDAO.delete(item);
        }
    }

    @Override
    public int getTotalProducer() {
        return producerDAO.getTotalProducer();
    }

    @Override
    public List<String> findAllCode() {
        return producerDAO.findAllCode();
    }
}
