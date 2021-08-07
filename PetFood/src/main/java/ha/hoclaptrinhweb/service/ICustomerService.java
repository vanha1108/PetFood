package ha.hoclaptrinhweb.service;

import ha.hoclaptrinhweb.model.CustomerModel;
import ha.hoclaptrinhweb.paging.Pageble;

import java.util.List;

public interface ICustomerService {
    public List<CustomerModel> findAll(Pageble pageble);
    public List<CustomerModel> findAll();
    int getTotalCustomer();
}
