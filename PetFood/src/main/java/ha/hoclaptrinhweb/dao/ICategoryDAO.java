package ha.hoclaptrinhweb.dao;

import ha.hoclaptrinhweb.model.CategoryModel;
import ha.hoclaptrinhweb.paging.Pageble;

import java.util.List;

public interface ICategoryDAO {
    List<CategoryModel> findAll(Pageble pageble);
    List<CategoryModel> findAll();
    CategoryModel findOne(Long id);
    CategoryModel findOneByCode(String code);
    Long save(CategoryModel categoryModel);
    void update(CategoryModel categoryModel);
    void delete(long ids);
    int getTotalCategory();
    List<String> findAllCode();
}
