package com.example.wuji.mvp.login;

import android.support.annotation.NonNull;

import com.example.wuji.data.LoginDetailData;
import com.example.wuji.data.LoginType;
import com.example.wuji.mvp.BasePresenter;
import com.example.wuji.mvp.BaseView;

public interface LoginContract {
    interface Presenter extends BasePresenter {
        void login(String username, String password, @NonNull LoginType loginType);

        void clearReadLaterData();
    }

    interface View extends BaseView<Presenter> {
        void showLoginError(String errorMsg);

        boolean isActive();

        void saveUser2Preference(LoginDetailData loginDetailData);

        void showNetworkError();
    }

}
