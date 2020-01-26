package App.fel.view;


import App.bll.model.Client_;
import App.dal.entities.Client;
import App.fel.presenter.MenuPresenter;

import java.util.List;
import java.util.Scanner;

public class MenuView {
    private static MenuView instance;

    private MenuView() {
    }

    public static MenuView getInstance() {
        if (instance == null)
            instance = new MenuView();
        return instance;
    }


    public void showBalanceClient(double balance) throws InterruptedException {
        System.out.println(balance + "$");
        Thread.sleep(2000);
    }


    public double createAccount() {
        return getDouble("Please Enter the account balance");
    }


    public int signInMenu() {

        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println("Choose from these choices");
        System.out.println("-------------------------\n");
        System.out.println("1 - Sign In as Client");
        System.out.println("2 - Sign In as Manager");
        System.out.println("3 - Quit");

        selection = input.nextInt();
        return selection;
    }

    public long signInNum() {

        return getInt("Please Enter Your Client Num");
    }

    public double addToBalance() {
        return getDouble("Please Enter the Amount to add");
    }

    public double removeToBalance() {
        return getDouble("Please Enter the Amount to remove");
    }

    public int clientMenu() {

        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println("Choose from these choices");
        System.out.println("-------------------------\n");
        System.out.println("1 - Afficher le solde d’un compte");
        System.out.println("2 - Ajouter de l'argent à l’un des comptes");
        System.out.println("3 - Retirer de l'argent sur l’un des comptes");
        System.out.println("4 - Demander un crédit");
        System.out.println("5 - Afficher l’historique des transactions sur un compte");
        System.out.println("6 - Sign Out");

        selection = input.nextInt();
        return selection;
    }

    public int managerMenu() {

        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println("Choose from these choices");
        System.out.println("-------------------------\n");
        System.out.println("1 - Ajouter un nouveau client");
        System.out.println("2 - Consulter le solde d'un compte d’un client");
        System.out.println("3 - Lister tous les clients");
        System.out.println("4 - Accorder du crédit à un client");
        System.out.println("5 - Ouvrir un nouveau compte à un client");
        System.out.println("6 - Effectuer une opération (Ajout ou Retrait) sur le compte d’un client");
        System.out.println("7 - Sign Out");

        selection = input.nextInt();
        return selection;
    }

    public void showIfAccepted(boolean shouldBeAccepted) throws InterruptedException {
        if (shouldBeAccepted)
            System.out.println("You are eligible");
        else
            System.out.println("You are not eligible");
        Thread.sleep(2000);
    }

    public void showHistory(String showHistory) {
        System.out.println(showHistory);
    }

    public long requestNum() {
        return getInt("Please Enter Your Request Num");
    }

    public void listClient(List<Client> listClient) {
        listClient
                .forEach(c -> System.out.println(c.toString()));
    }

    private double getDouble(String message) {
        double selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println(message);
        System.out.println("-------------------------\n");

        selection = input.nextDouble();
        return selection;
    }

    private long getInt(String message) {
        long selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println(message);
        System.out.println("-------------------------\n");

        selection = input.nextInt();
        return selection;
    }

    private String getString(String message) {
        String selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println(message);
        System.out.println("-------------------------\n");

        selection = input.nextLine();
        return selection;
    }

    public String lastName() {
        return getString("Please Enter Your lastName");
    }

    public String firstName() {
        return getString("Please Enter Your firstName");
    }

    public String phoneNumber() {
        return getString("Please Enter Your phoneNumber");
    }

    public String email() {
        return getString("Please Enter Your email");
    }

    public String civilNum() {
        return getString("Please Enter Your civilNum");
    }

    public String street() {
        return getString("Please Enter Your street");
    }

    public String city() {
        return getString("Please Enter Your city");
    }

    public String zip() {
        return getString("Please Enter Your zip");
    }

    public String status() {
        return getString("Please Enter Your status");
    }

    public double salary() {
        return getDouble("Please Enter the salary");
    }

    public int birthYear() {
        return (int) getInt("Please Enter the birthYear");
    }

    public short nip() {
        return (short) getInt("Please Enter the nip");
    }

    public boolean male() {
        return getBool("Are you male?");
    }

    private boolean getBool(String message) {
        String selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println(message);
        System.out.println("-------------------------\n");

        selection = input.nextLine();
        return selection.toLowerCase().equals("yes");
    }

    public long deskNum() {
        return getInt("Please Enter the Desk Num");
    }

    public String password() {
        return getString("Please Enter the password");
    }

    public void againPassword() {
        System.out.println("Wrong Password/Desk Num, Try again");
    }

    public void attempts(int attempts) throws InterruptedException {
        System.out.println(attempts + " Attempts");

        Thread.sleep(2000);
    }
}
