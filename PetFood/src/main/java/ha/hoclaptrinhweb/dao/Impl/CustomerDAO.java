package ha.hoclaptrinhweb.dao.Impl;

import ha.hoclaptrinhweb.dao.ICustomerDAO;
import ha.hoclaptrinhweb.mapper.CategoryMapper;
import ha.hoclaptrinhweb.mapper.CustomerMapper;
import ha.hoclaptrinhweb.model.CustomerModel;
import ha.hoclaptrinhweb.paging.Pageble;
import org.apache.commons.lang.StringUtils;

import java.util.List;

public class CustomerDAO extends AbstractDAO<CustomerModel> implements ICustomerDAO {
    @Override
    public List<CustomerModel> findAll(Pageble pageble) {
        StringBuilder sql = new StringBuilder("SELECT * FROM customer");
        if(pageble.getSorter() != null && StringUtils.isNotBlank(pageble.getSorter().getSortName()) && StringUtils.isNotBlank(pageble.getSorter().getSortBy())) {
            sql.append(" ORDER BY " + pageble.getSorter().getSortName() + " " + pageble.getSorter().getSortBy());
        }

        if(pageble.getOffset() != null && pageble.getLimit() != null ) {
            sql.append(" LIMIT "+pageble.getOffset() + ", " + pageble.getLimit());
        }
        return query(sql.toString(), new CustomerMapper());
    }

    @Override
    public List<CustomerModel> findAll() {
        String sql = "SELECT * FROM customer";
        return  query(sql, new CustomerMapper());
    }

    @Override
    public int getTotalCustomer() {
        String sql = "SELECT count(*) FROM customer";
        return count(sql);
    }
}
