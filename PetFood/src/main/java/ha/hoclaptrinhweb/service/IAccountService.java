package ha.hoclaptrinhweb.service;

import ha.hoclaptrinhweb.model.AccountModel;

public interface IAccountService {
    AccountModel findByUsernameAndPasswordAndStatus(String username, String password, int status);
}
