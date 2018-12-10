package tema4.pem.seriesmania.register;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import tema4.pem.seriesmania.AppMediador;

public class PresenterRegister implements IPresenterRegister {

    private IModelRegister modelRegister;
    private AppMediador appMediador;

    public PresenterRegister() {
        appMediador = AppMediador.getInstance();
        modelRegister = ModelRegister.getInstance();
    }

    private BroadcastReceiver receptorAvisos = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(AppMediador.AVISO_AUTHENTICATION_SUCCESS)){
                appMediador.getViewRegister().toMenu();
            }
            appMediador.unRegisterReceiver(this);
        }
    };


    @Override
    public void register(String username, String password) {
        appMediador.registerReceiver(receptorAvisos, AppMediador.AVISO_AUTHENTICATION_SUCCESS);
        modelRegister.register(username, password);
    }
}
