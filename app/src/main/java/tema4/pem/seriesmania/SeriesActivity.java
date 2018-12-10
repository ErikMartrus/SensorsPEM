package tema4.pem.seriesmania;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class SeriesActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private NavigationView navigationView;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            onNavigationItemSelected(navigationView.getMenu().getItem(2));

            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_series:
                    //mTextMessage.setText(R.string.title_series);
                    setContentView(R.layout.activity_series);

                    return true;
                case R.id.navigation_films:
                    mTextMessage.setText(R.string.title_films);
                    return true;
                case R.id.navigation_calendar:
                    mTextMessage.setText(R.string.title_calendar);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        mTextMessage = (TextView) findViewById(R.id.message);

    }
}
