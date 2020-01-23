package App.dal.entities;

import java.util.Random;

public class Account implements Comparable {
    @Override
    public String toString() {
        return "\n\tAccount{" +"\n\t"+
                "number=" + number +", \n\t"+
                "type=" + type +", \n\t"+
                "balance=" + balance +"\n\t"+
                '}';
    }

    private long number;
    private boolean type;
    private double balance;
    //FK
    private long clientNum;

    public Account(long number, boolean type, double balance) {
        this.number = number;
        this.type = type;
        this.balance = balance;
    }
    public Account(boolean type, double balance) {
        this.number = new Random().nextInt();
        this.type = type;
        this.balance = balance;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public long getClientNum() {
        return clientNum;
    }

    public void setClientNum(long clientNum) {
        this.clientNum = clientNum;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
