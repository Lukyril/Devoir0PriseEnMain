package model;

public class Client implements Comparable {
    private ClientData data;

    public Client(ClientData data) {
        this.data = data;
    }

    public ClientData getData() {
        return data;
    }

    public void setData(ClientData data) {
        this.data = data;
    }
    public void addCreditRequests(CRData request){
        data.getCreditRequests().add(request);
        RequestManagement.getInstance().requestCredit(request,this);
    }
    @Override
    public int compareTo(Object o) {
        return 0;
    }
}
