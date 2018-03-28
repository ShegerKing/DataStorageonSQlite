package com.example.beakaltiliskew.datastorageonsqlite;

/**
 * Created by Beakal Tiliskew on 3/25/2018.
 */
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.ThemedSpinnerAdapter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Beakal Tiliskew on 3/24/2018.
 */

public class DisplaySaved extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.savedpreference);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DbHelper Helper = new DbHelper(getApplicationContext());
        SQLiteDatabase db = Helper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
// you will actually use after this query.
        String[] projection = {

                Contract.infoEntry.COLUMN_NAME_FIRST,
                Contract.infoEntry.COLUMN_NAME_LAST,
                Contract.infoEntry.COLUMN_NAME_PHONE,
                Contract.infoEntry.COLUMN_NAME_EMAIL
        };

        String[] binding = {

                Contract.infoEntry._ID,
                Contract.infoEntry.COLUMN_NAME_FIRST,
                Contract.infoEntry.COLUMN_NAME_LAST,
                Contract.infoEntry.COLUMN_NAME_PHONE,
                Contract.infoEntry.COLUMN_NAME_EMAIL
        };


// Filter results WHERE "title" = 'My Title'


// How you want the results sorted in the resulting Cursor
        String sortOrder =
                Contract.infoEntry.COLUMN_NAME_FIRST + " DESC";

        Cursor cursor = db.query(
                Contract.infoEntry.TABLE_NAME,   // The table to query
                binding,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        int[] item = new int[]{R.id.first, R.id.last, R.id.phone, R.id.email};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(getApplicationContext(), R.layout.adapterlayout, cursor, projection, item, 0);

        final ListView listView = (ListView) findViewById(android.R.id.list);
        listView.setAdapter(adapter);
        TextView emptyView = (TextView) findViewById(android.R.id.empty);
        listView.setEmptyView(emptyView);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.display_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menuactivity3) {
            Intent intent = new Intent(getApplicationContext()
                    , MainActivity.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.menuactivity1){
            DbHelper helper = new DbHelper(getApplicationContext());
            SQLiteDatabase db = helper.getWritableDatabase();
            db.delete(Contract.infoEntry.TABLE_NAME, "1", null);
            final ListView listView = (ListView) findViewById(android.R.id.list);
            TextView emptyView = (TextView) findViewById(android.R.id.empty);
            listView.setEmptyView(emptyView);
        }


        return super.onOptionsItemSelected(item);




    }





}
