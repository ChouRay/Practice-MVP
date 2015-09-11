package com.example.jarry.practice_mvp.view;

import com.example.jarry.practice_mvp.model.BookEntity;

/**
 * Created by root on 15-9-11.
 */
public interface LoadDataView {
    void showLoading();
    void hideLoading();

    void showDetailsView(BookEntity entity);
}
