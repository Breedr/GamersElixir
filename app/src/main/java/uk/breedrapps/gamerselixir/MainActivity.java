package uk.breedrapps.gamerselixir;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import uk.breedrapps.gamerselixir.common.Constants;
import uk.breedrapps.gamerselixir.common.Utils;
import uk.breedrapps.gamerselixir.fragments.NavigationDrawerFragment;
import uk.breedrapps.gamerselixir.fragments.NetworkFeedFragment;
import uk.breedrapps.gamerselixir.fragments.StreamersFragment;


public class MainActivity extends AppCompatActivity implements NavigationDrawerFragment.NavigationDrawerCallbacks{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ParseAnalytics.trackAppOpenedInBackground(getIntent());

        setContentView(R.layout.activity_main);
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


        DrawerLayout mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        android.support.v7.app.ActionBarDrawerToggle mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(
                this, mDrawerLayout, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close
        );
        mDrawerLayout.setDrawerListener(mDrawerToggle);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
        mDrawerToggle.syncState();

        NavigationDrawerFragment mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout), mToolbar);

        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new NetworkFeedFragment()).commit();

    }
    

    @Override
    public void onNavigationDrawerItemSelected(int position) {
        Fragment contentFragment;
        switch(position){
            case 0:
                contentFragment = new NetworkFeedFragment();
                if(getSupportActionBar() != null)
                    getSupportActionBar().setTitle(R.string.app_name);
                Utils.trackView(Constants.VIEW_NETWORK_FEED);
                break;
            case 1:
//                contentFragment = new NetworkFeedFragment();
//                if(getSupportActionBar() != null)
//                    getSupportActionBar().setTitle(R.string.app_name);
//                Utils.trackView(Constants.VIEW_NETWORK_FEED);
                Snackbar.make(findViewById(android.R.id.content), "STUB.", Snackbar.LENGTH_LONG).show();
                return;
//                break;
            case 2:
                contentFragment = new StreamersFragment();
                if(getSupportActionBar() != null)
                    getSupportActionBar().setTitle(R.string.menu_streamers);
                Utils.trackView(Constants.VIEW_STREAMERS);
                break;
            case 3:
                Intent intent = Utils.openURLIntent(Constants.APP_FORUMS_URL);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                Utils.trackView(Constants.VIEW_FORUMS);
                return;
            default:
                return;
        }
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, contentFragment).commit();
    }

}
