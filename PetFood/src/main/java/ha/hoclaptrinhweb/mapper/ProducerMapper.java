package ha.hoclaptrinhweb.mapper;

import ha.hoclaptrinhweb.model.ProducerModel;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProducerMapper implements RowMapper<ProducerModel>{

    @Override
    public ProducerModel mapRow(ResultSet rs) {
        ProducerModel model = new ProducerModel();
        try {
            model.setId(rs.getLong("id"));
            model.setCodeProducer(rs.getString("codeproducer"));
            model.setNameProducer(rs.getString("nameproducer"));
            model.setPhoneNumber(rs.getString("phonenumber"));
            return model;
        } catch (SQLException e) {
            return null;
        }
    }
}
