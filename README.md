![Alt text](res/drawable-hdpi/ic_launcher.png "Icon") An easy to use menu view, located in the bottom left corner of the screen.
===================

![Alt text](screenshot.jpg "BottomLeftMenuView Samsung Galaxy S2")

Required min API level 5
-------------------------

USAGE
------
    mMenu = (BottomLeftMenuView) findViewById(R.id.bottom_left_menu);

    mMenu.setOnCustomMenuItemClickHandler(this);

    mMenu.addMenuItem(new BottomLeftMenuItemView(this, R.drawable.ic_action_settings, R.string.settings, _MENU_ID_SETTINGS));
    mMenu.addMenuItem(new BottomLeftMenuItemView(this, R.drawable.ic_action_about, R.string.about, _MENU_ID_ABOUT));
    mMenu.addMenuItem(new BottomLeftMenuItemView(this, R.drawable.ic_rating_good, R.string.rate, _MENU_ID_RATE));
    mMenu.addMenuItem(new BottomLeftMenuItemView(this, R.drawable.ic_action_search, R.string.search, _MENU_ID_SEARCH));
    mMenu.addMenuItem(new BottomLeftMenuItemView(this, R.drawable.ic_social_share, R.string.share, _MENU_ID_SHARE));
    mMenu.addMenuItem(new BottomLeftMenuItemView(this, R.drawable.ic_collections_cloud, R.string.upload, _MENU_ID_UPLOAD));
    mMenu.addMenuItem(new BottomLeftMenuItemView(this, R.drawable.ic_social_send_now, R.string.send, _MENU_ID_SEND));

You might want to override **onBackPressed()** and **onKeyDown(int keyCode, KeyEvent event)** for opening and closing the menu:

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == KeyEvent.KEYCODE_MENU) {

            if (mMenu.isOpened())
                mMenu.closeMenu();
            else {
                mMenu.openMenu();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {

        if (mMenu.isOpened()) {
            mMenu.closeMenu();
        } else {
            super.onBackPressed();
        }
    }


For a full working example, please browse the **src** directory in this repository, and look for UsageExampleActivity.java
Inside the **bin** directory you can find the **apk** file of this demonstration.

AUTHOR
-------

**Rany Albeg Wein**


LICENSE
--------

Copyright 2014 Rany Albeg Wein

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

