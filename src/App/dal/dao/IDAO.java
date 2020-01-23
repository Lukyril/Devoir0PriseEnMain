package App.dal.dao;

import App.dal.entities.AccountManager;
import App.dal.entities.Client;

public interface IDAO {
    Client initClient();
    AccountManager initManager();
}
