package com.mespitech.mvpbase.uikit;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TabBarItem {
    private LinearLayout container;
    private ImageView icon;
    private TextView title;

    public TabBarItem(LinearLayout container, ImageView icon, TextView title) {
        this.container = container;
        this.icon = icon;
        this.title = title;
    }

    public ImageView getIcon() {
        return icon;
    }

    public TextView getTitle() {
        return title;
    }

    public void setIcon(ImageView icon) {
        this.icon = icon;
    }

    public void setTitle(TextView title) {
        this.title = title;
    }

    public LinearLayout getContainer() {
        return container;
    }

    public void setContainer(LinearLayout container) {
        this.container = container;
    }
}
