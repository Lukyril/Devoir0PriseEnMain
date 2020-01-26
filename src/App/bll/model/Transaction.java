package App.bll.model;

public class Transaction {
    private String action;

    @Override
    public String toString() {
        return "\nTransaction{" +
                "action='" + action + '\'' +
                "}";
    }

    public Transaction(String action) {
        this.action = action;
    }

}
