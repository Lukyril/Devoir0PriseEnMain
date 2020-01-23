package App.dal.entities;


import java.util.Set;
import java.util.TreeSet;

public class AccountManager extends Person {
    private long deskNum;
    private Set<Client> clients;
    private Set<CreditRequest> requests;

    public AccountManager(String lastName, String firstName, String phoneNumber, String email, boolean sex, Address address, long deskNum) {
        super(lastName, firstName, phoneNumber, email, sex, address);
        this.deskNum = deskNum;
        clients = new TreeSet<>();
        requests = new TreeSet<>();
    }

    public long getDeskNum() {
        return deskNum;
    }

    public void setDeskNum(long deskNum) {
        this.deskNum = deskNum;
    }

    public Set<Client> getClients() {
        return clients;
    }

    public void setClients(Set<Client> clients) {
        this.clients = clients;
    }

    public Set<CreditRequest> getRequests() {
        return requests;
    }

    public void setRequests(Set<CreditRequest> requests) {
        this.requests = requests;
    }
}
