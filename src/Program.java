import model.*;
import view.*;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws InterruptedException {
        Menu menu = Menu.getInstance();
        AccountManager manager = menu.initManager();
        RequestManagement.getInstance().addManager(manager);
        for (int i = 0; i < 5; i++)
            manager.addClient(menu.initClient());
        int tempMenu = 0;
        while (tempMenu != 3) {
            switch (menu.signInMenu()) {
                case 1:
                    Client client = menu.signInClientLogic(manager);
                    int tempClient = 0;
                    while (tempClient != 6) {
                        tempClient = menu.clientMenu();
                        switch (tempClient) {
                            case 1:
                                menu.showBalanceClient(client);
                                break;
                            case 2:
                                menu.addToBalanceClient(client);
                                break;
                            case 3:
                                menu.removeToBalanceClient(client);
                                break;
                            case 4:
                                menu.creditRequestClient(client);
                                break;
                        }
                    }
                    break;
                case 2:
                    int tempManager = 0;
                    while (tempManager != 7) {
                        tempManager = menu.managerMenu();
                        switch (tempManager) {
                            case 3:
                                manager.listClient()
                                        .forEach(c -> System.out.println(c.getData().toString()));
                        }
                    }
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        }


    }


}
