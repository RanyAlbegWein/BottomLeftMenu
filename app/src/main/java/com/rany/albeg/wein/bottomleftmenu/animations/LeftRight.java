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
