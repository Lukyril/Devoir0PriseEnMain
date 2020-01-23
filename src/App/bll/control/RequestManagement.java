package App.bll.control;

import App.bll.model.AccountManager;
import App.bll.model.Client;
import App.dal.entities.ClientCreditRequest;
import App.dal.entities.CreditRequest;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class RequestManagement {
    private static RequestManagement instance;

    public static RequestManagement getInstance() {
        if (instance == null)
            instance = new RequestManagement();
        return instance;
    }

    private Set<AccountManager> managers;

    private RequestManagement() {
        managers = new TreeSet<>();
    }


    public void requestCredit(ClientCreditRequest request, Client client) {
        Objects.requireNonNull(
                managers.stream()
                        .filter(m -> m.getData().getClients().contains(client.getData()))
                        .findFirst().orElse(null)).getData()
                .getRequests().add(new CreditRequest(request, 4.6));
    }

    public void addManager(AccountManager manager) {
        this.managers.add(manager);
    }
}
