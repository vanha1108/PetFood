package ha.hoclaptrinhweb.dao;

import ha.hoclaptrinhweb.model.CustomerModel;
import ha.hoclaptrinhweb.paging.Pageble;

import java.util.List;

public interface ICustomerDAO {
    List<CustomerModel> findAll(Pageble pageble);
    List<CustomerModel> findAll();
    int getTotalCustomer();
}
