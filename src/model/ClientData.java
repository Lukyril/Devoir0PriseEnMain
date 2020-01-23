package model;

import java.time.Year;
import java.util.Set;
import java.util.TreeSet;

public class ClientData extends Person {
    @Override
    public String toString() {
        return "ClientData{" + "\n" +
                "clientNum=" + clientNum + ", \n" +
                "salary=" + salary + ", \n" +
                "status='" + status + '\'' + ", \n" +
                "birthYear=" + birthYear + ", \n" +
                "nip=" + nip + ", \n" +
                "accounts=" + accounts + ", \n" +
                "creditRequests=" + creditRequests + "\n" +
                '}';
    }

    private long clientNum;
    private double salary;
    private String status;
    private int birthYear;
    private short nip;
    private Set<Account> accounts;
    private Set<CRData> creditRequests;

    public ClientData(String lastName, String firstName, String phoneNumber, String email, boolean sex, Address address, long clientNum, double salary, String status, int birthYear, short nip) {
        super(lastName, firstName, phoneNumber, email, sex, address);
        this.clientNum = clientNum;
        this.salary = salary;
        this.status = status;
        this.birthYear = birthYear;
        this.nip = nip;
        accounts = new TreeSet<>();
        creditRequests = new TreeSet<>();
    }

    public long getClientNum() {
        return clientNum;
    }

    public void setClientNum(long clientNum) {
        this.clientNum = clientNum;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getBirthYear() {
        return birthYear;
    }

    public void setBirthYear(int birthYear) {
        this.birthYear = birthYear;
    }

    public short getNip() {
        return nip;
    }

    public void setNip(short nip) {
        this.nip = nip;
    }

    public Set<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Set<Account> accounts) {
        this.accounts = accounts;
    }

    public Set<CRData> getCreditRequests() {
        return creditRequests;
    }


    public void setCreditRequests(Set<CRData> creditRequests) {
        this.creditRequests = creditRequests;
    }
}
