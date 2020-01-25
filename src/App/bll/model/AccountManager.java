package App.bll.model;

import App.dal.entities.Account;
import App.dal.entities.CreditRequest;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class AccountManager implements Comparable {
    private App.dal.entities.AccountManager data;

    private Client client;

    public AccountManager(App.dal.entities.AccountManager data) {
        this.data = data;
    }

    public App.dal.entities.AccountManager getData() {
        return data;
    }

    public void setData(App.dal.entities.AccountManager data) {
        this.data = data;
    }


    public void addClient(App.dal.entities.Client client) {
        data.getClients().add(client);
    }

    public double showBalance(long clientNum, long accountNum) {
        return getClient(clientNum).showBalance();
    }

    public double showBalance(long clientNum) {
        return getClient(clientNum).showBalance();
    }

    public List<App.dal.entities.Client> listClient() {
        return data.getClients();
    }

    public void credit(CreditRequest credit, long clientNum, long accountNum) {
        Client tempClient = getClient(clientNum);

        Account tempAccount = getAccountStream(tempClient)
                .filter(c -> c.getNumber() == accountNum)
                .findFirst().orElse(null);

        addCredit(credit, tempClient, tempAccount);

    }

    public void credit(CreditRequest credit, long clientNum) {
        Client tempClient = getClient(clientNum);

        Account tempAccount = getAccountStream(tempClient)
                .findFirst().orElse(null);

        addCredit(credit, tempClient, tempAccount);

    }

    public void createAccount(long clientNum) {
        getClient(clientNum).openAccount(true, 0);
    }

    public void accountInteraction(long clientNum, double amount) {
        getClient(clientNum).addToBalance(amount);
    }


    public Client getClient(long clientNum) {
        if (client == null)
            client = new Client(getData(clientNum));
        else
            client.setData(getData(clientNum));
        return client;
    }

    private App.dal.entities.Client getData(long clientNum) {
        return data.getClients()
                .stream().filter(c -> c.getClientNum() == clientNum)
                .findFirst().orElse(null);
    }

    private Stream<Account> getAccountStream(Client tempClient) {
        return tempClient
                .getData().getAccounts()
                .stream();
    }

    private void addCredit(CreditRequest credit, Client tempClient, Account tempAccount) {
        assert tempAccount != null;

        if (tempClient.getData().getClientCreditRequests().contains(credit.getData())) {
            tempAccount.setBalance(tempAccount.getBalance() + credit.getData().getAmount());
        }
        credit.accept();
    }

    public void sortFirstPositionClient() {

        AtomicReference<App.dal.entities.Client> bestClient = new AtomicReference<>(Objects.requireNonNull(data.getClients()
                .stream().findFirst().orElse(null)));
        AtomicReference<Double> bestSalary = new AtomicReference<>(getSalary(bestClient.get()));
        final int[] i = {-1};
        AtomicReference<Integer> bestPosition = new AtomicReference<Integer>(0);
        data.getClients().forEach(c -> {
            i[0]++;

            if (getSalary(c) > bestSalary.get()) {
                bestClient.set(c);
                bestPosition.set(i[0]);
                bestSalary.set(getSalary(c));
            }
        });
        data.getClients().set(bestPosition.get(), data.getClients().get(0));
        data.getClients().set(0, bestClient.get());
    }

    public double getSalary(App.dal.entities.Client bestClient) {
        return bestClient
                .getSalary();
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

}
