package com.example.wuji.mvp.login;

import android.support.annotation.NonNull;

import com.example.wuji.data.LoginData;
import com.example.wuji.data.LoginType;
import com.example.wuji.data.source.LoginDataRepository;
import com.example.wuji.data.source.ReadLaterArticlesRepository;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class LoginPresenter implements LoginContract.Presenter {

    private final LoginContract.View view;
    private final LoginDataRepository repository;
    private final ReadLaterArticlesRepository readLaterArticlesRepository;
    private final CompositeDisposable compositeDisposable;

    public LoginPresenter(@NonNull LoginContract.View view, @NonNull LoginDataRepository loginDataRepository,
                          @NonNull ReadLaterArticlesRepository readLaterArticlesRepository) {
        this.view = view;
        this.repository = loginDataRepository;
        this.readLaterArticlesRepository = readLaterArticlesRepository;
        this.view.setPresenter(this);
        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void login(String username, String password, @NonNull LoginType loginType) {
        getLoginData(username, password, loginType);
    }

    @Override
    public void clearReadLaterData() {
        readLaterArticlesRepository.clearAll();
    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unSubscribe() {
        compositeDisposable.clear();
    }

    private void getLoginData(String username, String password, @NonNull final LoginType loginType) {

        Disposable disposable = repository.getRemoteLoginData(username, password, loginType)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<LoginData>() {

                    @Override
                    public void onNext(LoginData value) {
                        if (!view.isActive()) {
                            return;
                        }
                        if (value.getErrorCode() == -1) {
                            view.showLoginError(value.getErrorMsg());
                        } else {
                            view.saveUser2Preference(value.getData());
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if (view.isActive()) {
                            view.showNetworkError();
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        compositeDisposable.add(disposable);
    }
}
