package App.fel.presenter;

import App.bll.control.ManagerService;
import App.bll.model.AccountManager_;
import App.bll.model.Client_;
import App.dal.entities.Account;
import App.dal.entities.Address;
import App.dal.entities.Client;
import App.dal.entities.ClientCreditRequest;
import App.fel.view.MenuView;

import java.util.Random;

public class MenuPresenter {
    private static MenuPresenter instance;

    private MenuPresenter() {
    }

    public static MenuPresenter getInstance() {
        if (instance == null)
            instance = new MenuPresenter();
        return instance;
    }


    public void MenuDynamics(MenuView menuView) throws InterruptedException {

        AccountManager_ manager = ManagerService.getInstance().getAccountManager();
        int tempMenu = 0;
        while (tempMenu != 3) {
            tempMenu = menuView.signInMenu();
            switch (tempMenu) {
                case 1:
                    clientSignInCase(menuView, manager);
                    break;
                case 2:
                    managerSignInCase(menuView, manager);
                    break;
            }
        }
    }

    private void creditRequestClient(Client_ client) throws InterruptedException {

        MenuView.getInstance()
                .showIfAccepted(client
                        .addCreditRequests(new ClientCreditRequest(
                                MenuView.getInstance().addToBalance(),
                                client.getData().getClientNum())));

    }

    private void removeToBalanceClient(Client_ client) {
        client.remToBalance(MenuView.getInstance().removeToBalance());
    }

    private void addToBalanceClient(Client_ client) {
        client.addToBalance(MenuView.getInstance().addToBalance());
    }

    private double getBalance(Client_ client) {
        return client.showBalance();
    }

    private Client_ signInClientLogic(AccountManager_ manager) {
        Client_ client = null;
        MenuView menuView = MenuView.getInstance();
        boolean goodNum = false;
        while (!goodNum) {
            client = getClient(menuView, manager);
            if (client != null)
                goodNum = true;
            else System.out.println("Wrong Num");
        }
        if (client.getData().getAccounts().isEmpty()) {
            createAccount(client, menuView);
        }
        return client;
    }

    private void managerSignInCase(MenuView menuView, AccountManager_ manager) throws InterruptedException {

        boolean goodUserAndPassword;
        int attempts = 0;
        do {
            long id = menuView.deskNum();
            String pass = menuView.password();
            goodUserAndPassword = ManagerService.getInstance()
                    .verifyPassword(id, pass);
            attempts++;
            if (goodUserAndPassword)
                break;
            menuView.againPassword();
        } while (true);

        menuView.attempts(attempts);
        int tempManager = 0;
        while (tempManager != 7) {
            tempManager = menuView.managerMenu();
            manager.sortFirstPositionClient();
            switch (tempManager) {
                case 1:
                    addNewClient(menuView, manager);
                    break;
                case 2:
                    showClientBalance(menuView, manager);
                    break;
                case 3:
                    listClient(menuView, manager);
                    break;
                case 4:
                    creditClient(menuView, manager);
                    break;
                case 5:
                    openNewClientAccount(menuView, manager);
                    break;
                case 6:
                    clientAccountInteraction(menuView, manager);
                    break;
            }
        }
    }

    private void addNewClient(MenuView menuView, AccountManager_ manager) {
        manager.addClient(new Client(
                menuView.lastName(),
                menuView.firstName(),
                menuView.phoneNumber(),
                menuView.email(),
                menuView.male(),
                new Address(
                        menuView.civilNum(),
                        menuView.street(),
                        menuView.city(),
                        menuView.zip()),
                new Random().nextInt(10000),
                menuView.salary(),
                menuView.status(),
                menuView.birthYear(),
                menuView.nip()));
    }

    private void openNewClientAccount(MenuView menuView, AccountManager_ manager) {
        while (true) {
            try {
                manager.createAccount(menuView.signInNum());
                break;
            } catch (NullPointerException e) {
                System.out.println("Wrong Num");
            }
        }
    }

    private Client_ getClient(MenuView menuView, AccountManager_ manager) {
        return manager.getClient(menuView.signInNum());
    }

    private void clientAccountInteraction(MenuView menuView, AccountManager_ manager) {
        manager.accountInteraction(getClient(menuView, manager).getData().getClientNum(), MenuView.getInstance().addToBalance());
    }

    private void creditClient(MenuView menuView, AccountManager_ manager) {
        Client_ client = null;
        boolean goodNum = false;
        while (!goodNum) {
            client = getClient(menuView, manager);
            if (client != null)
                goodNum = true;
            else System.out.println("Wrong Num");
        }
        manager.credit(
                manager.getData().getRequests().stream()
                        .filter(r -> r.getData().getRequestNum() == menuView.requestNum())
                        .findFirst().orElse(null),
                client.getData().getClientNum());
    }

    private void listClient(MenuView menuView, AccountManager_ manager) {
        menuView.listClient(manager.listClient());
    }

    private void showClientBalance(MenuView menuView, AccountManager_ manager) throws InterruptedException {
        while (true) {
            try {
                menuView.showBalanceClient(manager.showBalance(menuView.signInNum()));
                break;
            } catch (NullPointerException e) {
                System.out.println("Wrong Num");
            }
        }
    }

    private void clientSignInCase(MenuView menuView, AccountManager_ manager) throws InterruptedException {
        Client_ client = signInClientLogic(manager);
        int tempClient = 0;
        while (tempClient != 6) {
            tempClient = menuView.clientMenu();
            switch (tempClient) {
                case 1:
                    createAccount(client, menuView);
                    break;
                case 2:
                    addToBalanceClient(client);
                    break;
                case 3:
                    removeToBalanceClient(client);
                    break;
                case 4:
                    showHistory(client);
                    break;
                case 5:
                    menuView.showBalanceClient(MenuPresenter.getInstance().getBalance(client));
                    break;
                case 6:
                    creditRequestClient(client);
                    break;
            }
        }
    }

    private void showHistory(Client_ client) {
        MenuView.getInstance().showHistory(client.showHistory());
    }


    private void createAccount(Client_ client, MenuView menuView) {
        client.getData().getAccounts()
                .add(new Account(true, menuView.createAccount()));
    }


}
