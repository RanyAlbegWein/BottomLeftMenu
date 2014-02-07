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

public class BottomLeftMenuView extends LinearLayout implements OnClickListener,
		OnBottomLeftMenuItemClickListener {

	private boolean _isOpened;
	private Animation _animOpen;
	private Animation _animClose;
	private Animation _animBlink;
	private OnBottomLeftMenuItemClickHandler _onCustomMenuItemClickHandler;
	private final static int _HIGHLIGHT_MENU_ITEM_DURATION;
	private final static int _HIGHLIGHT_REPETITIONS;

	static {
		_HIGHLIGHT_MENU_ITEM_DURATION = 500;
		_HIGHLIGHT_REPETITIONS = 4;
	}

	public BottomLeftMenuView(Context context, AttributeSet attrs) {
		super(context, attrs);

		_isOpened = getVisibility() == View.VISIBLE ? true : false;
		setOrientation(LinearLayout.VERTICAL);

		_animOpen = AnimationUtils.loadAnimation(context, R.anim.slide_up_in);
		_animClose = AnimationUtils.loadAnimation(context,
				R.anim.slide_down_out);

		_animBlink = new AlphaAnimation(1, 0);
		_animBlink.setDuration(_HIGHLIGHT_MENU_ITEM_DURATION);
		_animBlink.setInterpolator(new LinearInterpolator());
		_animBlink.setRepeatCount(_HIGHLIGHT_REPETITIONS);
		_animBlink.setRepeatMode(Animation.REVERSE);
	}

	public void setOnCustomMenuItemClickHandler(OnBottomLeftMenuItemClickHandler l) {
		_onCustomMenuItemClickHandler = l;
	}

	public void addMenuItem(BottomLeftMenuItemView v) {
		v.setOnClickListener(this);
		addView(v);
	}

	public boolean isOpened() {
		return _isOpened;
	}

	public void openMenu() {
		if (!_isOpened) {
			setVisibility(View.VISIBLE);
			startAnimation(_animOpen);
			_isOpened = true;
		}
	}

	public void closeMenu() {
		if (_isOpened) {
			setVisibility(View.GONE);
			startAnimation(_animClose);
			_isOpened = false;
		}

		if (!_animBlink.hasEnded()) {
			_animBlink.cancel();
			_animBlink.reset();
		}
	}

	public void blinkAnimationMenuItem(int identifier) {

		if (!_isOpened)
			openMenu();

		int i;
		BottomLeftMenuItemView v;
		for (i = 0; i < getChildCount(); ++i) {
			v = (BottomLeftMenuItemView) getChildAt(i);
			if (v.getIdentifier() == identifier) {
				v.startAnimation(_animBlink);
				break;
			}
		}
	}

	public void onClick(View v) {

		closeMenu();
		BottomLeftMenuItemView cmv = (BottomLeftMenuItemView) v;
		if (_onCustomMenuItemClickHandler != null)
			onCustomMenuItemClick(cmv);
	}

	@Override
	public void onCustomMenuItemClick(BottomLeftMenuItemView item) {
		if (_onCustomMenuItemClickHandler != null)
			_onCustomMenuItemClickHandler.onClick(item);

	}
}
