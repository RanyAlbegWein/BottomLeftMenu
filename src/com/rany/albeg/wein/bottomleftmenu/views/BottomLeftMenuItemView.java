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
import android.graphics.Color;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rany.albeg.wein.bottomleftmenu.R;

public class BottomLeftMenuItemView extends LinearLayout {

	private TextView			mText;
	private ImageView			mImage;
	private int					mIdentifier;
	private Context				mContext;

	private static final int	_DEFAULT_TEXT_SIZE			= 22;
	private static final int	_DEFAULT_TEXT_PADDING		= 5;
	private static final int	_DEFAULT_TEXT_COLOR			= Color.BLACK;
	private static final int	_DEFAULT_TEXT_RIGHT_MARGIN	= 20;
	private static final int	_DEFAULT_ICON_PADDING		= 5;

	public BottomLeftMenuItemView(Context context, int iconResource, int textResouce, int identifier) {
		super(context);

		mContext = context;

		LinearLayout.LayoutParams thisLayoutParams = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		setOrientation(LinearLayout.HORIZONTAL);
		setBackgroundResource(R.drawable.custom_menu_item_bg_states);

		setLayoutParams(thisLayoutParams);

		mIdentifier = identifier;
		initChildViews(iconResource, textResouce);

		addView(mImage);
		addView(mText);
	}

	public int getIdentifier() {
		return mIdentifier;
	}

	private void initChildViews(int iconResource, int textResouce) {
		mImage = new ImageView(mContext);
		mText = new TextView(mContext);

		LinearLayout.LayoutParams imageLayoutParams = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		imageLayoutParams.gravity = Gravity.CENTER;

		mImage.setImageResource(iconResource);
		mImage.setPadding(_DEFAULT_ICON_PADDING, _DEFAULT_ICON_PADDING, _DEFAULT_ICON_PADDING, _DEFAULT_ICON_PADDING);
		mImage.setLayoutParams(imageLayoutParams);

		LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		textLayoutParams.gravity = Gravity.CENTER;
		textLayoutParams.setMargins(0, 0, _DEFAULT_TEXT_RIGHT_MARGIN, 0);

		mText.setLayoutParams(textLayoutParams);
		mText.setGravity(Gravity.LEFT);
		mText.setPadding(_DEFAULT_TEXT_PADDING, _DEFAULT_TEXT_PADDING, _DEFAULT_TEXT_PADDING, _DEFAULT_TEXT_PADDING);
		mText.setText(textResouce);
		mText.setTextSize(_DEFAULT_TEXT_SIZE);
		mText.setTextColor(_DEFAULT_TEXT_COLOR);
	}

}
