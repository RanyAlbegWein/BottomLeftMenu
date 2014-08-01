package com.rany.albeg.wein.bottomleftmenu;

import android.content.Context;
import android.view.animation.Animation;

public abstract class OpenCloseMenuAnimation {

	protected Context	mContext;

	public OpenCloseMenuAnimation(Context context) {
		mContext = context;
	}

	public abstract Animation open();

	public abstract Animation close();
}
