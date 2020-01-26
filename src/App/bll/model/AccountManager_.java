package App.bll.model;

import App.dal.entities.Account;
import App.dal.entities.AccountManager;
import App.dal.entities.Client;
import App.dal.entities.CreditRequest;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Stream;

public class AccountManager_ implements Comparable {

    private AccountManager data;

    public AccountManager getData() {
        return data;
    }

    public void setData(AccountManager data) {
        this.data = data;
    }

    private Client_ client;

    public Client_ getClient(long clientNum) {
        if (client == null)
            client = new Client_(getData(clientNum));
        else
            client.setData(getData(clientNum));
        return client;
    }

    private Client getData(long clientNum) {
        return data.getClients().stream()
                .filter(c -> c.getClientNum() == clientNum)
                .findFirst().orElse(null);
    }

    public AccountManager_(AccountManager data) {
        this.data = data;
    }


    public void addClient(Client client) {
        data.getClients().add(client);
    }

    public double showBalance(long clientNum, long accountNum) {
        return getClient(clientNum).showBalance();
    }

    public double showBalance(long clientNum) {
        return getClient(clientNum).showBalance();
    }

    public List<Client> listClient() {
        return data.getClients();
    }

    public void credit(CreditRequest credit, long clientNum, long accountNum) {
        Client_ tempClient = getClient(clientNum);

        Account tempAccount = getAccountStream(tempClient)
                .filter(c -> c.getNumber() == accountNum)
                .findFirst().orElse(null);

        addCredit(credit, tempClient, tempAccount);

    }

    public void credit(CreditRequest credit, long clientNum) {
        Client_ tempClient = getClient(clientNum);

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


    private Stream<Account> getAccountStream(Client_ tempClient) {
        return tempClient
                .getData().getAccounts()
                .stream();
    }

    private void addCredit(CreditRequest credit, Client_ tempClient, Account tempAccount) {
        assert tempAccount != null;

        if (tempClient.getData().getClientCreditRequests().contains(credit.getData()))
            tempAccount.setBalance(tempAccount.getBalance() + credit.getData().getAmount());
        credit.accept();
    }


    public void sortFirstPositionClient() {
        AtomicReference<Client> bestClient =
                new AtomicReference<>(Objects.requireNonNull(
                        data.getClients().stream()
                                .findFirst().orElse(null)));

        AtomicReference<Double> bestSalary =
                new AtomicReference<>(getSalary(bestClient.get()));

        AtomicReference<Integer> bestPosition =
                new AtomicReference<>(0);

        findBestSalary(bestClient, bestSalary, bestPosition);

        setNewFirstPosition(bestClient, bestPosition);
    }

    private void findBestSalary(
            AtomicReference<Client> bestClient,
            AtomicReference<Double> bestSalary,
            AtomicReference<Integer> bestPosition) {

        final int[] i = {-1};
        data.getClients()
                .forEach(c -> {
                    i[0]++;

                    if (getSalary(c) > bestSalary.get()) {
                        bestClient.set(c);
                        bestPosition.set(i[0]);
                        bestSalary.set(getSalary(c));
                    }
                });
    }

    public double getSalary(Client bestClient) {
        return bestClient
                .getSalary();
    }

    private void setNewFirstPosition(AtomicReference<Client> bestClient, AtomicReference<Integer> bestPosition) {
        data.getClients()
                .set(bestPosition.get(), data.getClients().get(0));

        data.getClients()
                .set(0, bestClient.get());
    }


    public void getStats() {
        AtomicReference<Client> bestClient = new AtomicReference<>(data.getClients().get(0));
        AtomicReference<Double> bestSalary = new AtomicReference<>(bestClient.get().getSalary());
        AtomicReference<Client> worstClient = new AtomicReference<>(data.getClients().get(0));
        AtomicReference<Double> worstSalary = new AtomicReference<>(bestClient.get().getSalary());
        AtomicReference<Double> totalSalary = new AtomicReference<>((double) 0);
        data.getClients().forEach(c -> {
            double currentSalary = getSalary(c);
            totalSalary.updateAndGet(v -> v + currentSalary);
            if (currentSalary >= bestSalary.get()) {
                bestSalary.set(currentSalary);
                bestClient.set(c);
            }
            if (currentSalary <= worstSalary.get()) {
                worstSalary.set(currentSalary);
                worstClient.set(c);
            }
        });
        String stats = "Best: " + bestClient.get().getFirstName() + " " + bestClient.get().getLastName() +
                "|| " + bestSalary +
                "\nWorst: " + worstClient.get().getFirstName() + " " + worstClient.get().getLastName() +
                "|| " + worstSalary +
                "\nDifference: " + (bestSalary.get() - worstSalary.get()) +
                "\nAVG: " + totalSalary.get() / data.getClients().size();
        System.out.println(stats);

    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

}
