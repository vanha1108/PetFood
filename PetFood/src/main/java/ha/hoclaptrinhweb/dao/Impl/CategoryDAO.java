package ha.hoclaptrinhweb.dao.Impl;

import ha.hoclaptrinhweb.dao.ICategoryDAO;
import ha.hoclaptrinhweb.mapper.CategoryMapper;
import ha.hoclaptrinhweb.mapper.ProducerMapper;
import ha.hoclaptrinhweb.model.CategoryModel;
import ha.hoclaptrinhweb.paging.Pageble;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CategoryDAO extends AbstractDAO<CategoryModel> implements ICategoryDAO {
    @Override
    public List<CategoryModel> findAll(Pageble pageble) {
        StringBuilder sql = new StringBuilder("SELECT * FROM category");

        if(pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
            sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy());
        }

        if(pageble.getOffset() != null && pageble.getLimit() != null ) {
            sql.append(" LIMIT "+pageble.getOffset() + ", " + pageble.getLimit());
        }
        return query(sql.toString(), new CategoryMapper());
    }

    @Override
    public List<CategoryModel> findAll() {
        String sql = "SELECT * FROM category";
        return query(sql, new CategoryMapper());
    }

    @Override
    public CategoryModel findOne(Long id) {
        String sql = "SELECT * FROM category WHERE id= ?";
        List<CategoryModel> categoryModels = query(sql, new CategoryMapper(), id);
        return categoryModels.isEmpty() ? null : categoryModels.get(0);
    }

    @Override
    public CategoryModel findOneByCode(String code) {
        String sql = "SELECT * FROM category WHERE codecategory = ?";
        List<CategoryModel> categories = query(sql, new CategoryMapper(), code);
        return categories.isEmpty() ? null : categories.get(0);
    }

    @Override
    public Long save(CategoryModel categoryModel) {
        String sql = "INSERT INTO category (codecategory, namecategory) VALUES (?, ?)";
        return insert(sql, categoryModel.getCodeCategory(), categoryModel.getNameCategory());
    }

    @Override
    public void update(CategoryModel categoryModel) {
        String sql = "UPDATE category SET codecategory = ?, namecategory = ? WHERE id =?";
        update(sql, categoryModel.getCodeCategory(), categoryModel.getNameCategory(), categoryModel.getId());
    }

    @Override
    public void delete(long ids) {
        String sql = "DELETE FROM category WHERE id = ?";
        update(sql, ids);
    }

    @Override
    public int getTotalCategory() {
        String sql = "SELECT count(*) FROM category";
        return count(sql);
    }

    @Override
    public List<String> findAllCode() {
        String sql = "SELECT * FROM category";
        List<CategoryModel> categorys =  query(sql, new CategoryMapper());
        List<String> results = new ArrayList<>();
        for (CategoryModel item : categorys) {
            results.add(item.getCodeCategory());
        }
        return results;
    }
}
