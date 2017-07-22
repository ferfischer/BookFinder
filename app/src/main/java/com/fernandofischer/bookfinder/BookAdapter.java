package com.fernandofischer.bookfinder;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.util.List;

/**
 * Created by ferna on 17/07/2017.
 */

public class BookAdapter extends ArrayAdapter<Book> {
    public BookAdapter(Context context, List<Book> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        final Book currentBook = getItem(position);

        new DownloadImageTask((ImageView) listItemView.findViewById(R.id.thumbnail))
                .execute(currentBook.getThumbnail());

        TextView title = (TextView) listItemView.findViewById(R.id.title);
        title.setText(currentBook.getTitle());

        TextView subtitle = (TextView) listItemView.findViewById(R.id.subtitle);
        if (currentBook.getSubtitle() != null && !currentBook.getSubtitle().isEmpty()) {
            subtitle.setText(currentBook.getSubtitle());
        } else {
            subtitle.setVisibility(View.GONE);
        }

        TextView author = (TextView) listItemView.findViewById(R.id.author);
        author.setText(currentBook.getAuthor());

        return listItemView;

    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
