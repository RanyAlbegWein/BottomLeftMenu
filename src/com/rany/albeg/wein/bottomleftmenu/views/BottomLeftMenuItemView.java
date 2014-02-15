package com.rany.albeg.wein.bottomleftmenu.views;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rany.albeg.wein.bottomleftmenu.R;

public class BottomLeftMenuItemView extends LinearLayout {

	private TextView _text;
	private ImageView _image;
	private int _identifier;
	private Context _cntx;

	private static final int DEFAULT_TEXT_SIZE = 22;
	private static final int DEFAULT_TEXT_PADDING = 5;
	private static final int DEFAULT_TEXT_COLOR = Color.BLACK;
	private static final int DEFAULT_TEXT_RIGHT_MARGIN = 20;
	private static final int DEFAULT_ICON_PADDING = 5;

	public BottomLeftMenuItemView(Context context, int iconResource,
			int textResouce, int identifier) {
		super(context);

		_cntx = context;

		LinearLayout.LayoutParams thisLayoutParams = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		setOrientation(LinearLayout.HORIZONTAL);
		setBackgroundResource(R.drawable.custom_menu_item_bg_states);

		setLayoutParams(thisLayoutParams);

		_identifier = identifier;
		initChildViews(iconResource, textResouce);

		addView(_image);
		addView(_text);
	}

	public int getIdentifier() {
		return _identifier;
	}

	private void initChildViews(int iconResource, int textResource) {
		_image = new ImageView(_cntx);
		_text = new TextView(_cntx);

		LinearLayout.LayoutParams imageLayoutParams = new LinearLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		imageLayoutParams.gravity = Gravity.CENTER;

		_image.setImageResource(iconResource);
		_image.setPadding(DEFAULT_ICON_PADDING, DEFAULT_ICON_PADDING,
				DEFAULT_ICON_PADDING, DEFAULT_ICON_PADDING);
		_image.setLayoutParams(imageLayoutParams);

		LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		textLayoutParams.gravity = Gravity.CENTER;
		textLayoutParams.setMargins(0, 0, DEFAULT_TEXT_RIGHT_MARGIN, 0);

		_text.setLayoutParams(textLayoutParams);
		_text.setGravity(Gravity.START);
		_text.setPadding(DEFAULT_TEXT_PADDING, DEFAULT_TEXT_PADDING,
				DEFAULT_TEXT_PADDING, DEFAULT_TEXT_PADDING);
		_text.setText(textResource);
		_text.setTextSize(DEFAULT_TEXT_SIZE);
		_text.setTextColor(DEFAULT_TEXT_COLOR);
	}

}
