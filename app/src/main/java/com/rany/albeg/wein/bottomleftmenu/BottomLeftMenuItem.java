/* Copyright 2015 Rany Albeg Wein
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*    http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
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

		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		setOrientation(LinearLayout.HORIZONTAL);
		setLayoutParams(layoutParams);

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

		void onClick(BottomLeftMenuItem item);
	}
}
