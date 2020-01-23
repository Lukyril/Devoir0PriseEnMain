package model;

import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

public class AccountManager implements Comparable {
    private AMData data;
    private Set<CreditRequest> requests;

    public AccountManager(AMData data) {
        this.data = data;
        requests = new TreeSet<>();
    }

    public AMData getData() {
        return data;
    }

    public void setData(AMData data) {
        this.data = data;
    }

    public void addClient(Client client) {
        data.getClients().add(client);
    }

    public double showBalance(Client client, Account account) {
        return Objects
                .requireNonNull(Objects
                        .requireNonNull(data.getClients()
                                .stream().filter(c -> c == client)
                                .findFirst().orElse(null))
                        .getData()
                        .getAccounts()
                        .stream().filter(a -> a == account)
                        .findFirst().orElse(null))
                .getBalance();
    }

    public double showBalance(Client client) {
        return Objects
                .requireNonNull(Objects
                        .requireNonNull(data.getClients()
                                .stream().filter(c -> c == client)
                                .findFirst().orElse(null))
                        .getData()
                        .getAccounts()
                        .stream().findFirst().orElse(null))
                .getBalance();
    }

    public Set<Client> listClient() {
        return data.getClients();
    }

    public void credit(CreditRequest credit, long clientNum, long number) {
        Client tempClient = getClient(clientNum);

        Account tempAccount = getAccountStream(tempClient)
                .filter(c -> c.getNumber() == number)
                .findFirst().orElse(null);

        addCredit(credit, tempClient, tempAccount);

    }

    public void credit(CreditRequest credit, long clientNum) {
        Client tempClient = getClient(clientNum);

        Account tempAccount = getAccountStream(tempClient)
                .findFirst().orElse(null);

        addCredit(credit, tempClient, tempAccount);

    }

    private Client getClient(long clientNum) {
        Client tempClient = data.getClients()
                .stream().filter(c -> c.getData().getClientNum() == clientNum)
                .findFirst().orElse(null);

        assert tempClient != null;
        return tempClient;
    }

    private Stream<Account> getAccountStream(Client tempClient) {
        return tempClient
                .getData().getAccounts()
                .stream();
    }

    private void addCredit(CreditRequest credit, Client tempClient, Account tempAccount) {
        assert tempAccount != null;

        if (tempClient.getData().getCreditRequests().contains(credit.getData())) {
            tempAccount.setBalance(tempAccount.getBalance() + credit.getData().getAmount());
        }
        credit.accept();
    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public Set<CreditRequest> getRequests() {
        return requests;
    }

    public void setRequests(Set<CreditRequest> requests) {
        this.requests = requests;
    }
}
