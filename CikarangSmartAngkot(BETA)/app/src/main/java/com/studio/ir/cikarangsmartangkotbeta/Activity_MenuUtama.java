package com.studio.ir.cikarangsmartangkotbeta;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.studio.ir.cikarangsmartangkotbeta.adapter.NavDrawerListAdapter;
import com.studio.ir.cikarangsmartangkotbeta.model.NavDrawerItem;

import java.util.ArrayList;



public class Activity_MenuUtama extends Activity {



    private DrawerLayout mDrawerLayout;

    private ListView mDrawerList;

    public ActionBarDrawerToggle mDrawerToggle;

    private CharSequence mDrawerTitle;

    private CharSequence mTitle;

    private String[] navMenuTitles;

    private TypedArray navMenuIcons;



    private ArrayList<NavDrawerItem> navDrawerItems;

    private NavDrawerListAdapter adapter;



    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.main_activity);





        mTitle = mDrawerTitle = getTitle();



        navMenuTitles = getResources().getStringArray(R.array.nav_drawer_items);



        navMenuIcons = getResources()

                .obtainTypedArray(R.array.nav_drawer_icons);



        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

        mDrawerList = (ListView) findViewById(R.id.list_slidermenu);







        navDrawerItems = new ArrayList<NavDrawerItem>();
        View header = getLayoutInflater().inflate(R.layout.header, null);

        mDrawerList.addHeaderView(header);

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[1], navMenuIcons.getResourceId(0, -1)));

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[2], navMenuIcons.getResourceId(1, -1)));

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[3], navMenuIcons.getResourceId(2, -1)));

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[4], navMenuIcons.getResourceId(3, -1)));

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[5], navMenuIcons.getResourceId(4, -1)));

        navDrawerItems.add(new NavDrawerItem(navMenuTitles[6], navMenuIcons.getResourceId(5, -1)));








        navMenuIcons.recycle();



        mDrawerList.setOnItemClickListener(new SlideMenuClickListener());



        adapter = new NavDrawerListAdapter(getApplicationContext(),

                navDrawerItems);

        mDrawerList.setAdapter(adapter);



        getActionBar().setDisplayHomeAsUpEnabled(true);

        getActionBar().setHomeButtonEnabled(true);



        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout,

                R.drawable.ic_drawer,

                R.string.app_name,

                R.string.app_name

        ) {

            public void onDrawerClosed(View view) {

                getActionBar().setTitle(mTitle);

                invalidateOptionsMenu();

            }



            public void onDrawerOpened(View drawerView) {

                getActionBar().setTitle(mDrawerTitle);

                invalidateOptionsMenu();

            }

        };

        mDrawerLayout.setDrawerListener(mDrawerToggle);



        if (savedInstanceState == null) {

            displayView(0);

        }

    }

    private void loadingPopup() {
        LayoutInflater inflater = this.getLayoutInflater();
        final View layout = inflater.inflate(R.layout.activity_splash, null);

        final PopupWindow windows = new PopupWindow(layout , 700,1000,true);
        windows.setFocusable(false);
        windows.setTouchable(true);
        windows.setOutsideTouchable(true);
        layout.post(new Runnable() {
            public void run() {
                windows.showAtLocation(layout,Gravity.CENTER, 0, 0);
            }
        });
    }



    private class SlideMenuClickListener implements

            ListView.OnItemClickListener {

        @Override

        public void onItemClick(AdapterView<?> parent, View view, int position,

                                long id) {

            displayView(position);

        }

    }



    @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_utama_draw, menu);

        return true;

    }



    @Override

    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {

            return true;

        }

        switch (item.getItemId()) {

            case R.id.action_settings:
                keluar();
                return true;

            default:

                return super.onOptionsItemSelected(item);

        }

    }


    public void keluar (){
        new AlertDialog.Builder(this).
                setMessage("Apa Kamu Yakin Ingin Keluar?")
                .setCancelable(false)
                .setPositiveButton("Ya",new DialogInterface.OnClickListener(){
                    public void onClick (DialogInterface dialog, int id){
                        Activity_MenuUtama.this.finish();
                    }
                })
                .setNegativeButton("Tidak",null)
                .show();
    }



    @Override

    public boolean onPrepareOptionsMenu(Menu menu) {

        boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);

        menu.findItem(R.id.action_settings).setVisible(!drawerOpen);

        return super.onPrepareOptionsMenu(menu);

    }



    private void displayView(int position) {

        // update the main content by replacing fragments

        Fragment fragment = null;


        switch (position) {
            case 0:

                fragment = new Nav_MenuUtamaFragment();
                break;

            case 1:
                fragment = new Nav_MenuUtamaFragment();
                break;

            case 2:
                fragment = new Nav_Bantuan();
                break;
            case 3:
                fragment = new Nav_Bantuan();
                break;
            case 4:
                startActivity (new Intent("android.intent.action.VIEW", Uri.parse("mailto: starmotionstudio@student.uinsgd.ac.id")));
                break;

            case 5:
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://details?id=com.irwanstudio.cikarangsmartangkotbeta")));
                break;

            case 6:
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("market://search?q=LABKOMIF")));
                break;

            default:

                break;

        }



        if (fragment != null) {

            FragmentManager fragmentManager = getFragmentManager();

            fragmentManager.beginTransaction()

                    .replace(R.id.frame_container, fragment).commit();



            mDrawerList.setItemChecked(position, true);

            mDrawerList.setSelection(position);

            setTitle(navMenuTitles[position]);

            mDrawerLayout.closeDrawer(mDrawerList);

        } else {

            Log.e("MainActivity", "Error in creating fragment");

        }

    }



    @Override

    public void setTitle(CharSequence title) {

        mTitle = title;

        getActionBar().setTitle(mTitle);

    }



    @Override

    protected void onPostCreate(Bundle savedInstanceState) {

        super.onPostCreate(savedInstanceState);

        mDrawerToggle.syncState();

    }
    private static long back_pressed;
    @Override
    public void onBackPressed() {
        // TODO Auto-generated method stub

        if(mDrawerLayout.isDrawerOpen(Gravity.LEFT)){
            mDrawerLayout.closeDrawer(Gravity.LEFT);
        }else{

            if (back_pressed + 2000 > System.currentTimeMillis()) super.onBackPressed();
            else Toast.makeText(getBaseContext(), "Tekan Lagi untuk Keluar!", Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();
        }

    }
   /* private static long back_pressed;

    @Override
    public void onBackPressed()
    {
        if (back_pressed + 2000 > System.currentTimeMillis()) super.onBackPressed();
        else Toast.makeText(getBaseContext(), "Tekan Lagi untuk Keluar!", Toast.LENGTH_SHORT).show();
        back_pressed = System.currentTimeMillis();
    }
    */
// see http://androidsnippets.com/double-back-press-to-exit

    @Override

    public void onConfigurationChanged(Configuration newConfig) {

        super.onConfigurationChanged(newConfig);

        mDrawerToggle.onConfigurationChanged(newConfig);

    }

}
