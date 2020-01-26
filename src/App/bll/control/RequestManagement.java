package App.bll.control;

import App.bll.model.AccountManager_;
import App.bll.model.Client_;
import App.dal.entities.Client;
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

    private Set<AccountManager_> managers;

    private RequestManagement() {
        managers = new TreeSet<>();
    }

    public boolean requestCredit(ClientCreditRequest request, Client_ client) {


        Client clientData = client.getData();

        String status = clientData.getStatus();

        double balance = getBalance(clientData);

        if (creditEligibility(clientData, status, balance)) {
            CreditRequest creditRequest =
                    new CreditRequest(request, 4.6);
            Objects.requireNonNull(
                    managers.stream()
                            .filter(m -> m.getData().getClients().contains(clientData))
                            .findFirst().orElse(null))
                    .getData().getRequests()
                    .add(creditRequest);
        }
        return false;
    }

    private boolean creditEligibility(Client clientData, String status, double balance) {
        if (balance < 31000) {
            switch (status) {
                case "Célibataire":
                case "Divorcé":
                    return false;
            }
        } else if (balance < 21000 && status.equals("Marié")) return false;

        int year = clientData.getBirthYear();
        return clientData.isMale() ? year <= 1999 : year <= 2002;
    }

    private double getBalance(Client clientData) {
        return Objects.requireNonNull(
                clientData.getAccounts().stream()
                        .findFirst().orElse(null))
                .getBalance();
    }

    public void addManager(AccountManager_ manager) {
        this.managers.add(manager);
    }
}
