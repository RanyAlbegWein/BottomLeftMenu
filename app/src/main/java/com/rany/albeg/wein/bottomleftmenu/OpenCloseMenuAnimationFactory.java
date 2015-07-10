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
