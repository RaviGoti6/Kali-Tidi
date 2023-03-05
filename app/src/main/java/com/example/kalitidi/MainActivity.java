package com.example.kalitidi;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView txtPlayercount;
    EditText edtPlayer1, edtPlayer2, edtPlayer3, edtPlayer4, edtPlayer5, edtPlayer6, edtPlayer7, edtPlayer8;
    Button btnNext;
    String player1Name = null, player2Name = null, player3Name = null, player4Name = null, player5Name = null, player6Name = null, player7Name = null, player8Name = null;
    ApplicationManager applicationManager;
    dbHelper dh;
    Integer points = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        applicationManager = new ApplicationManager(getApplicationContext());

        if (!applicationManager.GetSession().equals("")) {
            startActivity(new Intent(MainActivity.this, MainActivity2.class));
            finish();
        }

        txtPlayercount = (TextView) findViewById(R.id.txtPlayercount);
        edtPlayer1 = (EditText) findViewById(R.id.edtPlayer1);
        edtPlayer2 = (EditText) findViewById(R.id.edtPlayer2);
        edtPlayer3 = (EditText) findViewById(R.id.edtPlayer3);
        edtPlayer4 = (EditText) findViewById(R.id.edtPlayer4);
        edtPlayer5 = (EditText) findViewById(R.id.edtPlayer5);
        edtPlayer6 = (EditText) findViewById(R.id.edtPlayer6);
        edtPlayer7 = (EditText) findViewById(R.id.edtPlayer7);
        edtPlayer8 = (EditText) findViewById(R.id.edtPlayer8);
        btnNext = (Button) findViewById(R.id.btnNext);

        edtPlayer1.setVisibility(View.INVISIBLE);
        edtPlayer2.setVisibility(View.INVISIBLE);
        edtPlayer3.setVisibility(View.INVISIBLE);
        edtPlayer4.setVisibility(View.INVISIBLE);
        edtPlayer5.setVisibility(View.INVISIBLE);
        edtPlayer6.setVisibility(View.INVISIBLE);
        edtPlayer7.setVisibility(View.INVISIBLE);
        edtPlayer8.setVisibility(View.INVISIBLE);

        dh = new dbHelper(this);

        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.players);
        //create a list of items for the spinner.
        String[] items = new String[]{"1", "2", "3", "4", "5", "6", "7", "8"};
        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, R.layout.spinner_item, items);
        //set the spinners adapter to the previously created one.
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submit();
                applicationManager.SetSession("Game");
            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        switch (position) {
            case 0:
                txtPlayercount.setText(String.valueOf(position + 1));
                edtPlayer1.setVisibility(View.VISIBLE);
                edtPlayer2.setVisibility(View.INVISIBLE);
                edtPlayer3.setVisibility(View.INVISIBLE);
                edtPlayer4.setVisibility(View.INVISIBLE);
                edtPlayer5.setVisibility(View.INVISIBLE);
                edtPlayer6.setVisibility(View.INVISIBLE);
                edtPlayer7.setVisibility(View.INVISIBLE);
                edtPlayer8.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "1 player selected", Toast.LENGTH_SHORT).show();
                break;
            case 1:
                txtPlayercount.setText(String.valueOf(position + 1));
                edtPlayer1.setVisibility(View.VISIBLE);
                edtPlayer2.setVisibility(View.VISIBLE);
                edtPlayer3.setVisibility(View.INVISIBLE);
                edtPlayer4.setVisibility(View.INVISIBLE);
                edtPlayer5.setVisibility(View.INVISIBLE);
                edtPlayer6.setVisibility(View.INVISIBLE);
                edtPlayer7.setVisibility(View.INVISIBLE);
                edtPlayer8.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "2 players selected", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                txtPlayercount.setText(String.valueOf(position + 1));
                edtPlayer1.setVisibility(View.VISIBLE);
                edtPlayer2.setVisibility(View.VISIBLE);
                edtPlayer3.setVisibility(View.VISIBLE);
                edtPlayer4.setVisibility(View.INVISIBLE);
                edtPlayer5.setVisibility(View.INVISIBLE);
                edtPlayer6.setVisibility(View.INVISIBLE);
                edtPlayer7.setVisibility(View.INVISIBLE);
                edtPlayer8.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "3 players selected", Toast.LENGTH_SHORT).show();
                Log.e("Players", "onItemSelected: 3");
                break;
            case 3:
                txtPlayercount.setText(String.valueOf(position + 1));
                edtPlayer1.setVisibility(View.VISIBLE);
                edtPlayer2.setVisibility(View.VISIBLE);
                edtPlayer3.setVisibility(View.VISIBLE);
                edtPlayer4.setVisibility(View.VISIBLE);
                edtPlayer5.setVisibility(View.INVISIBLE);
                edtPlayer6.setVisibility(View.INVISIBLE);
                edtPlayer7.setVisibility(View.INVISIBLE);
                edtPlayer8.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "4 players selected", Toast.LENGTH_SHORT).show();
                break;
            case 4:
                txtPlayercount.setText(String.valueOf(position + 1));
                edtPlayer1.setVisibility(View.VISIBLE);
                edtPlayer2.setVisibility(View.VISIBLE);
                edtPlayer3.setVisibility(View.VISIBLE);
                edtPlayer4.setVisibility(View.VISIBLE);
                edtPlayer5.setVisibility(View.VISIBLE);
                edtPlayer6.setVisibility(View.INVISIBLE);
                edtPlayer7.setVisibility(View.INVISIBLE);
                edtPlayer8.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "5 players selected", Toast.LENGTH_SHORT).show();
                break;
            case 5:
                txtPlayercount.setText(String.valueOf(position + 1));
                edtPlayer1.setVisibility(View.VISIBLE);
                edtPlayer2.setVisibility(View.VISIBLE);
                edtPlayer3.setVisibility(View.VISIBLE);
                edtPlayer4.setVisibility(View.VISIBLE);
                edtPlayer5.setVisibility(View.VISIBLE);
                edtPlayer6.setVisibility(View.VISIBLE);
                edtPlayer7.setVisibility(View.INVISIBLE);
                edtPlayer8.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "6 players selected", Toast.LENGTH_SHORT).show();
                break;
            case 6:
                txtPlayercount.setText(String.valueOf(position + 1));
                edtPlayer1.setVisibility(View.VISIBLE);
                edtPlayer2.setVisibility(View.VISIBLE);
                edtPlayer3.setVisibility(View.VISIBLE);
                edtPlayer4.setVisibility(View.VISIBLE);
                edtPlayer5.setVisibility(View.VISIBLE);
                edtPlayer6.setVisibility(View.VISIBLE);
                edtPlayer7.setVisibility(View.VISIBLE);
                edtPlayer8.setVisibility(View.INVISIBLE);
                Toast.makeText(this, "7 players selected", Toast.LENGTH_SHORT).show();
                break;
            case 7:
                txtPlayercount.setText(String.valueOf(position + 1));
                edtPlayer1.setVisibility(View.VISIBLE);
                edtPlayer2.setVisibility(View.VISIBLE);
                edtPlayer3.setVisibility(View.VISIBLE);
                edtPlayer4.setVisibility(View.VISIBLE);
                edtPlayer5.setVisibility(View.VISIBLE);
                edtPlayer6.setVisibility(View.VISIBLE);
                edtPlayer7.setVisibility(View.VISIBLE);
                edtPlayer8.setVisibility(View.VISIBLE);
                Toast.makeText(this, "8 players selected", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // TODO Auto-generated method stub
    }


    public static class dbHelper extends SQLiteOpenHelper {

        //public static final String Databasename = "kali3.db";
        // Database Name
        private static final String DATABASE_NAME = "kali3.db";

        // Database Version
        private static final int DATABASE_VERSION = 1;

        public dbHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table player(id INTEGER PRIMARY KEY AUTOINCREMENT, PlayerName text, Points text)");
            db.execSQL("create table log(id INTEGER PRIMARY KEY AUTOINCREMENT, Logg text)");
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS player");
            db.execSQL("DROP TABLE IF EXISTS log");
            onCreate(db);
        }

        public boolean insertData(String PlayerName, int Points) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("PlayerName", PlayerName);
            cv.put("Points", Points);

            long rs = db.insert("player", null, cv);
            if (rs == -1) {
                return false;
            } else {
                return true;
            }

        }


    }

    public void submit() {

        player1Name = String.valueOf(edtPlayer1.getText());
        player2Name = String.valueOf(edtPlayer2.getText());
        player3Name = String.valueOf(edtPlayer3.getText());
        player4Name = String.valueOf(edtPlayer4.getText());
        player5Name = String.valueOf(edtPlayer5.getText());
        player6Name = String.valueOf(edtPlayer6.getText());
        player7Name = String.valueOf(edtPlayer7.getText());
        player8Name = String.valueOf(edtPlayer8.getText());

        /*Logg.d("player1", player1Name);
        Logg.d("player2", player2Name);
        Logg.d("player3", player3Name);
        Logg.d("player4", player4Name);
        Logg.d("player5", player5Name);
        Logg.d("player6", player6Name);
        Logg.d("player7", player7Name);
        Logg.d("player8", player8Name);*/

        boolean result, result1, result2, result3, result4, result5, result6, result7;

        if (!player1Name.equals("")) {
            result = dh.insertData(player1Name, points);
        }
        if (!player2Name.equals("")) {
            result1 = dh.insertData(player2Name, points);
        }
        if (!player3Name.equals("")) {
            result2 = dh.insertData(player3Name, points);
        }
        if (!player4Name.equals("")) {
            result3 = dh.insertData(player4Name, points);
        }
        if (!player5Name.equals("")) {
            result4 = dh.insertData(player5Name, points);
        }
        if (!player6Name.equals("")) {
            result5 = dh.insertData(player6Name, points);
        }
        if (!player7Name.equals("")) {
            result6 = dh.insertData(player7Name, points);
        }
        if (!player8Name.equals("")) {
            result7 = dh.insertData(player8Name, points);
        }

        if ((result = true) || (result2 = true) || (result3 = true) || (result4 = true) || (result5 = true) || (result6 = true) || (result7 = true)) {
            Toast.makeText(MainActivity.this, "Data Entered", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Error Occuerd", Toast.LENGTH_SHORT).show();
        }
     /*else

    {
        Toast.makeText(MainActivity.this, "Enter Player Name", Toast.LENGTH_SHORT).show();
    }*/

        Intent i = new Intent(MainActivity.this, MainActivity2.class);
        // i.putExtra("Player1", player1Name);
        // i.putExtra("Player2", player2Name);
        // i.putExtra("Player3", player3Name);
        // i.putExtra("Player4", player4Name);
        //i.putExtra("Player5", player5Name);
        //i.putExtra("Player6", player6Name);
        //i.putExtra("Player7", player7Name);
        //i.putExtra("Player8", player8Name);

        startActivity(i);
        finish();
    }


}

