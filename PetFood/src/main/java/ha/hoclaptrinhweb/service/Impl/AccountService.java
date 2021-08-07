package ha.hoclaptrinhweb.service.Impl;

import ha.hoclaptrinhweb.dao.IAccountDAO;
import ha.hoclaptrinhweb.model.AccountModel;
import ha.hoclaptrinhweb.service.IAccountService;

import javax.inject.Inject;

public class AccountService implements IAccountService {

    @Inject
    private IAccountDAO accountDAO;

    @Override
    public AccountModel findByUsernameAndPasswordAndStatus(String username, String password, int status) {
        return accountDAO.findByUsernameAndPasswordAndStatus(username, password, status);
    }
}
