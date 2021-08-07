package ha.hoclaptrinhweb.mapper;

import ha.hoclaptrinhweb.model.CategoryModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryMapper implements RowMapper<CategoryModel> {

    @Override
    public CategoryModel mapRow(ResultSet rs) {
        CategoryModel categoryModel = new CategoryModel();
        try {
            categoryModel.setId(rs.getLong("id"));
            categoryModel.setCodeCategory(rs.getString("codecategory"));
            categoryModel.setNameCategory(rs.getString("namecategory"));

            return categoryModel;
        } catch (SQLException e) {
            return null;
        }
    }
}
