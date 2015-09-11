package com.example.jarry.practice_mvp.model.net;

import com.example.jarry.practice_mvp.model.BookEntity;

/**
 * Created by root on 15-9-11.
 */
public interface RestApi {
    String API_ISBN_BASE_URL = "https://api.douban.com/v2/book/isbn/";

    interface BookDetailsCallback {
        void onBookEntityLoaded(BookEntity bookEntity);
        void onError(Exception e);
    }

    void getBookDetailByIsbn(final String isbn, final BookDetailsCallback bookDetailsCallback);

}

