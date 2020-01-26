package App.dal.entities;

import java.util.Random;

public class ClientCreditRequest implements Comparable {
    @Override
    public String toString() {
        return "\n\tRequest{" + "\n\t" +
                "amount=" + amount + "\n\t" +
                '}';
    }

    private long requestNum;
    private double amount;
    private long clientNum;

    public ClientCreditRequest(double amount, long clientNum) {
        requestNum = new Random().nextInt(10000);
        this.amount = amount;
        this.clientNum = clientNum;
    }

    public long getRequestNum() {
        return requestNum;
    }

    public void setRequestNum(long requestNum) {
        this.requestNum = requestNum;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public long getClientNum() {
        return clientNum;
    }
}
