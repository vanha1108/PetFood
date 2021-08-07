package ha.hoclaptrinhweb.service.Impl;

import ha.hoclaptrinhweb.dao.ICategoryDAO;
import ha.hoclaptrinhweb.dao.IProducerDAO;
import ha.hoclaptrinhweb.dao.IProductDAO;
import ha.hoclaptrinhweb.model.AccountModel;
import ha.hoclaptrinhweb.model.CategoryModel;
import ha.hoclaptrinhweb.model.ProducerModel;
import ha.hoclaptrinhweb.model.ProductModel;
import ha.hoclaptrinhweb.paging.Pageble;
import ha.hoclaptrinhweb.service.IProductService;
import ha.hoclaptrinhweb.utils.SessionUtil;

import javax.inject.Inject;
import javax.jms.Session;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.DatatypeConverter;
import java.io.*;
import java.sql.Timestamp;
import java.util.List;

public class ProductService implements IProductService {

    @Inject
    private IProductDAO productDAO;

    @Inject
    private ICategoryDAO categoryDAO;

    @Inject
    private IProducerDAO producerDAO;

    @Override
    public List<ProductModel> findAll(Pageble pageble) {
        return  productDAO.findAll(pageble);
    }

    @Override
    public List<ProductModel> findAll() {
        return productDAO.findAll();
    }

    @Override
    public ProductModel findOne(Long id) {
        ProductModel productModel = productDAO.findOne(id);

        CategoryModel categoryModel = categoryDAO.findOne(productModel.getCategoryId());
        productModel.setCategoryCode(categoryModel.getCodeCategory());
        productModel.setCategoryModel(categoryModel);

        ProducerModel producerModel = producerDAO.findOne(productModel.getProducerId());
        productModel.setProducerCode(producerModel.getCodeProducer());
        productModel.setProducerModel(producerModel);
        return productModel;
    }

    @Override
    public ProductModel save(HttpServletRequest request, ProductModel productModel) {
        productModel.setCreatedDate(new Timestamp(System.currentTimeMillis()));

        CategoryModel categoryModel = categoryDAO.findOneByCode(productModel.getCategoryCode());
        productModel.setCategoryId(categoryModel.getId());

        ProducerModel producerModel = producerDAO.findOneByCode(productModel.getProducerCode());
        productModel.setProducerId(producerModel.getId());

        Long id = productDAO.save(productModel);
        return productDAO.findOne(id);
    }

    @Override
    public ProductModel update(ProductModel productModel) {
        ProductModel oldModel = productDAO.findOne(productModel.getId());
        productModel.setCreatedDate(oldModel.getCreatedDate());
        productModel.setCreatedBy(oldModel.getCreatedBy());
        productModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));

        CategoryModel categoryModel = categoryDAO.findOneByCode(productModel.getCategoryCode());
        productModel.setCategoryId(categoryModel.getId());

        ProducerModel producerModel = producerDAO.findOneByCode(productModel.getProducerCode());
        productModel.setProducerId(producerModel.getId());

        productDAO.update(productModel);
        return productDAO.findOne(productModel.getId());
    }

    @Override
    public void delete(long[] ids) {
        for (Long item: ids) {
            productDAO.delete(item);
        }
    }

    @Override
    public int getTotalProduct() {
        return productDAO.getTotalProduct();
    }

    @Override
    public List<String> findAllCode() {
        return productDAO.findAllCode();
    }

    @Override
    public List<ProductModel> findByCategoryId(Long id) {
        return productDAO.findByCategoryId(id);
    }

    @Override
    public List<ProductModel> findByProducerId(Long id) {
        return productDAO.findByProducerId(id);
    }
}
