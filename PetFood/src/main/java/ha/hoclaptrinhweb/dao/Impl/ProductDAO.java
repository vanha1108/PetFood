package ha.hoclaptrinhweb.dao.Impl;

import ha.hoclaptrinhweb.dao.IProductDAO;
import ha.hoclaptrinhweb.mapper.ProducerMapper;
import ha.hoclaptrinhweb.mapper.ProductMapper;
import ha.hoclaptrinhweb.model.ProducerModel;
import ha.hoclaptrinhweb.model.ProductModel;
import ha.hoclaptrinhweb.paging.Pageble;
import org.apache.commons.lang.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ProductDAO extends AbstractDAO<ProductModel> implements IProductDAO {
    @Override
    public List<ProductModel> findAll(Pageble pageble) {
        StringBuilder sql = new StringBuilder("SELECT * FROM product as p INNER JOIN category as c ON p.categoryid = c.id");
        sql.append(" INNER JOIN producer as d ON p.producerid = d.id");
        if(pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
            sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy());
        }

        if(pageble.getOffset() != null && pageble.getLimit() != null ) {
            sql.append(" LIMIT "+pageble.getOffset() + ", " + pageble.getLimit());
        }
        return  query(sql.toString(), new ProductMapper());
    }

    @Override
    public List<ProductModel> findAll() {
        StringBuilder sql = new StringBuilder("SELECT * FROM product as p INNER JOIN category as c ON p.categoryid = c.id");
        sql.append(" INNER JOIN producer as d ON p.producerid = d.id");
        return  query(sql.toString(), new ProductMapper());
    }

    @Override
    public ProductModel findOne(Long id) {
        String sql = "SELECT * FROM product WHERE id = ?";
        List<ProductModel> productModels = new ArrayList<>();
        productModels = query(sql, new ProductMapper(), id);
        return productModels.isEmpty() ?  null : productModels.get(0);
    }

    @Override
    public Long save(ProductModel productModel) {
        StringBuilder sql = new StringBuilder("INSERT INTO product (codeproduct, nameproduct, image, description, ");
        sql.append("categoryid, producerid, amount, importprice, price, createddate, createdby) ");
        sql.append("VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        return insert(sql.toString(), productModel.getCodeProduct(), productModel.getNameProduct(),
                productModel.getImage(), productModel.getDescription(), productModel.getCategoryId(),
                productModel.getProducerId(), productModel.getAmount(), productModel.getImportPrice(),
                productModel.getPrice(), productModel.getCreatedDate(), productModel.getCreatedBy());
    }

    @Override
    public void update(ProductModel productModel) {
        StringBuilder sql = new StringBuilder("UPDATE product SET codeproduct = ?, nameproduct = ?, image = ?, ");
        sql.append("description = ?, categoryid = ?, producerid =?, amount =?, importprice = ?, price = ?, ");
        sql.append(" createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ? WHERE id = ?");
        update(sql.toString(), productModel.getCodeProduct(), productModel.getNameProduct(),
                productModel.getImage(), productModel.getDescription(), productModel.getCategoryId(),
                productModel.getProducerId(), productModel.getAmount(), productModel.getImportPrice(),
                productModel.getPrice(), productModel.getCreatedDate(), productModel.getCreatedBy(),
                productModel.getModifiedDate(), productModel.getModifiedBy(), productModel.getId());
    }

    @Override
    public void delete(long ids) {
        String sql = "DELETE FROM product WHERE id = ?";
        update(sql, ids);
    }

    @Override
    public List<ProductModel> findByCategoryId(Long id) {
        String sql = "SELECT * FROM product WHERE categoryid = ?";
        List<ProductModel> results = new ArrayList<>();
        results = query(sql, new ProductMapper(), id);
        return results;
    }

    @Override
    public List<ProductModel> findByProducerId(Long id) {
        String sql = "SELECT * FROM product WHERE producerid = ?";
        List<ProductModel> results = new ArrayList<>();
        results = query(sql, new ProductMapper(), id);
        return results;
    }

    @Override
    public int getTotalProduct() {
        String sql = "SELECT count(*) FROM product";
        return count(sql);
    }

    @Override
    public List<String> findAllCode() {
        String sql = "SELECT * FROM product";
        List<ProductModel> productModels = query(sql, new ProductMapper());
        List<String> results = new ArrayList<>();
        for (ProductModel item : productModels) {
            results.add(item.getCodeProduct());
        }
        return results;
    }
}
