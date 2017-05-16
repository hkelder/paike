package ee.kg.paike.saaremaapaike;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.github.ksoichiro.android.observablescrollview.ObservableScrollViewCallbacks;
import com.github.ksoichiro.android.observablescrollview.ScrollState;
import com.github.ksoichiro.android.observablescrollview.ScrollUtils;
import com.nineoldandroids.view.ViewHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import ee.kg.paike.saaremaapaike.pojo.ParallaxImageData;
import ee.kg.paike.saaremaapaike.view.AccommodationFragment;
import ee.kg.paike.saaremaapaike.view.EventsFragment;
import ee.kg.paike.saaremaapaike.view.FoodFragment;

public class MainActivity extends AppCompatActivity implements ObservableScrollViewCallbacks{
    @BindView(R.id.navbottom)
    BottomNavigationView bottomNavigationView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private FragmentManager fragmentManager;

    private ParallaxImageData parallaxImageData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setTitle("");

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

    public Toolbar getToolbar() {
        return toolbar;
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

    public void setScrollViewCallbacks(ParallaxImageData parallaxImageData) {
        parallaxImageData.mScrollView.setScrollViewCallbacks(this);

        this.parallaxImageData = parallaxImageData;
    }

    @Override
    public void onScrollChanged(int scrollY, boolean firstScroll, boolean dragging) {
        View imageContainer = parallaxImageData.mImageContainer;
        View logo = parallaxImageData.mLogoView;

        imageContainer.bringToFront();

        if (scrollY > 250) {
            ViewHelper.setTranslationY(imageContainer, scrollY - 250);
        } else {
            ViewHelper.setTranslationY(imageContainer, 0);
        }

        ViewHelper.setTranslationY(logo, Math.min(scrollY, 250));
    }

    @Override
    public void onDownMotionEvent() {

    }

    @Override
    public void onUpOrCancelMotionEvent(ScrollState scrollState) {

    }
}
