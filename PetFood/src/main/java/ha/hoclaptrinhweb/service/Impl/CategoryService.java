package ha.hoclaptrinhweb.service.Impl;

import ha.hoclaptrinhweb.dao.ICategoryDAO;
import ha.hoclaptrinhweb.dao.IProductDAO;
import ha.hoclaptrinhweb.model.CategoryModel;
import ha.hoclaptrinhweb.model.ProductModel;
import ha.hoclaptrinhweb.paging.Pageble;
import ha.hoclaptrinhweb.service.ICategoryService;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

public class CategoryService implements ICategoryService {

    @Inject
    private ICategoryDAO categoryDAO;

    @Inject
    private IProductDAO productDAO;

    @Override
    public List<CategoryModel> findAll(Pageble pageble) {
        List<CategoryModel> categoryModels = new ArrayList<>();
        categoryModels = categoryDAO.findAll(pageble);
        for (CategoryModel item : categoryModels) {
            if (productDAO.findByCategoryId(item.getId()).isEmpty()) {
                item.setDelFlag("1");
            } else {
                item.setDelFlag("0");
            }
        }
        return categoryModels;
    }

    @Override
    public List<CategoryModel> findAll() {
        return categoryDAO.findAll();
    }

    @Override
    public CategoryModel save(CategoryModel categoryModel) {
        Long id = categoryDAO.save(categoryModel);
        return findOne(id);
    }

    @Override
    public CategoryModel update(CategoryModel categoryModel) {
        categoryDAO.update(categoryModel);
        return findOne(categoryModel.getId());
    }

    @Override
    public CategoryModel findOne(Long id) {
        return categoryDAO.findOne(id);
    }

    @Override
    public void delete(long[] ids) {
        for (Long item: ids) {
                categoryDAO.delete(item);
        }
    }

    @Override
    public int getTotalCategory() {
        return categoryDAO.getTotalCategory();
    }

    @Override
    public List<String> findAllCode() {
        return categoryDAO.findAllCode();
    }
}
