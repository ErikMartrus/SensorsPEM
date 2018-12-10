package tema4.pem.seriesmania.login;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import tema4.pem.seriesmania.AppMediador;

public class PresenterLogin implements IPresenterLogin {

    private IModelLogin modelLogin;
    private AppMediador appMediador;

    public PresenterLogin() {
        appMediador = AppMediador.getInstance();
        modelLogin = ModelLogin.getInstance();
    }

    private BroadcastReceiver receptorAvisos = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(AppMediador.AVISO_AUTHENTICATION_SUCCESS)){
                appMediador.getViewLogin().toMenu();

            }
            appMediador.unRegisterReceiver(this);
        }
    };

    @Override
    public void doLogin(String username, String password) {
        appMediador.registerReceiver(receptorAvisos, AppMediador.AVISO_AUTHENTICATION_SUCCESS);
        modelLogin.doLogin(username, password);
    }
}
