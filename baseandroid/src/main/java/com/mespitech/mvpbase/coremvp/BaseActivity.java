package com.mespitech.mvpbase.coremvp;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.DrawableRes;
import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mespitech.mvpbase.R;
import com.mespitech.mvpbase.uikit.LoadingView;

import java.io.File;

public abstract class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements BaseView {

    protected T mPresenter;

    private LoadingView loadingView;

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void init();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       try {
           // disable rotation screen
           setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
           setContentView(getLayoutId());
           mPresenter = initPresenter();
           mPresenter.attachView(this);
           loadingView = new LoadingView(this);
           setStatusBarDrawable();
           init();
       }catch (Exception e){

       }
    }

    @Override
    protected void onDestroy() {
        try {
            mPresenter.detachView();
        }catch (Throwable e){

        }
        super.onDestroy();
    }

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) { e.printStackTrace();}
    }

    public static boolean deleteDir(File dir) {
        if (dir != null && dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
            return dir.delete();
        } else if(dir!= null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

    protected abstract T initPresenter();

    public void showLoading() {
        try{
            loadingView.show();
        }catch (Exception e){

        }
    }

    public void dismissLoading() {
        try {
            loadingView.cancel();
        }catch (Exception e){

        }

    }

    @Nullable
    protected Drawable overrideStatusBar() {
        return getDrawable(R.drawable.status_bar);
    }

    private void setStatusBarDrawable() {
        Drawable background = overrideStatusBar();
        if (background == null) {
            return;
        }
        Window window = getWindow();
        int navigationColor = window.getNavigationBarColor();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        window.setNavigationBarColor(Color.TRANSPARENT);
        window.setBackgroundDrawable(background);
        window.setNavigationBarColor(navigationColor);
    }

    View focusedView;
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                    INPUT_METHOD_SERVICE);
            focusedView = this.getCurrentFocus();
            if (focusedView != null)
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        }catch (Exception e){

        }
        return true;
    }

    public void popKeyBord(){
        try {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.
                    INPUT_METHOD_SERVICE);
            focusedView = this.getCurrentFocus();
            if (focusedView != null)
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);

        }catch (Exception e){

        }
    }
}
