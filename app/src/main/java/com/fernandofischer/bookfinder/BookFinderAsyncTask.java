package com.fernandofischer.bookfinder;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

/**
 * Created by Fernando.Fischer on 25/07/2017.
 */

public class BookFinderAsyncTask extends AsyncTask<String, Void, List<Book>> {

    private AsyncTaskDelegate delegate = null;

    public BookFinderAsyncTask(Context context, AsyncTaskDelegate responder){
        this.delegate = responder;
    }

    @Override
    protected List<Book> doInBackground(String... urls) {

        if (urls.length < 1 || urls[0] == null) {
            return null;
        }

        List<Book> books = QueryUtils.fetchBooksData(urls[0]);

        return books;
    }

  /*  @Override
    protected void onPreExecute() {
        showLoadingIndicator();
    }
*/
    @Override
    protected void onPostExecute(List<Book> books) {
        super.onPostExecute(books);
        if(delegate != null)
            delegate.processFinish(books);
    }
}