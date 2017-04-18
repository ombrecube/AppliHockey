package prog.teampoule.applitest.Activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.ListFragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import prog.teampoule.applitest.R;
import prog.teampoule.applitest.Utilities.HttpRequestTask_Friend;
import prog.teampoule.applitest.Utilities.Menu;
import prog.teampoule.applitest.classAdapter.AdapteurUser;
import prog.teampoule.applitest.classAdapter.User;

/**
 * Created by Julien on 09/04/2017.
 */

public class activity_friend extends Menu{

    private SectionsPagerAdapter mSectionsPagerAdapter;
    private ViewPager mViewPager;

    private ListFragment Amis = new ListFragment();
    private ListFragment requestedAmis = new ListFragment();
    private ListFragment Search = new ListFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menutabbed);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        if(is_Connected){
            Log.d("Connected","Reussie");
            navigationView.getMenu().findItem(R.id.Menu_itemAmis).setVisible(true);
        }else{
            navigationView.getMenu().findItem(R.id.Menu_itemAmis).setVisible(false);
        }

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        //GetFriend();
    }

    public void GetFriend() {
        SharedPreferences prefs = getSharedPreferences("MYPREF", MODE_PRIVATE);
        int id = prefs.getInt("id_user",-1);
        String myurl = "https://patinou-rest-api-ombrecube.c9users.io/amis/"+ String.valueOf(id);
        HttpRequestTask_Friend http = new HttpRequestTask_Friend();
        http.setURL(myurl);
        http.setContext(getApplicationContext());
        http.setFLAG(1);
       // http.setListView();
        http.execute();
    }



    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public ListFragment getItem(int position) {
            if (position == 0){
                return Amis;
            }else if (position == 1){
                return requestedAmis;
            }else{
                return Search;
            }
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Mes amis";
                case 1:
                    return "Demande amis";
                case 2:
                    return "Chercher des amis";
            }
            return null;
        }
    }

    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        public ListView list;

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            ListView list_all = (ListView) rootView.findViewById(R.id.lv_friend);
            return rootView;
        }
    }
}
