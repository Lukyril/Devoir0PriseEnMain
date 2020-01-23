package App.fel.view;


import App.fel.presenter.MenuPresenter;

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


    public void showBalanceClient(App.bll.model.Client client) throws InterruptedException {
        System.out.println(MenuPresenter.getInstance().getBalance(client) + "$");
        Thread.sleep(2000);
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
}
