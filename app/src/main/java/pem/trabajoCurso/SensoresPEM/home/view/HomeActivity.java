package pem.trabajoCurso.SensoresPEM.home.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import pem.trabajoCurso.SensoresPEM.AppMediador;
import pem.trabajoCurso.SensoresPEM.R;
import pem.trabajoCurso.SensoresPEM.films.view.FilmsActivity;
import pem.trabajoCurso.SensoresPEM.home.presenter.IPresenterHome;
import pem.trabajoCurso.SensoresPEM.login.LoginActivity;
import pem.trabajoCurso.SensoresPEM.series.view.SeriesActivity;

public class HomeActivity extends AppCompatActivity implements IViewHome,
        FragmentMasterHome.EscuchaFragmento {

    private AppMediador appMediador;
    private IPresenterHome presentadorPrincipal;
    private FragmentMasterHome fragmentMasterHome;
    private FragmentDetailHome fragmentDetailHome;
    //Declaracion de un objeto llamado fab, que corresponda con un boton flotante


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        appMediador = (AppMediador)this.getApplication();
        presentadorPrincipal = appMediador.getPresenterHome();
        appMediador.setViewHome(this);
        // Se comprueba si la actividad esta usando una version de layout con un contenedor de fragmentos
        // de tipo FrameLayout (si es asi, es un smartphone y no permite mas de un fragmento en pantalla),
        // por tanto, solo se anade el primero
        if (findViewById(R.id.contenedorDeFragmentos) != null) {
            // se crea el fragmento maestro y se anade al contenedor de fragmentos
            fragmentMasterHome = new FragmentMasterHome();
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.contenedorDeFragmentos, fragmentMasterHome)
                    .commit();
        }
        // Creacion de un boton flotante para que, cuando se seleccione, solicite al presentador principal que trate
        // la opcion de agregar una nueva receta
        ImageButton home = (ImageButton) findViewById(R.id.m_home);
        home.setColorFilter(getResources().getColor(R.color.blue));
        ImageButton series = (ImageButton) findViewById(R.id.m_series);
        final ImageButton films = (ImageButton) findViewById(R.id.m_films);
        ImageButton calendar = (ImageButton) findViewById(R.id.m_calendar);


        series.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SeriesActivity.class);
                startActivity(intent);
                finish();
            }
        });

        films.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FilmsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_calendar);
            }
        });
    }


    @Override
    protected void onStart() {
        super.onStart();
        // TODO Solicitar al presentador que recupere los datos desde el modelo.
        presentadorPrincipal.obtenerDatos();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        appMediador.removePresenterHome();
    }

    // create an action bar button
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    // handle button activities
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.mybutton) {
            // do something here
            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("RestrictedApi")
    @Override
    public void alSeleccionarItem(int posicion) {
        // Si no hay fragmento detalle, se crea la vista detalle (esto ocurre si es panel unico)
        if (fragmentDetailHome == null)
            fragmentDetailHome = new FragmentDetailHome();

        if (findViewById(R.id.contenedorDeFragmentos) != null) {
            // si es de panel unico, se reemplaza, en el contenedor de fragmentos
            // el fragmento que esta visible por el de la vista detalle
            FragmentTransaction transaccion = getSupportFragmentManager().beginTransaction();
            transaccion.replace(R.id.contenedorDeFragmentos, fragmentDetailHome);
            transaccion.addToBackStack(null);
            transaccion.commit();
            // Quita la visibilidad al boton flotante (para que no aparezca en el detalle)

            // realiza la transaccion
            getSupportFragmentManager().executePendingTransactions();
        }
        // TODO Solicitar al presentador que trate el item seleccionado.
        presentadorPrincipal.obtenerDetalle(posicion);

    }


    // Redefinicion del metodo onBackPressed para que si se tiene un dispositivo de panel unico, y el boton
    // flotante no esta visible (esta el fragmento detalle en pantalla), reemplace el fragmento detalle por el
    // fragmento maestro. En cualquier otro caso, la actividad debe finalizar (porque se quiere salir de ella)
    @SuppressLint("RestrictedApi")
	@Override
    public void onBackPressed(){
	    if (findViewById(R.id.contenedorDeFragmentos)!=null){
	        //es panel único
            //está en la vista del detalle
            FragmentTransaction transaccion = getSupportFragmentManager().beginTransaction();
            transaccion.replace(R.id.contenedorDeFragmentos,fragmentMasterHome);
            transaccion.addToBackStack(null);
            transaccion.commit();
            presentadorPrincipal.obtenerDatos();


        }else
	        //no es panel único
            finish();
    }



    // TODO Añadir el método actualizarMaestro(Object[] datos) que actualiza la lista maestro con los datos
    // recibidos por parámetros. En cada entrada del vector, está el nombre de una receta.
    @Override
    public void actualizarMaestro(Object[] datos) {
        // TODO Dentro del método actualizarMaestro(Object[] datos), crear la lista maestro con los nombres
        // de las recetas que entran por parámetros.
        fragmentMasterHome.crearLista((String[]) datos);
        // TODO Dentro del método actualizarMaestro(Object[] datos), si es una pantalla multi-panel, presentar
        // el detalle de la primera receta.
        if (findViewById(R.id.contenedorDeFragmentos) == null){
            presentadorPrincipal.obtenerDetalle(0);
        }


    }

    // TODO Añadir el método actualizarDetalle(Object[] datos) que actualiza los valores del detalle,
    // teniendo en cuenta que en la posición 0 del vector está el nombre de la receta y en qué se usa
    // para realizarla, en la posición 1 del vector está la imagen como un Bitmap y en en la posición 3
    // del vector está la descripción de la receta.
    @Override
    public void actualizarDetalle(Object[] datos) {
        fragmentDetailHome.actualizarNombreReceta((String) datos[0]);
        fragmentDetailHome.actualizarImagenReceta((Bitmap) datos[1]);
        fragmentDetailHome.actualizarDescripcion((String) datos[2]);
    }


}
