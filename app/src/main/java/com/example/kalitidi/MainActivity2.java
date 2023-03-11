package com.example.kalitidi;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.text.Spannable;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hootsuite.nachos.NachoTextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {

    TextView txtPlayer1, txtPlayer2, txtPlayer3, txtPlayer4, txtPlayer5, txtPlayer6, txtPlayer7, txtPlayer8;
    TextView txtPoints1, txtPoints2, txtPoints3, txtPoints4, txtPoints5, txtPoints6, txtPoints7, txtPoints8;
    TextView txtCaptainNameText;
    TextView txtPartner1, txtPartner2, txtPartner3;
    String player1 = "", player2 = "", player3 = "", player4 = "", player5 = "", player6 = "", player7 = "", player8 = "";
    int points1 = 0, points2 = 0, points3 = 0, points4 = 0, points5 = 0, points6 = 0, points7 = 0, points8 = 0;
    String partner1, partner2, partner3, Lpartner1, Lpartner2, Lpartner3;
    String id, Players, log;
    long iid;
    Integer points;
    Integer totalPartners;
    //String[] items;
    ArrayList<String> items;
    ArrayList<String> LP;
    ArrayList<String> LPPrint;
    Player player;
    ApplicationManager applicationManager;

    int bid;
    String CaptainName, LCaptainName;
    EditText edtBidAmount;
    Button btnWon, btnLoss, btnNewGame;
    MultiSelectionSpinner dropdownPartners;
    dbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtCaptainNameText = (TextView) findViewById(R.id.txtCaptainNameText);
        btnWon = (Button) findViewById(R.id.btnWon);
        btnLoss = (Button) findViewById(R.id.btnLoss);
        btnNewGame = (Button) findViewById(R.id.btnNewGame);

        ListView listView = (ListView) findViewById(R.id.lvPlayer);
        ListView listViewLog = (ListView) findViewById(R.id.lvLog);

        db = new dbHelper(this);
        applicationManager = new ApplicationManager(getApplicationContext());

        edtBidAmount = (EditText) findViewById(R.id.edtBidAmount);

        player = new Player(id, Players, points);

        /*
        txtPlayer1 = (TextView) findViewById(R.id.txtplayer1);
        txtPlayer2 = (TextView) findViewById(R.id.txtplayer2);
        txtPlayer3 = (TextView) findViewById(R.id.txtplayer3);
        txtPlayer4 = (TextView) findViewById(R.id.txtplayer4);
        txtPlayer5 = (TextView) findViewById(R.id.txtplayer5);
        txtPlayer6 = (TextView) findViewById(R.id.txtplayer6);
        txtPlayer7 = (TextView) findViewById(R.id.txtplayer7);
        txtPlayer8 = (TextView) findViewById(R.id.txtplayer8);

        txtPoints1 = (TextView) findViewById(R.id.txtPoints1);
        txtPoints2 = (TextView) findViewById(R.id.txtPoints2);
        txtPoints3 = (TextView) findViewById(R.id.txtPoints3);
        txtPoints4 = (TextView) findViewById(R.id.txtPoints4);
        txtPoints5 = (TextView) findViewById(R.id.txtPoints5);
        txtPoints6 = (TextView) findViewById(R.id.txtPoints6);
        txtPoints7 = (TextView) findViewById(R.id.txtPoints7);
        txtPoints8 = (TextView) findViewById(R.id.txtPoints8);

         */

        txtPartner1 = (TextView) findViewById(R.id.txtPartner1);
        txtPartner2 = (TextView) findViewById(R.id.txtPartner2);
        txtPartner3 = (TextView) findViewById(R.id.txtPartner3);

        /*
        txtPlayer1.setVisibility(View.INVISIBLE);
        txtPlayer2.setVisibility(View.INVISIBLE);
        txtPlayer3.setVisibility(View.INVISIBLE);
        txtPlayer4.setVisibility(View.INVISIBLE);
        txtPlayer5.setVisibility(View.INVISIBLE);
        txtPlayer6.setVisibility(View.INVISIBLE);
        txtPlayer7.setVisibility(View.INVISIBLE);
        txtPlayer8.setVisibility(View.INVISIBLE);

        txtPoints1.setVisibility(View.INVISIBLE);
        txtPoints2.setVisibility(View.INVISIBLE);
        txtPoints3.setVisibility(View.INVISIBLE);
        txtPoints4.setVisibility(View.INVISIBLE);
        txtPoints5.setVisibility(View.INVISIBLE);
        txtPoints6.setVisibility(View.INVISIBLE);
        txtPoints7.setVisibility(View.INVISIBLE);
        txtPoints8.setVisibility(View.INVISIBLE);

         */

        txtPartner1.setVisibility(View.INVISIBLE);
        txtPartner2.setVisibility(View.INVISIBLE);
        txtPartner3.setVisibility(View.INVISIBLE);

        //get the spinner from the xml.
        Spinner dropdown = findViewById(R.id.Captain);
        dropdownPartners = (MultiSelectionSpinner) findViewById(R.id.dropParteners);

        ArrayList<Player> arrayOfPlayers = new ArrayList<Player>();
        PlayerAdapter adapter = new PlayerAdapter(this, arrayOfPlayers);
        ArrayList<Logg> arrayOfLogs = new ArrayList<Logg>();
        LogAdapter ladapter = new LogAdapter(this, arrayOfLogs);


        /*
        items = new ArrayList<String>(Arrays.asList(arrayOfPlayers));
        Collections.sort(items);

        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);
*/


        //NachoTextView nachoTextView = findViewById(R.id.dropParteners);
        //create a list of items for the spinner.

        //create an adapter to describe how the items are displayed, adapters are used in several places in android.
        //There are multiple variations of this, but this is the basic variant.

        //set the spinners adapter to the previously created one.
        String[] from = new String[]{"PlayerName"};
        int[] to = new int[]{android.R.id.text1};

        Cursor c = db.selectData();

        if (c.getCount() > 0) {
            items = new ArrayList<String>();
            //c.moveToFirst();
            //c.moveToPosition(-1);
            while (c.moveToNext()) {

                id = c.getString(c.getColumnIndex("_id"));
                Players = c.getString(c.getColumnIndex("PlayerName"));
                points = c.getInt(c.getColumnIndex("Points"));
                //Logg.e("TAG", Players);
                items.add(Players);


                //adapter.notifyDataSetChanged();
                listView.setAdapter(adapter);
                Player Player1 = new Player(id, Players, points);
                adapter.add(Player1);

            }
        }

        Cursor clog = db.selectLog();

        if (clog.getCount() > 0) {
            LPPrint = new ArrayList<String>();
            //c.moveToFirst();
            //c.moveToPosition(-1);
            while (clog.moveToNext()) {
                id = clog.getString(clog.getColumnIndex("_id"));
                log = clog.getString(clog.getColumnIndex("Logg"));
                //Logg.e("TAG", Players);
                LPPrint.add(log);

                //adapter.notifyDataSetChanged();
                listViewLog.setAdapter(ladapter);
                Logg logg = new Logg(id, log);
                ladapter.add(logg);

            }
        }

        Collections.sort(arrayOfPlayers, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o2.Points.compareTo(o1.Points);
            }
        });
        //Logg.e("TAG", String.valueOf(c));
        SimpleCursorAdapter sAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, c, from, to, 0);

        sAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sAdapter.setViewBinder(new SimpleCursorAdapter.ViewBinder() {
            @Override
            public boolean setViewValue(View view, Cursor cursor, int columnIndex) {

                if (columnIndex == 1) {
                    String desc = cursor.getString(columnIndex);
                    TextView textView = (TextView) view;
                    //textView.setPadding(10,10,10,10);
                    textView.setTextSize(20);
                    textView.setText(desc);
                    return true;
                }
                return false;
            }
        });
        dropdown.setAdapter(sAdapter);

        dropdown.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivity2.this, "Selected ID=" + id, Toast.LENGTH_LONG).show();
                iid = id;
                Cursor value = (Cursor) dropdown.getItemAtPosition(position);
                CaptainName = value.getString(value.getColumnIndex("PlayerName"));
                txtCaptainNameText.setText(CaptainName);
                Toast.makeText(MainActivity2.this, "Name= " + CaptainName, Toast.LENGTH_LONG).show();
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });


        dropdownPartners._items = items.toArray(new String[]{String.valueOf(items)});
        Log.e("Partners", Arrays.toString(dropdownPartners._items));
        dropdownPartners.mSelection = new boolean[]{false, false, false, false, false, false, false, false};

        //c.close();

        //Toast.makeText(MainActivity2.this, "Name= " + , Toast.LENGTH_LONG).show();
        //dropdownPartners.setAdapter(adapter);

      /*  if (c.getCount() > 0) {
            while (c.moveToNext()) {
                String id = c.getString(c.getColumnIndex("Id"));
                String playername = c.getString(c.getColumnIndex("PlayerName"));
                //String points = c.getString(c.getColumnIndex("Points"));

                //dropdown.setAdapter(adapter);

              //  Player Player1 = new Player(id, playername);



            }

        }

       */

        //c.close();

        // Cursor value = (Cursor) dropdownPartners.getIt;
        //String PartnerName = value.getString(value.getColumnIndex("PlayerName"));
        //dropdownPartners._items = new String[]{PartnerName};
        //dropdownPartners.mSelection = new boolean[] {false};
        //Toast.makeText(MainActivity2.this, "Name= " + PartnerName, Toast.LENGTH_LONG).show();

        /*
        Intent ii = getIntent();
        Bundle b = ii.getExtras();
        //Bundle extras = getIntent().getExtras();
        if (b != null) {
            player1 = (String) b.get("Player1");
            player2 = (String) b.get("Player2");
            player3 = (String) b.get("Player3");
            player4 = (String) b.get("Player4");
            player5 = (String) b.get("Player5");
            player6 = (String) b.get("Player6");
            player7 = (String) b.get("Player7");
            player8 = (String) b.get("Player8");

            //Toast.makeText(this, player2, Toast.LENGTH_SHORT).show();
            //Logg.d("player2", player2);
        }


        if (!player1.equals("")) {
            items = new String[]{player1};
            dropdownPartners._items = new String[]{player1};
            //dropdownPartners.mSelection = new boolean[] {false};
            txtPlayer1.setVisibility(View.VISIBLE);
            txtPoints1.setVisibility(View.VISIBLE);
            txtPlayer1.setText(player1);
        }
        if (!player2.equals("")) {
            items = new String[]{player1, player2};
            dropdownPartners._items = new String[]{player1, player2};
            //dropdownPartners.mSelection = new boolean[] {};
            txtPlayer2.setVisibility(View.VISIBLE);
            txtPoints2.setVisibility(View.VISIBLE);
            txtPlayer2.setText(player2);
        }
        if (!player3.equals("")) {
            items = new String[]{player1, player2, player3};
            dropdownPartners._items = new String[]{player1, player2, player3};
            txtPlayer3.setVisibility(View.VISIBLE);
            txtPoints3.setVisibility(View.VISIBLE);
            txtPlayer3.setText(player3);
        }
        if (!player4.equals("")) {
            items = new String[]{player1, player2, player3, player4};
            dropdownPartners._items = new String[]{player1, player2, player3, player4};
            txtPlayer4.setVisibility(View.VISIBLE);
            txtPoints4.setVisibility(View.VISIBLE);
            txtPlayer4.setText(player4);
        }
        if (!player5.equals("")) {
            items = new String[]{player1, player2, player3, player4, player5};
            dropdownPartners._items = new String[]{player1, player2, player3, player4, player5};
            txtPlayer5.setVisibility(View.VISIBLE);
            txtPoints5.setVisibility(View.VISIBLE);
            txtPlayer5.setText(player5);
        }
        if (!player6.equals("")) {
            items = new String[]{player1, player2, player3, player4, player5, player6};
            dropdownPartners._items = new String[]{player1, player2, player3, player4, player5, player6};
            txtPlayer6.setVisibility(View.VISIBLE);
            txtPoints6.setVisibility(View.VISIBLE);
            txtPlayer6.setText(player6);
        }
        if (!player7.equals("")) {
            items = new String[]{player1, player2, player3, player4, player5, player6, player7};
            dropdownPartners._items = new String[]{player1, player2, player3, player4, player5, player6, player7};
            txtPlayer7.setVisibility(View.VISIBLE);
            txtPoints7.setVisibility(View.VISIBLE);
            txtPlayer7.setText(player7);
        }
        if (!player8.equals("")) {
            items = new String[]{player1, player2, player3, player4, player5, player6, player7, player8};
            dropdownPartners._items = new String[]{player1, player2, player3, player4, player5, player6, player7, player8};
            txtPlayer8.setVisibility(View.VISIBLE);
            txtPoints8.setVisibility(View.VISIBLE);
            txtPlayer8.setText(player8);
        }
         */

        //ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        //dropdown.setAdapter(adapter);
        // dropdown.setOnItemSelectedListener(this);

        //String[] suggestions = new String[]{"Tortilla Chips", "Melted Cheese", "Salsa", "Guacamole", "Mexico", "Jalapeno"};
        //ArrayAdapter<String> adapter1 = new ArrayAdapter<>(this, android.R.layout.simple_dropdown_item_1line, suggestions);

        Button btnPartner = (Button) findViewById(R.id.btnPartner);
        btnPartner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s = dropdownPartners.getSelectedItemsAsString();
                String[] partners = s.split(",[  ]*");
                Log.e("TAGP", Arrays.toString(partners));

                if (partners.length == 1) {
                    partner1 = partners[0];
                    txtPartner1.setVisibility(View.VISIBLE);
                    txtPartner1.setText(partner1);
                    totalPartners = 1;
                } else if (partners.length == 2) {
                    partner1 = partners[0];
                    partner2 = partners[1];
                    txtPartner1.setVisibility(View.VISIBLE);
                    txtPartner2.setVisibility(View.VISIBLE);
                    txtPartner1.setText(partner1);
                    txtPartner2.setText(partner2);
                    totalPartners = 2;
                } else if (partners.length == 3) {
                    partner1 = partners[0];
                    partner2 = partners[1];
                    partner3 = partners[2];
                    txtPartner1.setVisibility(View.VISIBLE);
                    txtPartner2.setVisibility(View.VISIBLE);
                    txtPartner3.setVisibility(View.VISIBLE);
                    txtPartner1.setText(partner1);
                    txtPartner2.setText(partner2);
                    txtPartner3.setText(partner3);
                    totalPartners = 3;
                } else {
                    Toast.makeText(MainActivity2.this, "Maximum 3 Partners", Toast.LENGTH_SHORT).show();
                }
                Log.e("TAG", partner1 + " " + partner2 + " " + partner3);
            }
        });

        btnWon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wonn();
                partner1 = null;
                partner2 = null;
                partner3 = null;
                CaptainName = null;
                //points = 0;
                Intent intent = getIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                startActivity(intent);
                // points = player.getPoints();
            }
        });

        btnLoss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                losss();
                partner1 = null;
                partner2 = null;
                partner3 = null;
                CaptainName = null;
                //points = 0;
                Intent intent = getIntent();
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                finish();
                startActivity(intent);
            }
        });

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                //Yes button clicked
                                db.deleteAll();
                                Intent i = new Intent(MainActivity2.this, MainActivity.class);
                                applicationManager.SetSession("");
                                startActivity(i);
                                finish();
                                break;

                            case DialogInterface.BUTTON_NEGATIVE:
                                //No button clicked
                                dialog.dismiss();
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity2.this);
                builder.setTitle("Confirm");
                builder.setMessage("Are you sure?").setPositiveButton("Yes", dialogClickListener)
                        .setNegativeButton("No", dialogClickListener).show();
            }
        });
    }

    public void wonn() {
        //Cursor c1 = db.selectPlayerData();
        LP = new ArrayList<String>();
        bid = Integer.parseInt(String.valueOf(edtBidAmount.getText()));
        CaptainName = String.valueOf(txtCaptainNameText.getText());

        partner1 = txtPartner1.getText().toString();
        partner2 = txtPartner2.getText().toString();
        partner3 = txtPartner3.getText().toString();

        Cursor c1 = db.selectPlayerData();
        if (c1.getCount() > 0) {
            while (c1.moveToNext()) {
                id = c1.getString(c1.getColumnIndex("id"));
                Players = c1.getString(c1.getColumnIndex("PlayerName"));
                points = c1.getInt(c1.getColumnIndex("Points"));
            }

            //Integer P = player.getPoints();
            points = points + 2 * bid;
            // player.setPoints(points);
            //Logg.e("c1", String.valueOf(points));
            boolean b1 = db.updatePlayerData(String.valueOf(iid), CaptainName, points);
            if (b1 == true) {
                Toast.makeText(MainActivity2.this, "Data Updated....", Toast.LENGTH_SHORT).show();
                //Logg.e("Captain Name and Point:", String.valueOf(CaptainName + ", " + (2 * bid)));
                LCaptainName = String.valueOf(CaptainName + ": " + (2 * bid));
                //Logg.e("LCapatinName:", String.valueOf(LCaptainName));
                LP.add(LCaptainName);
            }

        }

        Cursor cp1 = db.selectPartner1Data();
        if (cp1.getCount() > 0) {
            while (cp1.moveToNext()) {
                id = cp1.getString(cp1.getColumnIndex("id"));
                Players = cp1.getString(cp1.getColumnIndex("PlayerName"));
                points = cp1.getInt(cp1.getColumnIndex("Points"));
            }
            points = points + bid;
            //Logg.e("cp1", String.valueOf(points));
            boolean b1 = db.updatePartnerData(id, partner1, points);
            if (b1 == true) {
                Toast.makeText(MainActivity2.this, "Data Updated....", Toast.LENGTH_SHORT).show();
                //Logg.e("Partner1 and Point:", String.valueOf(partner1 + ", " + bid));
                Lpartner1 = String.valueOf(partner1 + ": " + bid);
                //Logg.e("LP1:",Lpartner1);
                LP.add(Lpartner1);
            }
        }

        Cursor cp2 = db.selectPartner2Data();
        if (cp2.getCount() > 0) {
            while (cp2.moveToNext()) {
                id = cp2.getString(cp2.getColumnIndex("id"));
                Players = cp2.getString(cp2.getColumnIndex("PlayerName"));
                points = cp2.getInt(cp2.getColumnIndex("Points"));
            }
            points = points + bid;
            //Logg.e("cp2", String.valueOf(points));
            boolean b1 = db.updatePartnerData(id, partner2, points);
            if (b1 == true) {
                Toast.makeText(MainActivity2.this, "Data Updated....", Toast.LENGTH_SHORT).show();
                //Logg.e("Partner2 and Point:", String.valueOf(partner2 + ", " + bid));
                Lpartner2 = String.valueOf(partner2 + ": " + bid);
                //Logg.e("LP2:" , Lpartner2);
                LP.add(Lpartner2);
            }
        }

        Cursor cp3 = db.selectPartner3Data();
        if (cp3.getCount() > 0) {
            while (cp3.moveToNext()) {
                id = cp3.getString(cp3.getColumnIndex("id"));
                Players = cp3.getString(cp3.getColumnIndex("PlayerName"));
                points = cp3.getInt(cp3.getColumnIndex("Points"));
            }
            points = points + bid;
            //Logg.e("cp3", String.valueOf(points));
            boolean b1 = db.updatePartnerData(id, partner3, points);
            if (b1 == true) {
                Toast.makeText(MainActivity2.this, "Data Updated....", Toast.LENGTH_SHORT).show();
                //Logg.e("Partner3 and Point:", String.valueOf(partner3 + ", " + bid));
                Lpartner3 = String.valueOf(partner3+ ": " + bid);
                //Logg.e("LP3:", Lpartner3);
                LP.add(Lpartner3);
            }
        }
        Log.e("Lplayers:", String.valueOf(LP));
        db.insertData(String.valueOf(LP));
    }

    public void losss() {
        LP = new ArrayList<String>();
        //Cursor c1 = db.selectPlayerData();
        bid = Integer.parseInt(String.valueOf(edtBidAmount.getText()));
        CaptainName = String.valueOf(txtCaptainNameText.getText());
        Cursor c1 = db.selectPlayerData();
        if (c1.getCount() > 0) {
            while (c1.moveToNext()) {
                id = c1.getString(c1.getColumnIndex("id"));
                Players = c1.getString(c1.getColumnIndex("PlayerName"));
                points = c1.getInt(c1.getColumnIndex("Points"));
            }
            //Integer P = player.getPoints();
            points = points - bid;
            // player.setPoints(points);
            //Logg.e("TAG", String.valueOf(points));
            boolean b1 = db.updatePlayerData(String.valueOf(iid), CaptainName, points);
            if (b1 == true) {
                Toast.makeText(MainActivity2.this, "Data Updated....", Toast.LENGTH_SHORT).show();
                //Logg.e("CaptainName and Point:", String.valueOf(CaptainName + ", " + (-bid)));
                LCaptainName = String.valueOf(CaptainName + ": " + (-bid));
                //Logg.e("LCaptaionName:", LCaptainName);
                LP.add(LCaptainName);
            }
        }

        Cursor c = db.selectData();
        if (c.getCount() > 0) {
            while (c.moveToNext()) {
                id = c.getString(c.getColumnIndex("_id"));
                Players = c.getString(c.getColumnIndex("PlayerName"));
                points = c.getInt(c.getColumnIndex("Points"));

                if (!Players.equals(CaptainName)) {
                    if (!Players.equals(partner1)) {
                        if (!Players.equals(partner2)) {
                            if (!Players.equals(partner3)) {
                                //Logg.e("c", String.valueOf(points));
                                points = points + bid;
                                boolean b1 = db.updatePlayerData(id, Players, points);
                                //Logg.e("Players and Point:", String.valueOf(Players + ", " + bid));
                                String LPlayers = String.valueOf(Players + ": " + bid);
                                LP.add(LPlayers);
                            }
                        }
                    }
                }
            }
        }
        Log.e("Lplayers:", String.valueOf(LP));
        db.insertData(String.valueOf(LP));
    }

    public class dbHelper extends SQLiteOpenHelper {
        // public static final String Databasename = "kali2.db";
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
            //db.execSQL("DROP TABLE IF EXISTS player");
            onCreate(db);
        }


        public Cursor selectData() {

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c = db.rawQuery("select id as _id, PlayerName, Points from player", null);
            //c.moveToFirst();
            Log.e("TAG2", String.valueOf(c));
            return c;
        }

        public Cursor selectLog() {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor clog = db.rawQuery("select id as _id, logg from log", null);
            //c.moveToFirst();
            Log.e("TAG2", String.valueOf(clog));
            return clog;
        }

        public Cursor selectPlayerData() {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c1 = db.rawQuery("select * from player where id = '" + iid + "'", null);
            return c1;
        }

        public Cursor selectPartner1Data() {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cp1 = db.rawQuery("select * from player where PlayerName = '" + partner1 + "'", null);
            return cp1;
        }

        public Cursor selectPartner2Data() {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cp2 = db.rawQuery("select * from player where PlayerName = '" + partner2 + "'", null);
            return cp2;
        }

        public Cursor selectPartner3Data() {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cp3 = db.rawQuery("select * from player where PlayerName = '" + partner3 + "'", null);
            return cp3;
        }

        public boolean updatePlayerData(String Id, String playerName, int points) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            String updateQ = "update player set Points='" + points + "' where PlayerName='" + playerName + "' and id = '" + Id + "'";
            db.execSQL(updateQ);
            return true;
        }

        public boolean updatePartnerData(String Id, String partnerName, int points) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            String updateQ = "update player set Points='" + points + "' where PlayerName='" + partnerName + "' and id = '" + Id + "'";
            db.execSQL(updateQ);
            return true;
        }

        public void deleteAll() {
            SQLiteDatabase db = this.getReadableDatabase();
            // db.delete("player", null, null);
            String clearQ = "DROP TABLE IF EXISTS player";
            String clearLog = "DROP TABLE IF EXISTS log";
            //String clearQ = "DELETE from player";
            db.execSQL(clearQ);
            db.execSQL(clearLog);
            db.execSQL("VACUUM");
            //return true;
            onCreate(db);
        }

        public boolean insertData(String Log) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            cv.put("Logg", Log);

            long rs = db.insert("log", null, cv);
            if (rs == -1) {
                return false;
            } else {
                return true;
            }
        }
    }
}