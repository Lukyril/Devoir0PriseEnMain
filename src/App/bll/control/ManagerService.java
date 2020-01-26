package App.bll.control;

import App.bll.model.AccountManager_;
import App.dal.DataFacade;
import App.dal.entities.Client;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

public class ManagerService {

    private static ManagerService instance;


    public static ManagerService getInstance() {
        if (instance == null)
            instance = new ManagerService();
        return instance;
    }

    private AccountManager_ accountManager;

    private ManagerService() {
        accountManager = new AccountManager_(DataFacade.getInstance().getDao()
                .initManager());
        RequestManagement.getInstance().addManager(accountManager);
        IntStream.range(0, 5)
                .forEach(i -> accountManager
                        .addClient(DataFacade.getInstance().getDao()
                                .initClient()));
        accountManager.getStats();
    }

    public AccountManager_ getAccountManager() {
        return accountManager;
    }

    public boolean verifyPassword(long deskNum, String password) {
        return accountManager.getData()
                .getDeskNum() == deskNum
                && accountManager.getData()
                .getPassword().equals(password);
    }


}
