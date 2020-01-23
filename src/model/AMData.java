package model;

import java.util.Set;
import java.util.TreeSet;

public class AMData extends Person {
    private long deskNum;
    private Set<Client> clients;

    public AMData(String lastName, String firstName, String phoneNumber, String email, boolean sex, Address address, long deskNum) {
        super(lastName, firstName, phoneNumber, email, sex, address);
        this.deskNum = deskNum;
        clients = new TreeSet<>();
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
}
