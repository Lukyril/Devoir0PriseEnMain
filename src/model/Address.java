package model;

public class Address {
    private String civilNum;
    private String street;
    private String city;
    private String zip;

    public Address(String civilNum, String street, String city, String zip) {
        this.civilNum = civilNum;
        this.street = street;
        this.city = city;
        this.zip = zip;
    }

    public String getCivilNum() {
        return civilNum;
    }

    public void setCivilNum(String civilNum) {
        this.civilNum = civilNum;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }
}
