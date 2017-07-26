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

import static android.R.attr.data;

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

        ViewHolderItem viewHolder;

        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);

            // well set up the ViewHolder
            viewHolder = new ViewHolderItem();
            viewHolder.thumb = (ImageView) listItemView.findViewById(R.id.thumbnail);
            viewHolder.title = (TextView) listItemView.findViewById(R.id.title);
            viewHolder.subtitle = (TextView) listItemView.findViewById(R.id.subtitle);
            viewHolder.author = (TextView) listItemView.findViewById(R.id.author);

            // store the holder with the view.
            listItemView.setTag(viewHolder);
        } else {
            // we've just avoided calling findViewById() on resource everytime
            // just use the viewHolder
            viewHolder = (ViewHolderItem) listItemView.getTag();
        }

        final Book currentBook = getItem(position);

        viewHolder.thumb.setImageBitmap(currentBook.getBitmap());

        viewHolder.title.setText(currentBook.getTitle());

        if (currentBook.getSubtitle() != null && !currentBook.getSubtitle().isEmpty()) {
            viewHolder.subtitle.setText(currentBook.getSubtitle());
        } else {
            viewHolder.subtitle.setVisibility(View.GONE);
        }

        viewHolder.author.setText(currentBook.getAuthor());

        return listItemView;

    }

    static class ViewHolderItem {
        ImageView thumb;
        TextView title;
        TextView subtitle;
        TextView author;
    }
}
