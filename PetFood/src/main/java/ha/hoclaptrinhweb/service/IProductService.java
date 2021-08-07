package ha.hoclaptrinhweb.service;

import ha.hoclaptrinhweb.model.ProductModel;
import ha.hoclaptrinhweb.paging.Pageble;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IProductService {
    List<ProductModel> findAll(Pageble pageble);
    List<ProductModel> findAll();
    ProductModel findOne(Long id);
    ProductModel save(HttpServletRequest request, ProductModel productModel);
    ProductModel update(ProductModel productModel);
    void delete(long[] ids);
    int getTotalProduct();
    List<String> findAllCode();
    List<ProductModel> findByCategoryId(Long id);
    List<ProductModel> findByProducerId(Long id);
}
