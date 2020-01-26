package App.dal.entities;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class AccountManager extends Person {
    private long deskNum;
    private String password;
    private ArrayList<Client> clients;
    private Set<CreditRequest> requests;

    public AccountManager(String lastName, String firstName, String phoneNumber, String email, boolean sex, Address address, long deskNum) {
        super(lastName, firstName, phoneNumber, email, sex, address);
        this.deskNum = deskNum;
        this.password = "password";
        clients = new ArrayList<>();
        requests = new TreeSet<>();
    }

    public long getDeskNum() {
        return deskNum;
    }

    public void setDeskNum(long deskNum) {
        this.deskNum = deskNum;
    }

    public ArrayList<Client> getClients() {
        return clients;
    }

    public void setClients(ArrayList<Client> clients) {
        this.clients = clients;
    }

    public Set<CreditRequest> getRequests() {
        return requests;
    }

    public void setRequests(Set<CreditRequest> requests) {
        this.requests = requests;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
