package com.rany.albeg.wein.bottomleftmenu;

import android.content.Context;

import com.rany.albeg.wein.bottomleftmenu.BottomLeftMenu.OPEN_CLOSE_ANIMATION;
import com.rany.albeg.wein.bottomleftmenu.animations.BottomTop;
import com.rany.albeg.wein.bottomleftmenu.animations.FadeInOut;
import com.rany.albeg.wein.bottomleftmenu.animations.LeftRight;

public class OpenCloseMenuAnimationFactory {

	public static OpenCloseMenuAnimation getAnimation(Context context, OPEN_CLOSE_ANIMATION animType) {
		OpenCloseMenuAnimation animation = null;
		if (animType == OPEN_CLOSE_ANIMATION.BOTTOM_TOP) {

			animation = new BottomTop(context);

		} else if (animType == OPEN_CLOSE_ANIMATION.LEFT_RIGHT) {

			animation = new LeftRight(context);

		} else if (animType == OPEN_CLOSE_ANIMATION.FADE_IN_OUT) {

			animation = new FadeInOut(context);
		}

		return animation;
	}
}
