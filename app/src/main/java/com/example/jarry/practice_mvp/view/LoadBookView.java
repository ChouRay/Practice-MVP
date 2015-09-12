package com.example.jarry.practice_mvp.view;

import com.example.jarry.practice_mvp.model.BookEntity;

/**
 * Created by root on 15-9-11.
 */
public interface LoadBookView extends LoadDataView {
    /**
     *
     * @param entity
     */
    void showDetailsView(BookEntity entity);
}
