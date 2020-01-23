package view;

import model.*;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Menu {
    private static Menu instance;

    private Menu() {
    }

    public static Menu getInstance() {
        if (instance == null)
            instance = new Menu();
        return instance;
    }

    public void creditRequestClient(Client client) {
        client.addCreditRequests(new CRData(addToBalance(), client.getData().getClientNum()));
    }

    public void removeToBalanceClient(Client client) {
        Objects.requireNonNull(client.getData().getAccounts()
                .stream().findFirst().orElse(null))
                .setBalance(getBalance(client) - removeToBalance());
    }

    public void addToBalanceClient(Client client) {
        Objects.requireNonNull(client.getData().getAccounts()
                .stream().findFirst().orElse(null))
                .setBalance(getBalance(client) + addToBalance());
    }

    public void showBalanceClient(Client client) throws InterruptedException {
        System.out.println(getBalance(client) + "$");
        Thread.sleep(2000);
    }

    public Client signInClientLogic(AccountManager manager) {
        Client client = null;
        boolean goodNum = false;
        while (!goodNum) {
            int temp = signInNum();
            client = manager.getData().getClients()
                    .stream().filter(c -> c.getData().getClientNum() == temp)
                    .findFirst().orElse(null);
            if (client != null)
                goodNum = true;
            else System.out.println("Wrong Num");
        }
        if (client.getData().getAccounts().isEmpty())
            client.getData().getAccounts().add(new Account(true, createAccount()));
        return client;
    }

    public double createAccount() {

        double selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println("Please Enter the account balance");
        System.out.println("-------------------------\n");

        selection = input.nextDouble();
        return selection;
    }

    public double getBalance(Client client) {
        return client.getData().getAccounts()
                .stream().findFirst().orElse(null).getBalance();
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

    public int signInNum() {

        int selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println("Please Enter Your Nip");
        System.out.println("-------------------------\n");

        selection = input.nextInt();
        return selection;
    }

    public double addToBalance() {

        double selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println("Please Enter the Amount to add");
        System.out.println("-------------------------\n");

        selection = input.nextDouble();
        return selection;
    }

    public double removeToBalance() {

        double selection;
        Scanner input = new Scanner(System.in);

        /***************************************************/

        System.out.println("Please Enter the Amount to remove");
        System.out.println("-------------------------\n");

        selection = input.nextDouble();
        return selection;
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

    public Client initClient() {
        return new Client(new ClientData(
                "Desforges",
                "Luc",
                "4504504500",
                "desforgesluc00@gmail.com",
                true,
                new Address(
                        "28",
                        "Saint-Francois",
                        "Valleyfield",
                        "J6T3Y4"),
                new Random().nextInt(),
                16.5,
                "couple",
                2000,
                (short) 1234));
    }

    public AccountManager initManager() {
        return new AccountManager(
                new AMData(
                        "Desforges",
                        "Luc",
                        "4504504500",
                        "desforgesluc00@gmail.com",
                        true,
                        new Address(
                                "28",
                                "Saint-Francois",
                                "Valleyfield",
                                "J6T3Y4"),
                        123));
    }
}
