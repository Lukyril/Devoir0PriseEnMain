package model;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class RequestManagement {
    private static RequestManagement instance;

    private Set<AccountManager> managers;

    private RequestManagement() {
        managers = new TreeSet<>();
    }

    public static RequestManagement getInstance() {
        if (instance == null)
            instance = new RequestManagement();
        return instance;
    }

    public void requestCredit(CRData request, Client client) {
        Objects.requireNonNull(
                managers.stream().filter(m -> m.getData().getClients().contains(client))
                        .findFirst().orElse(null))
                .getRequests().add(new CreditRequest(request,4.6));
    }

    public void addManager(AccountManager manager) {
        this.managers.add(manager);
    }
}
