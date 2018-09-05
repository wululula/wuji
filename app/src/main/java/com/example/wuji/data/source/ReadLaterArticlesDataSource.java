package com.example.wuji.data.source;

import android.database.Observable;
import android.support.annotation.NonNull;

import com.example.wuji.data.ReadLaterArticleData;

import java.util.List;

public interface ReadLaterArticlesDataSource {
    Observable<List<ReadLaterArticleData>> getReadLaterArticles(@NonNull int userId);

    void insertReadLaterArticle(int userId, @NonNull int id, @NonNull long timeStamp);

    void removeReadLaterArticle(int userId, @NonNull int id);

    boolean isExist(int userId, @NonNull int id);

    void clearAll();
}
