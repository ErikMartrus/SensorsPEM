package tema4.pem.seriesmania.series.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;

import java.util.ArrayList;

import tema4.pem.seriesmania.series.model.IModeloSeries;
import tema4.pem.seriesmania.series.model.ItemSeries;
import tema4.pem.seriesmania.series.model.ModeloSeries;
import tema4.pem.seriesmania.AppMediador;

public class PresenterSeries implements IPresenterSeries {

    private AppMediador appMediador;

	// TODO Declarar una variable modelo para acceder al ModelHome
    private IModeloSeries modelo;

	// TODO Declarar e implementar el receptor broadcast que espera por la notificacion del modelo.
	// El modelo notificara cuando los datos de los telefonos a notificar estan disponibles.
    private BroadcastReceiver receptorAvisos = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(AppMediador.AVISO_DATOS_LISTOS)){
                ArrayList<ItemSeries> infoReceta = (ArrayList<ItemSeries>) intent.getSerializableExtra(AppMediador.CLAVE_LISTA_RECETAS);
                String[] datos = new String[infoReceta.size()];
                for (int i = 0; i < infoReceta.size(); i++){
                    datos[i] = infoReceta.get(i).getNombreReceta();
                }
                appMediador.getViewSeries().actualizarMaestro(datos);
            } else if (intent.getAction().equals(AppMediador.AVISO_DETALLE_LISTO)){
                String[] datosDetalle = intent.getStringArrayExtra(AppMediador.CLAVE_DETALLE_RECETA);
                Object[] datos = new Object[3];
                datos[0] = datosDetalle[0] + "(" + datosDetalle[1] + ")";
                datos[1] = BitmapFactory.decodeFile(datosDetalle[2]);
                datos[2] = datosDetalle[3];
                appMediador.getViewSeries().actualizarDetalle(datos);
            }
            appMediador.unRegisterReceiver(this);
        }
    };

	// TODO Implementar un constructor que crea el modelo.
    public PresenterSeries(){
        appMediador = AppMediador.getInstance();
        modelo = ModeloSeries.getInstance();
    }


    // TODO Implementar el metodo obtenerDatos() que registra el receptor para recibir notificaciones y
	// solicita al modelo que recupere los datos de la lista maestro.
    @Override
    public void obtenerDatos() {
        appMediador.registerReceiver(receptorAvisos, AppMediador.AVISO_DATOS_LISTOS);
        modelo.obtenerDatos();

    }

    // TODO Implementar el metodo obtenerDetalle(int posicion) que registra el receptor para recibir
	// notificaciones y solicita al modelo que recupere los datos de la lista detalle para una receta dada su posicion.
    @Override
    public void obtenerDetalle(int posicion) {
        appMediador.registerReceiver(receptorAvisos, AppMediador.AVISO_DETALLE_LISTO);
        modelo.obtenerDetalle(posicion);
    }

	// TODO Implementar el metodo tratarAgregar() que lanza la vista de agregacion por medio del mediador.

}
