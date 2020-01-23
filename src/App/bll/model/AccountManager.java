package App.bll.model;

import App.dal.entities.Account;
import App.dal.entities.CreditRequest;

import java.util.Set;
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

    public Set<App.dal.entities.Client> listClient() {
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


    @Override
    public int compareTo(Object o) {
        return 0;
    }

}
