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

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.rany.albeg.wein.bottomleftmenu.BottomLeftMenuItem.OnBottomLeftMenuItemClickListener;

public class BottomLeftMenu extends ScrollView implements OnClickListener {

	private final static int					_HIGHLIGHT_MENU_ITEM_DURATION	= 500;
	private final static int					_HIGHLIGHT_REPETITIONS			= 4;

	private boolean								mIsOpened;
	private Animation							mOpenAnimation;
	private Animation							mCloseAnimation;
	private Animation							mBlinkAnimation;
	private LinearLayout						mViewsContainer;
	private OnBottomLeftMenuItemClickListener	mOnCustomMenuItemClickListener;

	public BottomLeftMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {

		mViewsContainer = new LinearLayout(context);
		mViewsContainer.setOrientation(LinearLayout.VERTICAL);
		mViewsContainer.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT));

		setBackgroundResource(R.drawable.menu_bg);
		setVisibility(View.GONE);

		mIsOpened = false;
		mOpenAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_up_in);
		mCloseAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_down_out);
		mBlinkAnimation = new AlphaAnimation(1, 0);
		mBlinkAnimation.setDuration(_HIGHLIGHT_MENU_ITEM_DURATION);
		mBlinkAnimation.setInterpolator(new LinearInterpolator());
		mBlinkAnimation.setRepeatCount(_HIGHLIGHT_REPETITIONS);
		mBlinkAnimation.setRepeatMode(Animation.REVERSE);

		addView(mViewsContainer);
	}

	public void setOnBottomLeftMenuItemClickListener(OnBottomLeftMenuItemClickListener l) {
		mOnCustomMenuItemClickListener = l;
	}

	private void addMenuItem(BottomLeftMenuItem item) {
		item.setOnClickListener(this);
		mViewsContainer.addView(item);
	}

	public void addMenuItem(Context context, int iconResource, int textResouce, int identifier) {
		addMenuItem(new BottomLeftMenuItem(context, iconResource, textResouce, identifier));
	}

	public boolean isOpened() {
		return mIsOpened;
	}

	public void openMenu() {
		if (!mIsOpened) {
			setVisibility(View.VISIBLE);
			startAnimation(mOpenAnimation);
			mIsOpened = true;
		}
	}

	public void closeMenu() {
		if (mIsOpened) {
			setVisibility(View.GONE);
			startAnimation(mCloseAnimation);
			mIsOpened = false;
		}

		if (!mBlinkAnimation.hasEnded()) {
			mBlinkAnimation.cancel();
			mBlinkAnimation.reset();
		}
	}

	public void blink(int identifier) {

		if (!mIsOpened)
			openMenu();

		int i;
		BottomLeftMenuItem item;

		for (i = 0; i < getChildCount(); ++i) {
			item = (BottomLeftMenuItem) getChildAt(i);
			if (item.getIdentifier() == identifier) {
				item.startAnimation(mBlinkAnimation);
				break;
			}
		}
	}

	public void onClick(View v) {

		closeMenu();
		BottomLeftMenuItem item = (BottomLeftMenuItem) v;

		if (mOnCustomMenuItemClickListener != null)
			mOnCustomMenuItemClickListener.onClick(item);
	}
}
