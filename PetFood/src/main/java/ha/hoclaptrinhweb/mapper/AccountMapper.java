package ha.hoclaptrinhweb.mapper;

import ha.hoclaptrinhweb.model.AccountModel;
import ha.hoclaptrinhweb.model.RoleModel;

import javax.management.relation.Role;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountMapper implements RowMapper<AccountModel> {
    @Override
    public AccountModel mapRow(ResultSet rs) {
        AccountModel model = new AccountModel();
        try {
            model.setId(rs.getLong("id"));
            model.setUserName(rs.getString("username"));
            model.setPassword(rs.getString("password"));
            model.setStatus(rs.getInt("status"));
            model.setRoleId(rs.getLong("roleId"));
            if (rs.getTimestamp("createdDate") != null) {
                model.setCreatedDate(rs.getTimestamp("createdDate"));
            }
            model.setCreatedBy(rs.getString("createdBy"));

            RoleModel roleModel = new RoleModel();
            roleModel.setId(rs.getLong("roleId"));
            roleModel.setName(rs.getString("name"));
            roleModel.setCode(rs.getString("code"));
            model.setRole(roleModel);

            return model;
        } catch (SQLException e) {
            return null;
        }
    }
}
