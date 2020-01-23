package App.fel.presenter;

import App.bll.control.RequestManagement;
import App.dal.DataFacade;
import App.dal.entities.Account;
import App.dal.entities.ClientCreditRequest;
import App.fel.view.MenuView;

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
        App.bll.model.AccountManager manager = new App.bll.model.AccountManager(DataFacade.getInstance().getDao().initManager());
        RequestManagement.getInstance().addManager(manager);
        for (int i = 0; i < 5; i++)
            manager.addClient(DataFacade.getInstance().getDao().initClient());
        int tempMenu = 0;
        while (tempMenu != 3) {
            tempMenu = menuView.signInMenu();
            switch (tempMenu) {
                case 1:
                    App.bll.model.Client client = signInClientLogic(manager);
                    int tempClient = 0;
                    while (tempClient != 6) {
                        tempClient = menuView.clientMenu();
                        switch (tempClient) {
                            case 1:
                                menuView.showBalanceClient(client);
                                break;
                            case 2:
                                addToBalanceClient(client);
                                break;
                            case 3:
                                removeToBalanceClient(client);
                                break;
                            case 4:
                                creditRequestClient(client);
                                break;
                        }
                    }
                    break;
                case 2:
                    int tempManager = 0;
                    while (tempManager != 7) {
                        tempManager = menuView.managerMenu();
                        switch (tempManager) {
                            case 3:
                                manager.listClient()
                                        .forEach(c -> System.out.println(c.toString()));
                        }
                    }
                    break;
            }
        }
    }


    public void creditRequestClient(App.bll.model.Client client) {
        client.addCreditRequests(new ClientCreditRequest(MenuView.getInstance().addToBalance(), client.getData().getClientNum()));
    }

    public void removeToBalanceClient(App.bll.model.Client client) {
        client.remToBalance(MenuView.getInstance().removeToBalance());
    }

    public void addToBalanceClient(App.bll.model.Client client) {
        client.addToBalance(MenuView.getInstance().addToBalance());
    }

    public double getBalance(App.bll.model.Client client) {
        return client.showBalance();
    }

    public App.bll.model.Client signInClientLogic(App.bll.model.AccountManager manager) {
        App.bll.model.Client client = null;
        MenuView menuView = MenuView.getInstance();
        boolean goodNum = false;
        while (!goodNum) {
            int temp = menuView.signInNum();
            client = manager.getClient(temp);
            if (client != null)
                goodNum = true;
            else System.out.println("Wrong Num");
        }
        if (client.getData().getAccounts().isEmpty())
            client.getData().getAccounts()
                    .add(new Account(true, menuView.createAccount()));
        return client;
    }


}
