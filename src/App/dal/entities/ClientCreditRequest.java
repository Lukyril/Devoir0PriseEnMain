package App.dal.entities;

public class ClientCreditRequest implements Comparable {
    @Override
    public String toString() {
        return "\n\tRequest{" +"\n\t"+
                "amount=" + amount +"\n\t"+
                '}';
    }

    private double amount;
    private long clientNum;

    public ClientCreditRequest(double amount, long clientNum) {
        this.amount = amount;
        this.clientNum = clientNum;
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
