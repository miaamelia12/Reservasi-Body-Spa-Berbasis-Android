package pnj.uas.fahmia_amelia.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

import pnj.uas.fahmia_amelia.R;
import pnj.uas.fahmia_amelia.fragment.HomeFragment;
import pnj.uas.fahmia_amelia.fragment.LocationFragment;
import pnj.uas.fahmia_amelia.fragment.TreatmentFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNav;
    HomeFragment homeFragment = new HomeFragment();
    TreatmentFragment treatmentFragment = new TreatmentFragment();
//    AccountFragment accountFragment = new AccountFragment();
    LocationFragment locationFragment = new LocationFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNav = findViewById(R.id.bottomNav);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.frameAll, homeFragment);
        fragmentTransaction.commit();

        bottomNav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                FragmentTransaction fragmentTransaction1 = getSupportFragmentManager().beginTransaction();
                switch (item.getItemId()) {
                    case R.id.home:
                        fragmentTransaction1.replace(R.id.frameAll, homeFragment);
                        break;
                    case R.id.treatment:
                        fragmentTransaction1.replace(R.id.frameAll, treatmentFragment);
                        break;
                    case R.id.location:
                        fragmentTransaction1.replace(R.id.frameAll, locationFragment);
                        break;
                }
                fragmentTransaction1.commit();
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.actionList){
            Intent intent2 = new Intent(this, ListReservationActivity.class);
            startActivity(intent2);
        } else {
            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(this, SignInActivity.class));
        }
        return true;
    }
}
