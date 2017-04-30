package ee.kg.paike.saaremaapaike;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import butterknife.BindView;
import butterknife.ButterKnife;
import ee.kg.paike.saaremaapaike.view.AccommodationFragment;
import ee.kg.paike.saaremaapaike.view.EventsFragment;
import ee.kg.paike.saaremaapaike.view.FoodFragment;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.navbottom)
    BottomNavigationView bottomNavigationView;

    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        openFragment(new EventsFragment(), false);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_events:
                        openFragment(new EventsFragment(), false);
                        break;
                    case R.id.menu_food:
                        openFragment(new FoodFragment(), false);
                        break;
                    case R.id.menu_accommodation:
                        openFragment(new AccommodationFragment(), false);
                        break;

                }
                return true;
            }
        });

    }

    public void openFragment(Fragment fragment, boolean addToBackStack) {
        if (fragmentManager == null) fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_activity_holder, fragment);
        if (addToBackStack) fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    public void showBackArrow(boolean show) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(show);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
