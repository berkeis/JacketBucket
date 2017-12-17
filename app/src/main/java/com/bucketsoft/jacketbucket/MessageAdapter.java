package com.bucketsoft.jacketbucket;

/**
 * Created by Isin on 12/15/2017.
 */


import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class MessageAdapter extends ArrayAdapter<Books> {
    public MessageAdapter(Context context, int resource, List<Books> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_message, parent, false);
        }

        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);
        TextView bookNameTextView = (TextView) convertView.findViewById(R.id.bookName);
        TextView authorTextView = (TextView) convertView.findViewById(R.id.author);
        TextView locations = (TextView) convertView.findViewById(R.id.locations);

        Books aBook = getItem(position);

            Glide.with(photoImageView.getContext())
                    .load(aBook.getPhotoUrl())
                    .into(photoImageView);

        bookNameTextView.setText(aBook.getName());
        authorTextView.setText(aBook.getAuthor());
        locations.setText(aBook.getLocations());

        //TODO: Multiline spinner yaptıktan sonra line 45 yerine kullan.
        /*
        StringBuilder builder = new StringBuilder();
        for (String details : aBook.getLocations()) {
            builder.append(details + "\n");
        }
        locations.setText(builder.toString());
        */

        return convertView;
    }
}