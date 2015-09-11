package com.example.jarry.practice_mvp.model.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.example.jarry.practice_mvp.model.BookEntity;
import com.example.jarry.practice_mvp.model.BookEntityJsonMapper;


/**
 * Created by root on 15-9-11.
 */
public class RestApiImpl implements RestApi {
    private final Context context;
    private final BookEntityJsonMapper bookEntityJsonMapper;

    public RestApiImpl(Context context) {
        this.context = context.getApplicationContext();
        this.bookEntityJsonMapper = new BookEntityJsonMapper();
    }

    @Override
    public void getBookDetailByIsbn(String isbn, BookDetailsCallback bookDetailsCallback) {
        if (bookDetailsCallback == null) {
            throw new IllegalArgumentException("bookDetailsCallback cannot be null");
        }

        if (isThereInternetConnection()) {
            try {
                String apiUrl = RestApi.API_ISBN_BASE_URL + isbn;

                ApiConnection getBookDetailsConnection = ApiConnection.createGET(apiUrl);
                String responseBookDetails = getBookDetailsConnection.requestSyncCall();
                BookEntity bookEntity = this.bookEntityJsonMapper.transformBookEntity(responseBookDetails);
                bookDetailsCallback.onBookEntityLoaded(bookEntity);

            } catch (Exception e) {
                bookDetailsCallback.onError(e);
            }
        }else {
            bookDetailsCallback.onError(new Exception());
        }
    }

    private boolean isThereInternetConnection() {
        boolean isConnected;
        ConnectivityManager connectivityManager = (ConnectivityManager)this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected  = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;

    }
}
