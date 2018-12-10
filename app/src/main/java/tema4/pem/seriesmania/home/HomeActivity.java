package tema4.pem.seriesmania.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import tema4.pem.seriesmania.R;

public class HomeActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private NavigationView navigationView;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_series:
                    //mTextMessage.setText(R.string.title_series);
                    setContentView(R.layout.activity_series);
                    //onNavigationItemSelected(navigationView.getMenu().getItem(1));


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

        ImageButton home = (ImageButton) findViewById(R.id.m_home);
        ImageButton series = (ImageButton) findViewById(R.id.m_series);
        ImageButton films = (ImageButton) findViewById(R.id.m_films);
        ImageButton calendar = (ImageButton) findViewById(R.id.m_calendar);

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_home);
            }
        });

        series.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_series);
            }
        });

        films.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_films);
            }
        });

        calendar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_calendar);
            }
        });

    }

}
