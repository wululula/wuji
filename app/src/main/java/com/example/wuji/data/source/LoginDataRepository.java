package com.example.wuji.data.source;


import android.support.annotation.NonNull;

import com.example.wuji.data.LoginData;
import com.example.wuji.data.LoginDetailData;
import com.example.wuji.data.LoginType;

import io.reactivex.Observable;

public class LoginDataRepository implements LoginDataSource {
    private static LoginDataRepository INSTANCE;
    @NonNull
    private LoginDataSource localDataSource;
    @NonNull
    private LoginDataSource remoteDataSource;

    public static LoginDataRepository getInstance(@NonNull LoginDataSource localDataSource,@NonNull LoginDataSource remoteDataSource){
        if (INSTANCE == null) {
            INSTANCE = new LoginDataRepository(localDataSource, remoteDataSource);
        }
        return INSTANCE;
    }

    private LoginDataRepository(@NonNull LoginDataSource localDataSource,@NonNull LoginDataSource remoteDataSource){
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public Observable<LoginData> getRemoteLoginData(@NonNull String userName, @NonNull String password, @NonNull LoginType loginType) {
        return remoteDataSource.getRemoteLoginData(userName, password,loginType);
    }

    @Override
    public Observable<LoginDetailData> getLocalLoginData(@NonNull int userId) {
        return localDataSource.getLocalLoginData(userId);
    }
}
