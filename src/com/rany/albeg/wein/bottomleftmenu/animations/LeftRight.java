package com.rany.albeg.wein.bottomleftmenu.animations;

import android.content.Context;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.rany.albeg.wein.bottomleftmenu.OpenCloseMenuAnimation;
import com.rany.albeg.wein.bottomleftmenu.R;

public class LeftRight extends OpenCloseMenuAnimation {

	public LeftRight(Context context) {
		super(context);
	}

	@Override
	public Animation open() {
		return AnimationUtils.loadAnimation(mContext, R.anim.slide_right_in);
	}

	@Override
	public Animation close() {
		return AnimationUtils.loadAnimation(mContext, R.anim.slide_left_out);
	}
}
