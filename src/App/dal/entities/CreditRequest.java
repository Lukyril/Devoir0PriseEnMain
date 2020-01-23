package App.dal.entities;

public class CreditRequest implements Comparable {
    private ClientCreditRequest data;
    private double interest;
    private boolean accepted;
    //FK
    private long deskNum;

    public CreditRequest(ClientCreditRequest data, double interest) {
        this.data = data;
        this.interest = interest;
        accepted = false;
    }

    public void accept() {
        if (!accepted)
            accepted = true;
    }

    public ClientCreditRequest getData() {
        return data;
    }

    public void setData(ClientCreditRequest data) {
        this.data = data;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public long getDeskNum() {
        return deskNum;
    }

    public void setDeskNum(long deskNum) {
        this.deskNum = deskNum;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
