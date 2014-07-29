![Alt text](res/drawable-hdpi/ic_launcher.png "Icon") An easy to use menu view, located in the bottom left corner of the screen.
===================

![Alt text](screenshot.jpg "BottomLeftMenuView Samsung Galaxy S2")

Required min API level 8
-------------------------

USAGE
------
Add the menu to your XML layout file:
```java
<RelativeLayout

    ...

    <com.rany.albeg.wein.bottomleftmenu.views.BottomLeftMenu
        android:id="@+id/bottom_left_menu"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentBottom="true"
        android:background="@drawable/custom_menu_bg"
        android:visibility="gone" >
    </com.rany.albeg.wein.bottomleftmenu.views.BottomLeftMenu>

</RelativeLayout>
```
Use the menu in java code, like so:
```java
mMenu = (BottomLeftMenuView) findViewById(R.id.bottom_left_menu);
/*
 * Populate the menu with views.
 */
mMenu.addMenuItem(this, R.drawable.ic_action_settings, R.string.settings, _MENU_ID_SETTINGS);
mMenu.addMenuItem(this, R.drawable.ic_action_about, R.string.about, _MENU_ID_ABOUT);
mMenu.addMenuItem(this, R.drawable.ic_rating_good, R.string.rate, _MENU_ID_RATE);
mMenu.addMenuItem(this, R.drawable.ic_action_search, R.string.search, _MENU_ID_SEARCH);
mMenu.addMenuItem(this, R.drawable.ic_social_share, R.string.share, _MENU_ID_SHARE);
mMenu.addMenuItem(this, R.drawable.ic_collections_cloud, R.string.upload, _MENU_ID_UPLOAD);
mMenu.addMenuItem(this, R.drawable.ic_social_send_now, R.string.send, _MENU_ID_SEND);
/*
 * Set a listener for click events.
 */
mMenu.setOnBottomLeftMenuItemClickListener(this);
```

You might want to override **onBackPressed()** and **onKeyDown(int keyCode, KeyEvent event)** for opening and closing the menu:

```java
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
```
For a full working example, please browse the **src** directory in this repository, and look for UsageExampleActivity.java.
Inside the **bin** directory you can find the **apk** file of this demonstration.

AUTHOR
-------

**Rany Albeg Wein**


LICENSE
--------
Copyright (C) 2014 Rany Albeg Wein - rany.albeg@gmail.com

This program is free software; you can redistribute it and/or
modify it under the terms of the GNU General Public License
as published by the Free Software Foundation; either version 2
of the License, or (at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.

