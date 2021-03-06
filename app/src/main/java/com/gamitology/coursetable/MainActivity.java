package com.gamitology.coursetable;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.gamitology.fragments.AddEventFragment;
import com.gamitology.fragments.CourseListFragment;
import com.gamitology.fragments.MainCom;
import com.gamitology.fragments.MainFragment;

import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainCom {

    private MainFragment mainFragment;
    private List<Fragment> containerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // Assign Main Fragment
        this.mainFragment = new MainFragment();

        // Start Default fragment
        replaceFragmentInContainer(mainFragment);

    }

    @Override
    protected void onDestroy() {
        Log.d("Destroy ======= ", "Destroy");
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
//        if (drawer.isDrawerOpen(GravityCompat.START)) {
//            drawer.closeDrawer(GravityCompat.START);
//        } else {
//            super.onBackPressed();
//        }

        int backStack = getSupportFragmentManager().getBackStackEntryCount();
        Log.d("BACK PRESSED : ", "Back Stach  = " + backStack);

        if(backStack > 0){
            getSupportFragmentManager().popBackStack();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {

            // Get current table page index
            SharedPreferences sharedPreferences = this.getPreferences(Context.MODE_PRIVATE);
            int currentDayIndex = sharedPreferences.getInt(AppConfig.CURRENT_TABLE_INDEX, Context.MODE_PRIVATE);

            this.mainFragment = new MainFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("dayIndex", currentDayIndex);
            this.mainFragment.setArguments(bundle);
            replaceFragmentInContainer(this.mainFragment);
        } else if (id == R.id.nav_courses) {
            CourseListFragment courseListFragment = new CourseListFragment();
            replaceFragmentInContainer(courseListFragment);
        } else if (id == R.id.nav_add_event) {
            AddEventFragment addEventFragment = new AddEventFragment();
            replaceFragment(addEventFragment);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void replaceFragmentInContainer(Fragment fragment){
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.addToBackStack(null);
        ft.commit();
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        ft.addToBackStack(null);
        ft.commit();
        Toast.makeText(this, "TEST " + R.id.fragment_container, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void replaceFragment(Fragment fragment, boolean addToBackStack) {
        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, fragment);
        if(addToBackStack){
            ft.addToBackStack(null);
        }
        ft.commit();
        Toast.makeText(this, "TEST " + R.id.fragment_container, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void refreshFragment(Fragment fragment) {

        FragmentTransaction ft = this.getSupportFragmentManager().beginTransaction();
        ft.detach(fragment);
        ft.attach(fragment);
        ft.commit();

    }
}
