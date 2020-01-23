package App.bll.model;

import App.dal.entities.Account;
import App.dal.entities.ClientCreditRequest;

import static App.bll.control.RequestManagement.getInstance;

public class Client implements Comparable {
    private App.dal.entities.Client data;

    public Client(App.dal.entities.Client data) {
        this.data = data;

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
    }

    public void remToBalance(double balance) {
        Account account = getAccount();
        account.setBalance(account.getBalance() - balance);
    }

    public String showHistory() {
        return "";
    }

    public double showBalance() {
        Account account = getAccount();
        return account.getBalance();
    }

    public void addCreditRequests(ClientCreditRequest request) {
        data.getClientCreditRequests().add(request);
        getInstance().requestCredit(request, this);
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
