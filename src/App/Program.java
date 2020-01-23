package App;

import App.fel.presenter.MenuPresenter;
import App.fel.view.MenuView;

public class Program {
    public static void main(String[] args) throws InterruptedException {
        MenuPresenter.getInstance().MenuDynamics(MenuView.getInstance());
    }

}
