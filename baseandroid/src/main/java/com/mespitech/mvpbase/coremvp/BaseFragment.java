package com.mespitech.mvpbase.coremvp;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.mespitech.mvpbase.uikit.LoadingView;

import java.io.File;
import java.util.Objects;

public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements BaseView {

    private View rootView;

    protected T mPresenter;

    private LoadingView loadingView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null || !preventView()) {
            rootView = inflater.inflate(getLayoutId(), container, false);
        }
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // disable rotation screen
        Objects.requireNonNull(getActivity()).setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        loadingView = new LoadingView(getContext());
        mPresenter = initPresenter();
        mPresenter.attachView(this);
        init();
    }

    @LayoutRes
    protected abstract int getLayoutId();

    protected abstract void init();

    protected abstract T initPresenter();

    protected boolean preventView() {
        return false;
    }

    @Override
    public void onDestroyView() {
        mPresenter.detachView();
        super.onDestroyView();
    }

    public static void deleteCache(Context context) {
        try {
            File dir = context.getCacheDir();
            deleteDir(dir);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        } else if (dir != null && dir.isFile()) {
            return dir.delete();
        } else {
            return false;
        }
    }

    @Override
    public void showLoading() {
        loadingView.show();
    }

    @Override
    public void dismissLoading() {
        loadingView.cancel();
    }
}
