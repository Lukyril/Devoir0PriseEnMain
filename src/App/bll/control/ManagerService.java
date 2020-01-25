package App.bll.control;

import App.bll.model.AccountManager;
import App.dal.DataFacade;
import App.dal.entities.Client;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

public class ManagerService {

    private static ManagerService instance;

    private ManagerService() {
        accountManager = new App.bll.model
                .AccountManager(DataFacade.getInstance().getDao().initManager());
        RequestManagement.getInstance().addManager(accountManager);
        for (int i = 0; i < 5; i++)
            accountManager.addClient(DataFacade.getInstance().getDao().initClient());
    }

    public static ManagerService getInstance() {
        if (instance == null)
            instance = new ManagerService();
        return instance;
    }

    App.bll.model.AccountManager accountManager;

    public AccountManager getAccountManager() {
        return accountManager;
    }

    public void sortFirstPositionClient() {

        AtomicReference<Client> bestClient = new AtomicReference<>(Objects.requireNonNull(accountManager.getData().getClients()
                .stream().findFirst().orElse(null)));
        AtomicReference<Double> bestSalary = new AtomicReference<>(accountManager.getSalary(bestClient.get()));
        final int[] i = {-1};
        AtomicReference<Integer> bestPosition = new AtomicReference<Integer>(0);
        accountManager.getData().getClients().forEach(c -> {
            i[0]++;

            if (accountManager.getSalary(c) > bestSalary.get()) {
                bestClient.set(c);
                bestPosition.set(i[0]);
                bestSalary.set(accountManager.getSalary(c));
            }
        });
        accountManager.getData().getClients().set(bestPosition.get(), accountManager.getData().getClients().get(0));
        accountManager.getData().getClients().set(0, bestClient.get());
    }
}
