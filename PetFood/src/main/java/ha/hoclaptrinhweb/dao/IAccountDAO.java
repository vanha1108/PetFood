package ha.hoclaptrinhweb.dao;

import ha.hoclaptrinhweb.model.AccountModel;

public interface IAccountDAO {
    AccountModel findByUsernameAndPasswordAndStatus(String username, String password, int status);
}
