package ha.hoclaptrinhweb.service.Impl;

import ha.hoclaptrinhweb.dao.ICustomerDAO;
import ha.hoclaptrinhweb.model.CustomerModel;
import ha.hoclaptrinhweb.paging.Pageble;
import ha.hoclaptrinhweb.service.ICustomerService;

import javax.inject.Inject;
import java.util.List;

public class CustomerService implements ICustomerService {

    @Inject
    private ICustomerDAO customerDAO;

    @Override
    public List<CustomerModel> findAll(Pageble pageble) {
       return  customerDAO.findAll(pageble);
    }

    @Override
    public List<CustomerModel> findAll() {
        return customerDAO.findAll();
    }

    @Override
    public int getTotalCustomer() {
        return customerDAO.getTotalCustomer();
    }
}
