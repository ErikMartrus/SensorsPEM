package tema4.pem.seriesmania.register;

public interface IViewRegister {


    void onStop();
    void toLogin();
    void toMenu();

    void showProgress();
    void hideProgress();
    void clearForm();
    void showError();
}
