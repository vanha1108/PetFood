package ha.hoclaptrinhweb.dao.Impl;

import ha.hoclaptrinhweb.dao.IAccountDAO;
import ha.hoclaptrinhweb.mapper.AccountMapper;
import ha.hoclaptrinhweb.model.AccountModel;

import java.util.ArrayList;
import java.util.List;

public class AccountDAO extends AbstractDAO implements IAccountDAO {
    @Override
    public AccountModel findByUsernameAndPasswordAndStatus(String username, String password, int status) {
        List<AccountModel> accountModels = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM account as a INNER JOIN role as r ");
        sql.append("ON a.roleid = r.id WHERE username =? AND password =? AND status =?");
        accountModels = query(sql.toString(), new AccountMapper(), username, password, status);
        return accountModels.isEmpty() ? null : accountModels.get(0);
    }
}
