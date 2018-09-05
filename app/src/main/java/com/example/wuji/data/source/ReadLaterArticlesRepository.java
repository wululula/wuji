package com.example.wuji.data.source;

import android.database.Observable;
import android.support.annotation.NonNull;

import com.example.wuji.data.ReadLaterArticleData;

import java.util.List;

public class ReadLaterArticlesRepository implements ReadLaterArticlesDataSource{
    @NonNull
    private static ReadLaterArticlesRepository INSTANCE;
    private final ReadLaterArticlesDataSource localDataSource;

    private ReadLaterArticlesRepository(@NonNull ReadLaterArticlesDataSource local) {
        this.localDataSource = local;
    }

    public static ReadLaterArticlesRepository getInstance(@NonNull ReadLaterArticlesDataSource local) {
        if (INSTANCE == null) {
            INSTANCE = new ReadLaterArticlesRepository(local);
        }
        return INSTANCE;
    }

    @Override
    public Observable<List<ReadLaterArticleData>> getReadLaterArticles(@NonNull int userId) {
        return localDataSource.getReadLaterArticles(userId);
    }

    @Override
    public void insertReadLaterArticle(int userId, @NonNull int id, @NonNull long timeStamp) {
        localDataSource.insertReadLaterArticle(userId, id, timeStamp);
    }

    @Override
    public void removeReadLaterArticle(int userId, @NonNull int id) {
        localDataSource.removeReadLaterArticle(userId, id);
    }

    @Override
    public boolean isExist(int userId, @NonNull int id) {
        return localDataSource.isExist(userId, id);
    }

    @Override
    public void clearAll() {
        localDataSource.clearAll();
    }
}
