package App.bll.model;

import App.dal.entities.Account;
import App.dal.entities.ClientCreditRequest;

import java.util.ArrayList;
import java.util.List;

import static App.bll.control.RequestManagement.getInstance;

public class Client implements Comparable {
    private App.dal.entities.Client data;
    private List<Transaction> transactions;

    public Client(App.dal.entities.Client data) {
        this.data = data;
        transactions = new ArrayList<>();

    }

    public App.dal.entities.Client getData() {
        return data;
    }

    public void setData(App.dal.entities.Client data) {
        this.data = data;
    }


    public void openAccount(boolean type, double balance) {


    }

    public void addToBalance(double balance) {
        Account account = getAccount();
        account.setBalance(account.getBalance() + balance);
        transactions.add(new Transaction("addToBalance: " + balance + "$"));
    }

    public void remToBalance(double balance) {
        Account account = getAccount();
        account.setBalance(account.getBalance() - balance);
        transactions.add(new Transaction("remToBalance: " + balance + "$"));
    }

    public String showHistory() {
        return transactions.toString();
    }

    public double showBalance() {
        Account account = getAccount();
        return account.getBalance();
    }

    public boolean addCreditRequests(ClientCreditRequest request) {
        data.getClientCreditRequests().add(request);
        transactions.add(new Transaction("addCreditRequests: " + request.getAmount() + "$"));
        return getInstance().requestCredit(request, this);
    }

    private Account getAccount() {
        Account account = data.getAccounts()
                .stream().findFirst().orElse(null);
        assert account != null;
        return account;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
