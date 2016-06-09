package ar.gob.demo.ui.activities;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.astuetz.PagerSlidingTabStrip;

import ar.gob.demo.R;
import ar.gob.demo.helpers.Constants;
import ar.gob.demo.helpers.IntentHelper;
import ar.gob.demo.helpers.Permission;
import ar.gob.demo.ui.adapters.PagerAdapterFragment;

public class HomeActivity extends BaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    private Toolbar toolbar;
    private ViewPager viewPager;
    private PagerSlidingTabStrip tabsStrip;
    private DrawerLayout drawer;
    private ActionBarDrawerToggle toggle;
    private NavigationView navigationView;
    private int lastSectionIndexViewed = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        setContent();
    }

    public void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
    }

    public void setContent() {
        setSupportActionBar(toolbar);
        viewPager.setAdapter(new PagerAdapterFragment(getSupportFragmentManager()));
        tabsStrip.setViewPager(viewPager);
        setColorsTab();
        toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    private void setColorsTab() {
        tabsStrip.setDividerColor(getResources().getColor(R.color.transparent));
        //underline color
        tabsStrip.setUnderlineColor(getResources().getColor(R.color.transparent));
        //Divider color
        tabsStrip.setIndicatorColor(getResources().getColor(R.color.colorSecondary));
        //Text color
        tabsStrip.setTextColor(getResources().getColor(R.color.colorWhite));
        //Background color
        tabsStrip.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_section1:
                Toast.makeText(activity, "Ir section 1", Toast.LENGTH_SHORT).show();
                lastSectionIndexViewed = 0;
                break;

            case R.id.nav_section2:
                Toast.makeText(activity, "Ir section 2", Toast.LENGTH_SHORT).show();
                lastSectionIndexViewed = 1;
                break;

            case R.id.nav_section3:
                Toast.makeText(activity, "Ir section 3", Toast.LENGTH_SHORT).show();
                lastSectionIndexViewed = 2;
                break;

            case R.id.nav_about:
                IntentHelper.goToAboutActivity(activity);
                break;

            case R.id.nav_terms:
                IntentHelper.shareURL(activity, Constants.TERMS);
                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void changeFont(int nameText, String nameFont) {
        TextView txt = (TextView) findViewById(nameText);
        Typeface font = Typeface.createFromAsset(getAssets(), nameFont);
        txt.setTypeface(font);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    protected void onStart() {
        super.onStart();
        navigationView.getMenu().getItem(lastSectionIndexViewed).setChecked(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_info)
            IntentHelper.goToAboutActivity(activity);
        return super.onOptionsItemSelected(item);
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        Permission permissionSendSms = Permission.SEND_SMS;
        if (requestCode == permissionSendSms.getRequestCode()) {
            if (permissionHelper.handlePermission(permissionSendSms)) {
                IntentHelper.goToSmsSend(this, "", "");
                Toast.makeText(this, permissionSendSms.getMessagePermission(), Toast.LENGTH_LONG).show();
            }
        }

    }
}



