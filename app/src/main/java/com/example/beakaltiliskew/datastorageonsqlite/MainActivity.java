package com.example.beakaltiliskew.datastorageonsqlite;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText firstName, lastName, phoneNumber, emailAddress;
    Button Save;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        firstName = (EditText)findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        phoneNumber = findViewById(R.id.phoneNumber);
        emailAddress = findViewById(R.id.email);
        Save = (Button) findViewById(R.id.button);

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DbHelper Helper = new DbHelper(getApplicationContext());
                SQLiteDatabase db=  Helper.getWritableDatabase();


                // Create a new map of values, where column names are the keys
                ContentValues values = new ContentValues();

                values.put(Contract.infoEntry.COLUMN_NAME_FIRST, firstName.getText().toString());
                values.put(Contract.infoEntry.COLUMN_NAME_LAST, lastName.getText().toString());
                values.put(Contract.infoEntry.COLUMN_NAME_PHONE, phoneNumber.getText().toString());
                values.put(Contract.infoEntry.COLUMN_NAME_EMAIL, emailAddress.getText().toString());


// Insert the new row, returning the primary key value of the new row
                long newRowId = db.insert(Contract.infoEntry.TABLE_NAME, null, values);

                String result;

                if (newRowId != -1){

                    result = "New Person Created";

                }else{

                    result = "Error";

                }
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getApplicationContext(), result , duration);
                toast.show();



                firstName.setText("");
                lastName.setText("");
                phoneNumber.setText("");
                emailAddress.setText("");



            }
        });



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.display_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.menuactivity2) {
            Intent intent  = new Intent(getApplicationContext(),DisplaySaved.class);
            startActivity(intent);
            return true;
        }
        if (id == R.id.menuactivity3){

            DbHelper helper = new DbHelper(getApplicationContext());
            SQLiteDatabase db = helper.getWritableDatabase();
            db.delete(Contract.infoEntry.TABLE_NAME, "1", null);
            return true;


        }
        return super.onOptionsItemSelected(item);
    }
}
