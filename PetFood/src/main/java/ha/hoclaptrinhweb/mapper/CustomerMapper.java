package ha.hoclaptrinhweb.mapper;

import ha.hoclaptrinhweb.model.CustomerModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements RowMapper<CustomerModel> {

    @Override
    public CustomerModel mapRow(ResultSet rs) {
        CustomerModel customerModel = new CustomerModel();
        try {
            customerModel.setId(rs.getLong("id"));
            customerModel.setCodeCustomer(rs.getString("codecustomer"));
            customerModel.setFullName(rs.getString("fullname"));
            customerModel.setPhoneNumber(rs.getString("phonenumber"));
            customerModel.setAddress(rs.getString("address"));
            customerModel.setLevel(rs.getInt("level"));
            customerModel.setTotalMoney(rs.getDouble("totalmoney"));
            return customerModel;
        } catch (SQLException e) {
            return null;
        }
    }
}
