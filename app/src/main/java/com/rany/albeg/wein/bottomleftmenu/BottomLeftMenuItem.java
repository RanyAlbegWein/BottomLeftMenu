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
import android.content.res.Resources;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BottomLeftMenuItem extends LinearLayout {

	private TextView	mText;
	private ImageView	mImage;
	private int			mIdentifier;
	private Context		mContext;

	public BottomLeftMenuItem(Context context, int iconResource, int textResouce, int identifier) {
		super(context);

		mContext = context;
		mIdentifier = identifier;

		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		setOrientation(LinearLayout.HORIZONTAL);
		setLayoutParams(lp);

		initChildViews(iconResource, textResouce);
		addViews();
	}

	private void addViews() {
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

		Resources res = getResources();
		int defIconPadding = (int) res.getDimension(R.dimen.default_item_icon_padding);
		int defTextRightMargin = (int) res.getDimension(R.dimen.default_text_right_margin);
		int defTextPadding = (int) res.getDimension(R.dimen.default_text_padding);

		mImage.setImageResource(iconResource);
		mImage.setPadding(defIconPadding, defIconPadding, defIconPadding, defIconPadding);
		mImage.setLayoutParams(imageLayoutParams);

		LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		textLayoutParams.gravity = Gravity.CENTER;
		textLayoutParams.setMargins(0, 0, defTextRightMargin, 0);

		mText.setLayoutParams(textLayoutParams);
		mText.setPadding(defTextPadding, defTextPadding, defTextPadding, defTextPadding);
		mText.setText(textResouce);
	}

	public TextView getTextView() {
		return mText;
	}

	public ImageView getImageView() {
		return mImage;
	}

	public interface OnBottomLeftMenuItemClickListener {

		public void onClick(BottomLeftMenuItem item);
	}
}
