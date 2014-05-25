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
package com.rany.albeg.wein.bottomleftmenu.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;

import com.rany.albeg.wein.bottomleftmenu.R;
import com.rany.albeg.wein.bottomleftmenu.interfaces.OnBottomLeftMenuItemClickHandler;
import com.rany.albeg.wein.bottomleftmenu.interfaces.OnBottomLeftMenuItemClickListener;

public class BottomLeftMenuView extends LinearLayout implements OnClickListener, OnBottomLeftMenuItemClickListener {

	private final static int					_HIGHLIGHT_MENU_ITEM_DURATION	= 500;
	private final static int					_HIGHLIGHT_REPETITIONS			= 4;

	private boolean								mIsOpened;
	private Animation							mOpenAnimation;
	private Animation							mCloseAnimation;
	private Animation							mBlinkAnimation;
	private OnBottomLeftMenuItemClickHandler	mOnCustomMenuItemClickHandler;

	public BottomLeftMenuView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	private void init(Context context) {
		mIsOpened = getVisibility() == View.VISIBLE ? true : false;
		setOrientation(LinearLayout.VERTICAL);

		mOpenAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_up_in);
		mCloseAnimation = AnimationUtils.loadAnimation(context, R.anim.slide_down_out);

		mBlinkAnimation = new AlphaAnimation(1, 0);
		mBlinkAnimation.setDuration(_HIGHLIGHT_MENU_ITEM_DURATION);
		mBlinkAnimation.setInterpolator(new LinearInterpolator());
		mBlinkAnimation.setRepeatCount(_HIGHLIGHT_REPETITIONS);
		mBlinkAnimation.setRepeatMode(Animation.REVERSE);
	}

	public void setOnCustomMenuItemClickHandler(OnBottomLeftMenuItemClickHandler l) {
		mOnCustomMenuItemClickHandler = l;
	}

	public void addMenuItem(BottomLeftMenuItemView v) {
		v.setOnClickListener(this);
		addView(v);
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
		BottomLeftMenuItemView v;

		for (i = 0; i < getChildCount(); ++i) {
			v = (BottomLeftMenuItemView) getChildAt(i);
			if (v.getIdentifier() == identifier) {
				v.startAnimation(mBlinkAnimation);
				break;
			}
		}
	}

	public void onClick(View v) {

		closeMenu();
		BottomLeftMenuItemView cmv = (BottomLeftMenuItemView) v;

		if (mOnCustomMenuItemClickHandler != null)
			onCustomMenuItemClick(cmv);
	}

	@Override
	public void onCustomMenuItemClick(BottomLeftMenuItemView item) {
		if (mOnCustomMenuItemClickHandler != null)
			mOnCustomMenuItemClickHandler.onClick(item);

	}
}
