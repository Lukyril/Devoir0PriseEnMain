package model;

public abstract class Person {
    private String lastName;
    private String firstName;
    private String phoneNumber;
    private String email;
    private boolean sex;
    private Address address;

    protected Person(String lastName, String firstName, String phoneNumber, String email, boolean sex, Address address) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.sex = sex;
        this.address = address;
    }

    protected String getLastName() {
        return lastName;
    }

    protected void setLastName(String lastName) {
        this.lastName = lastName;
    }

    protected String getFirstName() {
        return firstName;
    }

    protected void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    protected String getPhoneNumber() {
        return phoneNumber;
    }

    protected void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    protected String getEmail() {
        return email;
    }

    protected void setEmail(String email) {
        this.email = email;
    }

    protected boolean isMale() {
        return sex;
    }

    protected void setMale(boolean sex) {
        this.sex = sex;
    }

    protected Address getAddress() {
        return address;
    }

    protected void setAddress(Address address) {
        this.address = address;
    }
}
