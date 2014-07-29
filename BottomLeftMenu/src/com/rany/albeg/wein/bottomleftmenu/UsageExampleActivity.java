/* Copyright (C) 2014 Rany Albeg Wein - rany.albeg@gmail.com
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */
package com.rany.albeg.wein.bottomleftmenu;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.Toast;

import com.rany.albeg.wein.bottomleftmenu.interfaces.OnBottomLeftMenuItemClickListener;
import com.rany.albeg.wein.bottomleftmenu.views.BottomLeftMenuItem;
import com.rany.albeg.wein.bottomleftmenu.views.BottomLeftMenu;

public class UsageExampleActivity extends Activity implements OnBottomLeftMenuItemClickListener {

	private static final int	_MENU_ID_UPLOAD		= 0;
	private static final int	_MENU_ID_SHARE		= 1;
	private static final int	_MENU_ID_SEND		= 2;
	private static final int	_MENU_ID_RATE		= 3;
	private static final int	_MENU_ID_ABOUT		= 4;
	private static final int	_MENU_ID_SEARCH		= 5;
	private static final int	_MENU_ID_SETTINGS	= 6;

	private BottomLeftMenu		mMenu;
	private CheckBox			mCheckBoxAnim;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_usage_example);

		findViewsByid();
		/*
		 * Initialize menu.
		 */
		mMenu.setOnBottomLeftMenuItemClickListener(this);
		/*
		 * Populate the menu with views.
		 */
		mMenu.addMenuItem(this, R.drawable.ic_action_settings, R.string.settings, _MENU_ID_SETTINGS);
		mMenu.addMenuItem(this, R.drawable.ic_action_about, R.string.about, _MENU_ID_ABOUT);
		mMenu.addMenuItem(this, R.drawable.ic_rating_good, R.string.rate, _MENU_ID_RATE);
		mMenu.addMenuItem(this, R.drawable.ic_action_search, R.string.search, _MENU_ID_SEARCH);
		mMenu.addMenuItem(this, R.drawable.ic_social_share, R.string.share, _MENU_ID_SHARE);
		mMenu.addMenuItem(this, R.drawable.ic_collections_cloud, R.string.upload, _MENU_ID_UPLOAD);
		mMenu.addMenuItem(this, R.drawable.ic_social_send_now, R.string.send, _MENU_ID_SEND);
	}

	private void findViewsByid() {
		mCheckBoxAnim = (CheckBox) findViewById(R.id.cb_anim);
		mMenu = (BottomLeftMenu) findViewById(R.id.bottom_left_menu);
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_MENU) {

			showHideSettings();
			return true;
		}
		return super.onKeyDown(keyCode, event);
	}

	@Override
	public void onBackPressed() {

		if (mMenu.isOpened()) {
			mMenu.closeMenu();
		} else {
			super.onBackPressed();
		}
	}

	private void showHideSettings() {
		if (mMenu.isOpened())
			mMenu.closeMenu();
		else {
			mMenu.openMenu();
			/*
			 * Blink animation on a random item.
			 */
			if (mCheckBoxAnim.isChecked())
				mMenu.blink(new Random().nextInt(6) + 1);
		}
	}

	/*
	 * Handle BottomLeftMenu click events.(non-Javadoc)
	 * 
	 * @see com.rany.albeg.wein.bottomleftmenu.interfaces.
	 * OnBottomLeftMenuItemClickListener
	 * #onClick(com.rany.albeg.wein.bottomleftmenu.views.BottomLeftMenuItem)
	 */
	@Override
	public void onClick(BottomLeftMenuItem item) {

		/*
		 * getIdentifier(), not getId()
		 */
		int id = item.getIdentifier();

		if (id == _MENU_ID_UPLOAD)
			Toast.makeText(this, getString(R.string.upload), Toast.LENGTH_SHORT).show();
		else if (id == _MENU_ID_SHARE)
			Toast.makeText(this, getString(R.string.share), Toast.LENGTH_SHORT).show();
		else if (id == _MENU_ID_SEND)
			Toast.makeText(this, getString(R.string.send), Toast.LENGTH_SHORT).show();
		else if (id == _MENU_ID_RATE)
			Toast.makeText(this, getString(R.string.rate), Toast.LENGTH_SHORT).show();
		else if (id == _MENU_ID_ABOUT)
			Toast.makeText(this, getString(R.string.about), Toast.LENGTH_SHORT).show();
		else if (id == _MENU_ID_SEARCH)
			Toast.makeText(this, getString(R.string.search), Toast.LENGTH_SHORT).show();
		else if (id == _MENU_ID_SETTINGS)
			Toast.makeText(this, getString(R.string.settings), Toast.LENGTH_SHORT).show();
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
		// Handle presses on the action bar items
		switch (item.getItemId()) {

			case R.id.action_settings:
				showHideSettings();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}
}
