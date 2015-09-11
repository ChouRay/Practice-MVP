package com.example.jarry.practice_mvp.presenter;


import android.content.Context;
import android.os.Looper;
import android.util.Log;

import com.example.jarry.practice_mvp.model.BookEntity;
import com.example.jarry.practice_mvp.model.net.RestApi;
import com.example.jarry.practice_mvp.model.net.RestApiImpl;
import com.example.jarry.practice_mvp.view.LoadDataView;

import android.os.Handler;

/**
 * Created by root on 15-9-11.
 */
public class BookDetailsPresenter {
    private RestApi restApi = null;
    private LoadDataView loadDataView;
    private String isbn;

    public BookDetailsPresenter(Context context, String isbn) {
        restApi = new RestApiImpl(context);
        this.isbn =isbn;
    }

    public void setView(LoadDataView loadDataView) {
        this.loadDataView = loadDataView;
    }
    public void loadData() {
        loadDataView.showLoading();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                restApi.getBookDetailByIsbn(isbn, bookDetailsCallback);
            }
        });
        thread.start();
    }


    private RestApi.BookDetailsCallback bookDetailsCallback = new RestApi.BookDetailsCallback() {
        @Override
        public void onBookEntityLoaded(BookEntity bookEntity) {
            notifyDataLoadedSuccessful(bookEntity);
            BookDetailsPresenter.this.loadDataView.hideLoading();
        }

        @Override
        public void onError(Exception e) {

        }
    };

    private void notifyDataLoadedSuccessful(final BookEntity bookEntity) {
        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {
            @Override
            public void run() {
                BookDetailsPresenter.this.loadDataView.showDetailsView(bookEntity);
            }
        });
    }


}
