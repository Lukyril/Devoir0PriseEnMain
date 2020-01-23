package model;

public class CreditRequest implements Comparable {
    private CRData data;
    private double interest;

    private boolean accepted;

    public CreditRequest(CRData data, double interest) {
        this.data = data;
        this.interest = interest;
        accepted = false;
    }

    public void accept() {
        if (!accepted)
            accepted = true;
    }

    public CRData getData() {
        return data;
    }

    public void setData(CRData data) {
        this.data = data;
    }

    @Override
    public int compareTo(Object o) {
        return 0;
    }

    public double getInterest() {
        return interest;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }
}
