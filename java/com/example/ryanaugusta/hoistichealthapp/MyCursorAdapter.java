// Project:     Holistic Health App
// Author:      Ryan Augusta
// Date:        2018
// Desc:        This Handles the cursor adapter

package com.example.ryanaugusta.hoistichealthapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.text.style.TtsSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by ryanaugusta on 2/4/18.
 */

public class MyCursorAdapter extends CursorAdapter implements Filterable {

    SQLiteDatabase db;

    // CursorAdapter will handle all the moveToFirst(), getCount() logic
    public MyCursorAdapter(Context context, Cursor c)
    {
        super(context, c, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup viewGroup)
    {
        // using my own list item layout
        return LayoutInflater.from(context).inflate(R.layout.list_items, viewGroup, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        // get the data from the cursor
        String id = cursor.getString(0);
        String name = cursor.getString(1);
        String info = cursor.getString(2);

        // get the textViews inside the item in the listView
        TextView tvItemName = (TextView)view.findViewById(R.id.tvItemName);
        TextView tvItemInfo = (TextView)view.findViewById(R.id.tvItemInfo);

        // assign the data from the cursor to the textViews in the item in listView
        tvItemName.setText(name);
        tvItemInfo.setText(info);

    }
}
