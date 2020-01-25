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


    public boolean requestCredit(ClientCreditRequest request, Client client) {
        CreditRequest creditRequest = new CreditRequest(request, 4.6);
        App.dal.entities.Client clientData = client.getData();
        Objects.requireNonNull(
                managers.stream()
                        .filter(m -> m.getData().getClients().contains(clientData))
                        .findFirst().orElse(null)).getData()
                .getRequests().add(creditRequest);
        String status = clientData.getStatus();
        double balance = getBalance(clientData);

        if (balance < 31000) {
            if (status.equals("Célibataire") || status.equals("Divorcé"))
                return false;
        } else if (balance < 21000)
            if (status.equals("Marié"))
                return false;
        int year = clientData.getBirthYear();
        if (clientData.isMale()) {
            return year <= 1999;
        } else return year <= 2002;
    }

    private double getBalance(App.dal.entities.Client clientData) {
        return Objects.requireNonNull(clientData.getAccounts()
                .stream().findFirst().orElse(null))
                .getBalance();
    }

    public void addManager(AccountManager manager) {
        this.managers.add(manager);
    }
}
