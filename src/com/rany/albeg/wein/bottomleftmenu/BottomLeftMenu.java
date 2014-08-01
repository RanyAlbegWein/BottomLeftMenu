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

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.rany.albeg.wein.bottomleftmenu.BottomLeftMenuItem.OnBottomLeftMenuItemClickListener;

public class BottomLeftMenu extends ScrollView implements OnClickListener {

	private boolean								mIsOpened;
	private OpenCloseMenuAnimation				mOpenCloseAnimation;
	private LinearLayout						mViewsContainer;
	private OnBottomLeftMenuItemClickListener	mOnCustomMenuItemClickListener;
	private int									mTextColorRes;
	private float								mTextSize;
	private int									mNormalStateColor;
	private int									mPressedStateColor;
	private boolean								mShowDivider;
	private int									mDividerHeight;
	private int									mDividerColor;
	private OPEN_CLOSE_ANIMATION				mOpenCloseAnimationType;
	private Context								mContext;

	public BottomLeftMenu(Context context, AttributeSet attrs) {
		super(context, attrs);
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BottomLeftMenu);
		initAttrs(ta);
		init(context);
	}

	public BottomLeftMenu(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.BottomLeftMenu, defStyle, 0);
		initAttrs(ta);
		init(context);
	}

	private void initAttrs(TypedArray a) {

		Resources r = getResources();

		mTextColorRes = a.getColor(R.styleable.BottomLeftMenu_itemTextColor, Color.BLACK);
		mTextSize = a.getDimension(R.styleable.BottomLeftMenu_itemTextSize, r.getDimension(R.dimen.default_item_text_size));
		mPressedStateColor = a.getColor(R.styleable.BottomLeftMenu_itemPressedStateColor,
				r.getColor(R.color.default_item_pressed_state_color));
		mNormalStateColor = a.getColor(R.styleable.BottomLeftMenu_itemNormalStateColor, r.getColor(android.R.color.transparent));
		mShowDivider = a.getBoolean(R.styleable.BottomLeftMenu_showDivider, true);
		mDividerHeight = (int) a.getDimension(R.styleable.BottomLeftMenu_dividerHeight,
				r.getDimension(R.dimen.default_divider_height));
		mDividerColor = a.getColor(R.styleable.BottomLeftMenu_dividerColor, r.getColor(R.color.default_divider_color));
		mOpenCloseAnimationType = OPEN_CLOSE_ANIMATION.values()[a.getInt(R.styleable.BottomLeftMenu_openingDirection, 0)];

		a.recycle();
	}

	private void init(Context context) {

		mContext = context;
		mViewsContainer = new LinearLayout(context);
		mViewsContainer.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
		mViewsContainer.setOrientation(LinearLayout.VERTICAL);

		setBackgroundResource(R.drawable.menu_bg);
		setVisibility(View.GONE);

		mIsOpened = false;

		setOpenCloseAnimation(OpenCloseMenuAnimationFactory.getAnimation(context, mOpenCloseAnimationType));

		if (mShowDivider)
			setDivider();

		addView(mViewsContainer);
	}

	/**
	 * Sets the open and close animations for this menu.
	 * 
	 * @param openCloseAnimation
	 *            - a OpenCloseMenuAnimation implementation.
	 */
	public void setOpenCloseAnimation(OpenCloseMenuAnimation openCloseAnimation) {

		mOpenCloseAnimation = openCloseAnimation;
	}

	private void setDivider() {
		final ShapeDrawable divider = new ShapeDrawable();

		divider.setIntrinsicHeight(mDividerHeight);
		divider.getPaint().setColor(mDividerColor);
		mViewsContainer.setDividerDrawable(divider);
		mViewsContainer.setShowDividers(LinearLayout.SHOW_DIVIDER_MIDDLE);
	}

	public void setOnBottomLeftMenuItemClickListener(OnBottomLeftMenuItemClickListener l) {
		mOnCustomMenuItemClickListener = l;
	}

	private void addMenuItem(BottomLeftMenuItem item) {
		setSelector(item);
		item.getTextView().setTextColor(mTextColorRes);
		item.getTextView().setTextSize(mTextSize);
		item.setOnClickListener(this);

		mViewsContainer.addView(item);
	}

	/**
	 * Add an item to the menu.
	 * 
	 * @param iconResource
	 *            - Resource id for the item's icon.
	 * @param textResouce
	 *            - Resource id for the item's text.
	 * @param identifier
	 *            - A unique identifier, to identify this item for handling
	 *            click events.
	 */
	public void addMenuItem(int iconResource, int textResouce, int identifier) {
		addMenuItem(new BottomLeftMenuItem(mContext, iconResource, textResouce, identifier));
	}

	@SuppressLint("NewApi")
	private void setSelector(BottomLeftMenuItem item) {

		ColorDrawable normalDrawable = new ColorDrawable(mNormalStateColor);
		ColorDrawable pressedDrawable = new ColorDrawable(mPressedStateColor);

		final StateListDrawable selector = new StateListDrawable();
		selector.addState(new int[] { android.R.attr.state_pressed }, pressedDrawable);
		selector.addState(new int[] {}, normalDrawable);

		int sdk = android.os.Build.VERSION.SDK_INT;
		if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
			item.setBackgroundDrawable(selector);
		} else {
			item.setBackground(selector);
		}
	}

	public boolean isOpened() {
		return mIsOpened;
	}

	public void openMenu() {
		if (!mIsOpened) {
			setVisibility(View.VISIBLE);
			startAnimation(mOpenCloseAnimation.open());
			mIsOpened = true;
		}
	}

	public void closeMenu() {
		if (mIsOpened) {
			setVisibility(View.GONE);
			startAnimation(mOpenCloseAnimation.close());
			mIsOpened = false;
		}
	}

	public BottomLeftMenuItem getMenuItemAt(int index) {

		return (BottomLeftMenuItem) mViewsContainer.getChildAt(index);
	}

	public void onClick(View v) {

		closeMenu();
		BottomLeftMenuItem item = (BottomLeftMenuItem) v;

		if (mOnCustomMenuItemClickListener != null)
			mOnCustomMenuItemClickListener.onClick(item);
	}

	public enum OPEN_CLOSE_ANIMATION {
		BOTTOM_TOP, LEFT_RIGHT, FADE_IN
	}
}
