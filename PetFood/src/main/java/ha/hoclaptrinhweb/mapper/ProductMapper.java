package ha.hoclaptrinhweb.mapper;

import ha.hoclaptrinhweb.model.CategoryModel;
import ha.hoclaptrinhweb.model.ProducerModel;
import ha.hoclaptrinhweb.model.ProductModel;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

public class ProductMapper implements RowMapper<ProductModel> {
    @Override
    public ProductModel mapRow(ResultSet rs) {
        try {
            ProductModel productModel = new ProductModel();
            productModel.setId(rs.getLong("id"));
            productModel.setCodeProduct(rs.getString("codeproduct"));
            productModel.setNameProduct(rs.getString("nameproduct"));
            productModel.setAmount(rs.getLong("amount"));
            productModel.setImage(rs.getString("image"));
            productModel.setDescription(rs.getString("description"));
            productModel.setImportPrice(rs.getDouble("importprice"));
            productModel.setPrice(rs.getDouble("price"));
            productModel.setProducerId(rs.getLong("producerid"));
            productModel.setCategoryId(rs.getLong("categoryid"));

            try {
                CategoryModel categoryModel = new CategoryModel();
                if(rs.getString("codecategory") != null) {
                    categoryModel.setCodeCategory(rs.getString("codecategory"));
                }
                if (rs.getString("namecategory") != null) {
                    categoryModel.setNameCategory((rs.getString("namecategory")));
                }
                productModel.setCategoryModel(categoryModel);

                ProducerModel producerModel = new ProducerModel();
                if (rs.getString("codeproducer") != null) {
                    producerModel.setCodeProducer(rs.getString("codeproducer"));
                }
                if (rs.getString("nameproducer") != null) {
                    producerModel.setNameProducer(rs.getString("nameproducer"));
                }
                if (rs.getString("phonenumber")!= null) {
                    producerModel.setPhoneNumber(rs.getString("phonenumber"));
                }
                productModel.setProducerModel(producerModel);
            } catch (Exception e) {
            }

            if(rs.getTimestamp("modifieddate") != null) {
                productModel.setModifiedDate(rs.getTimestamp("modifieddate"));
            }
            if(rs.getString("modifiedby") != null) {
                productModel.setModifiedBy(rs.getString("modifiedby"));
            }
            return productModel;
        } catch (SQLException e) {
            return  null;
        }
    }
}
