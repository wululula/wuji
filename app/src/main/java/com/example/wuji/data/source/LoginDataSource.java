package com.example.wuji.data.source;


import android.support.annotation.NonNull;

import com.example.wuji.data.LoginData;
import com.example.wuji.data.LoginDetailData;
import com.example.wuji.data.LoginType;

import io.reactivex.Observable;

public interface LoginDataSource {
    Observable<LoginData> getRemoteLoginData(@NonNull String userName, @NonNull String password, @NonNull LoginType loginType);

    Observable<LoginDetailData> getLocalLoginData(@NonNull int userId);
}
