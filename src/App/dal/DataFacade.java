package App.dal;

import App.dal.dao.IDAO;
import App.dal.dao.InMemoryDAO;

public class DataFacade {
    private static DataFacade instance;

    public static DataFacade getInstance() {
        if (instance == null)
            instance = new DataFacade();
        return instance;
    }

    private IDAO dao;

    public IDAO getDao() {
        return dao;
    }

    private DataFacade() {
        dao = new InMemoryDAO();
    }



}
