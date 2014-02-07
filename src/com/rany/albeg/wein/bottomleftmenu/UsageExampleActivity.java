package com.rany.albeg.wein.bottomleftmenu;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.widget.CheckBox;
import android.widget.Toast;

import com.rany.albeg.wein.bottomleftmenu.interfaces.OnBottomLeftMenuItemClickHandler;
import com.rany.albeg.wein.bottomleftmenu.views.BottomLeftMenuItemView;
import com.rany.albeg.wein.bottomleftmenu.views.BottomLeftMenuView;

public class UsageExampleActivity extends Activity implements OnBottomLeftMenuItemClickHandler {

	private static final int	_MENU_ID_UPLOAD		= 0;
	private static final int	_MENU_ID_SHARE		= 1;
	private static final int	_MENU_ID_SEND		= 2;
	private static final int	_MENU_ID_RATE		= 3;
	private static final int	_MENU_ID_ABOUT		= 4;
	private static final int	_MENU_ID_SEARCH		= 5;
	private static final int	_MENU_ID_SETTINGS	= 6;

	private BottomLeftMenuView	mMenu;
	private CheckBox			mCheckBoxAnim;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_usage_example);

		mCheckBoxAnim = (CheckBox) findViewById(R.id.cb_anim);

		mMenu = (BottomLeftMenuView) findViewById(R.id.bottom_left_menu);
		/*
		 * Initialize menu.
		 */
		mMenu.setOnCustomMenuItemClickHandler(this);
		/*
		 * Populate the menu with views.
		 */
		mMenu.addMenuItem(new BottomLeftMenuItemView(this, R.drawable.ic_action_settings, R.string.settings, _MENU_ID_SETTINGS));
		mMenu.addMenuItem(new BottomLeftMenuItemView(this, R.drawable.ic_action_about, R.string.about, _MENU_ID_ABOUT));
		mMenu.addMenuItem(new BottomLeftMenuItemView(this, R.drawable.ic_rating_good, R.string.rate, _MENU_ID_RATE));
		mMenu.addMenuItem(new BottomLeftMenuItemView(this, R.drawable.ic_action_search, R.string.search, _MENU_ID_SEARCH));
		mMenu.addMenuItem(new BottomLeftMenuItemView(this, R.drawable.ic_social_share, R.string.share, _MENU_ID_SHARE));
		mMenu.addMenuItem(new BottomLeftMenuItemView(this, R.drawable.ic_collections_cloud, R.string.upload, _MENU_ID_UPLOAD));
		mMenu.addMenuItem(new BottomLeftMenuItemView(this, R.drawable.ic_social_send_now, R.string.send, _MENU_ID_SEND));

	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {

		if (keyCode == KeyEvent.KEYCODE_MENU) {

			if (mMenu.isOpened())
				mMenu.closeMenu();
			else {
				mMenu.openMenu();
				/*
				 * Blink animation on a random item.
				 */
				if (mCheckBoxAnim.isChecked())
					mMenu.blinkAnimationMenuItem(new Random().nextInt(6) + 1);
			}
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

	@Override
	public void onClick(BottomLeftMenuItemView item) {

		// getIdentifier(), not getId().
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
}
