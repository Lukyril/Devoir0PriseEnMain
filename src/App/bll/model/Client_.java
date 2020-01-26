package App.bll.model;

import App.dal.entities.Account;
import App.dal.entities.Client;
import App.dal.entities.ClientCreditRequest;

import java.util.ArrayList;
import java.util.List;

import static App.bll.control.RequestManagement.getInstance;

public class Client_ implements Comparable {

    private Client data;
    public Client getData() {
        return data;
    }
    public void setData(Client data) {
        this.data = data;
    }

    private List<Transaction> transactions;

    public Client_(Client data) {
        this.data = data;
        transactions = new ArrayList<>();

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

        if (getInstance().requestCredit(request, this)) {
            data.getClientCreditRequests().add(request);
            transactions.add(new Transaction("addCreditRequests: " + request.getAmount() + "$"));
            return true;
        } else
            return false;
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
