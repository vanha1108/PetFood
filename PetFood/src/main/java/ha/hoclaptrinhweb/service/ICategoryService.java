package ha.hoclaptrinhweb.service;

import ha.hoclaptrinhweb.model.CategoryModel;
import ha.hoclaptrinhweb.paging.Pageble;

import java.util.List;

public interface ICategoryService {
    List<CategoryModel> findAll(Pageble pageble);
    List<CategoryModel> findAll();
    CategoryModel save(CategoryModel categoryModel);
    CategoryModel update(CategoryModel categoryModel);
    CategoryModel findOne(Long id);
    void delete(long[] ids);
    int getTotalCategory();
    List<String> findAllCode();
}
