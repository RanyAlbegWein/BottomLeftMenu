package com.rany.wein.albeg.bottomleftmenudemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.rany.albeg.wein.bottomleftmenu.BottomLeftMenu;
import com.rany.albeg.wein.bottomleftmenu.BottomLeftMenuItem;


public class MainActivity extends ActionBarActivity implements BottomLeftMenuItem.OnBottomLeftMenuItemClickListener {

    public static final int MENU_ID_SETTINGS = 0;
    public static final int MENU_ID_ABOUT = 1;
    public static final int MENU_ID_RATE = 2;
    public static final int MENU_ID_SEARCH = 3;
    public static final int MENU_ID_SHARE = 4;
    public static final int MENU_ID_UPLOAD = 5;
    public static final int MENU_ID_SEND = 6;

    private BottomLeftMenu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMenu = (BottomLeftMenu) findViewById(R.id.bottom_left_menu);
        /*
         * Populate the menu with views.
         */
        mMenu.addMenuItem(R.drawable.ic_action_settings, R.string.settings, MENU_ID_SETTINGS);
        mMenu.addMenuItem(R.drawable.ic_action_about, R.string.about, MENU_ID_ABOUT);
        mMenu.addMenuItem(R.drawable.ic_rating_good, R.string.rate, MENU_ID_RATE);
        mMenu.addMenuItem(R.drawable.ic_action_search, R.string.search, MENU_ID_SEARCH);
        mMenu.addMenuItem(R.drawable.ic_social_share, R.string.share, MENU_ID_SHARE);
        mMenu.addMenuItem(R.drawable.ic_collections_cloud, R.string.upload, MENU_ID_UPLOAD);
        mMenu.addMenuItem(R.drawable.ic_social_send_now, R.string.send, MENU_ID_SEND);
        /*
         * Set a listener for click events.
         */
        mMenu.setOnBottomLeftMenuItemClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            openOrCloseMenu(null);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void openOrCloseMenu(View v) {
        if (mMenu.isOpened()) {
            mMenu.closeMenu();
            ((Button) (findViewById(R.id.button))).setText(getString(R.string.open));
        } else {
            mMenu.openMenu();
            ((Button) (findViewById(R.id.button))).setText(R.string.close);

        }
    }

    @Override
    public void onBackPressed() {

        if (mMenu.isOpened()) {
            mMenu.closeMenu();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onClick(BottomLeftMenuItem item) {
        String itemTextString = item.getTextView().getText().toString();
        Toast.makeText(this, itemTextString, Toast.LENGTH_SHORT).show();
        ((Button) (findViewById(R.id.button))).setText(getString(R.string.open));
    }
}
