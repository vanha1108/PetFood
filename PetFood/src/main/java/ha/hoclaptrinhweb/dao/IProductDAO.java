package ha.hoclaptrinhweb.dao;

import ha.hoclaptrinhweb.model.ProductModel;
import ha.hoclaptrinhweb.paging.Pageble;

import java.util.List;

public interface IProductDAO extends GenericDAO<ProductModel>{
    List<ProductModel> findAll(Pageble pageble);
    List<ProductModel> findAll();
    ProductModel findOne(Long id);
    Long save(ProductModel productModel);
    void update(ProductModel productModel);
    void delete(long ids);
    List<ProductModel> findByCategoryId(Long id);
    List<ProductModel> findByProducerId(Long id);
    int getTotalProduct();
    List<String> findAllCode();

}
