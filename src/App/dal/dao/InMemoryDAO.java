package App.dal.dao;

import App.dal.entities.AccountManager;
import App.dal.entities.Address;
import App.dal.entities.Client;

import java.util.Random;

public class InMemoryDAO implements IDAO {
    public Client initClient() {
        return new Client(
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
                (short) 1234);
    }

    public AccountManager initManager() {
        return new AccountManager(
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
                        123);
    }
}
