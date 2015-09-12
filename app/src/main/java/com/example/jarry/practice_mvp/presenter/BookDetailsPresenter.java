package com.example.jarry.practice_mvp.presenter;

import android.content.Context;
import android.os.Looper;
import com.example.jarry.practice_mvp.model.BookEntity;
import com.example.jarry.practice_mvp.model.net.RestApi;
import com.example.jarry.practice_mvp.model.net.RestApiImpl;
import com.example.jarry.practice_mvp.view.LoadBookView;

import android.os.Handler;

/**
 * Created by root on 15-9-11.
 */
public class BookDetailsPresenter {
    private RestApi restApi = null;
    private LoadBookView loadBookView;
    private String isbn;

    public BookDetailsPresenter(Context context, String isbn) {
        restApi = new RestApiImpl(context);
        this.isbn =isbn;
    }

    public void setView(LoadBookView loadBookView) {
        this.loadBookView = loadBookView;
    }
    public void loadData() {
        loadBookView.showLoading();
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
            BookDetailsPresenter.this.loadBookView.hideLoading();
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
                BookDetailsPresenter.this.loadBookView.showDetailsView(bookEntity);
            }
        });
    }


}
