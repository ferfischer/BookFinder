package com.fernandofischer.bookfinder;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class BookFinderActivity extends AppCompatActivity implements AsyncTaskDelegate {

    BookAdapter mAdapter;
    TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_finder);

        // Seta o empty view
        ListView bookListView = (ListView) findViewById(R.id.list);

        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        bookListView.setEmptyView(mEmptyStateTextView);

        if (!hasConnection()) {
            hideLoadingIndicator();
            mEmptyStateTextView.setText(R.string.no_connection);
            return;
        }

        // Seta o botao de busca
        Button searchButton = (Button) findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View v) {
                                                showLoadingIndicator();
                                                TextView searchText = (TextView) findViewById(R.id.search_text);
                                                searchBook(searchText.getText().toString());
                                            }
                                        }
        );

    }

    private void searchBook(String searchQuery) {
        ListView bookListView = (ListView) findViewById(R.id.list);

        BookFinderAsyncTask task = new BookFinderAsyncTask(getApplicationContext(), this);
        try {
            task.execute("https://www.googleapis.com/books/v1/volumes?maxResults=10&q=" + URLEncoder.encode(searchQuery, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        mAdapter = new BookAdapter(this, new ArrayList<Book>());

        bookListView.setAdapter(mAdapter);
    }

    private boolean hasConnection() {
        ConnectivityManager cm =
                (ConnectivityManager) this.getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    private void showLoadingIndicator() {
        // Exibe o indicador de carregamento porque os dados foram carregados
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.VISIBLE);
    }

    private void hideLoadingIndicator() {
        // Esconde o indicador de carregamento porque os dados foram carregados
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);
    }

    @Override
    public void processFinish(Object output) {
        // Seta texto do estado vazio para mostrar "Nenhum livro encontrado."
        mEmptyStateTextView.setText(R.string.nothing_found);

        // Limpa o adapter de dados de books anteriores
        mAdapter.clear();

        // Se há uma lista válida de {@link Book}s, então adiciona-os ao data set do adapter.
        // Isto irá ativar a ListView para atualizar.
        if (output != null) {
            mAdapter.addAll((List<Book>)output);
        }

        hideLoadingIndicator();
    }
}
