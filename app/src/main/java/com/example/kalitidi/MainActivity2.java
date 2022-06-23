package com.example.kalitidi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cursoradapter.widget.SimpleCursorAdapter;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
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
import java.util.List;

public class MainActivity2 extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    TextView txtPlayer1, txtPlayer2, txtPlayer3, txtPlayer4, txtPlayer5, txtPlayer6, txtPlayer7, txtPlayer8;
    TextView txtPoints1, txtPoints2, txtPoints3, txtPoints4, txtPoints5, txtPoints6, txtPoints7, txtPoints8;
    TextView txtCaptainNameText;
    TextView txtPartner1, txtPartner2, txtPartner3;
    String player1 = "", player2 = "", player3 = "", player4 = "", player5 = "", player6 = "", player7 = "", player8 = "";
    int points1 = 0, points2 = 0, points3 = 0, points4 = 0, points5 = 0, points6 = 0, points7 = 0, points8 = 0;
    String partner1, partner2, partner3;
    String id, Players;
    long iid;
    Integer points;
    //String[] items;
    ArrayList<String> items;
    Player player;

    int bid;
    String CaptainName;
    EditText edtBidAmount;
    Button btnWon, btnLoss;
    MultiSelectionSpinner dropdownPartners;
    dbHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        txtCaptainNameText = (TextView) findViewById(R.id.txtCaptainNameText);
        btnWon = (Button) findViewById(R.id.btnWon);
        btnLoss = (Button) findViewById(R.id.btnLoss);

        ListView listView = (ListView) findViewById(R.id.lvPlayer);

        db = new dbHelper(this);

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
                //Log.e("TAG", Players);
                items.add(Players);

                listView.setAdapter(adapter);
                Player Player1 = new Player(id, Players, points);
                adapter.add(Player1);

            }
        }

        //Log.e("TAG", String.valueOf(c));
        SimpleCursorAdapter sAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_spinner_item, c, from, to, 0);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
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
            //Log.d("player2", player2);
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
                String[] partners = s.split(",[ ]*");
                Log.e("TAG", Arrays.toString(partners));

                if (partners.length == 1) {
                    partner1 = partners[0];
                    txtPartner1.setVisibility(View.VISIBLE);
                    txtPartner1.setText(partner1);
                } else if (partners.length == 2) {
                    partner1 = partners[0];
                    partner2 = partners[1];
                    txtPartner1.setVisibility(View.VISIBLE);
                    txtPartner2.setVisibility(View.VISIBLE);
                    txtPartner1.setText(partner1);
                    txtPartner2.setText(partner2);
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
    }

    public void wonn() {
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
        }
        //Integer P = player.getPoints();
        points = points + 2 * bid;
        // player.setPoints(points);
        Log.e("TAG", String.valueOf(points));
        boolean b1 = db.updatePlayerData(String.valueOf(iid), CaptainName, points);
        if (b1 == true) {
            Toast.makeText(MainActivity2.this, "Data Updated....", Toast.LENGTH_SHORT).show();
        }
    }

    public void losss() {
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
        }
        //Integer P = player.getPoints();
        points = points - bid;
        // player.setPoints(points);
        Log.e("TAG", String.valueOf(points));
        boolean b1 = db.updatePlayerData(String.valueOf(iid), CaptainName, points);
        if (b1 == true) {
            Toast.makeText(MainActivity2.this, "Data Updated....", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                txtCaptainNameText.setText(player1);
                //Toast.makeText(this, player1, Toast.LENGTH_SHORT).show();
                break;
            case 1:
                txtCaptainNameText.setText(player2);
                //Log.d("player2", player2);
                Toast.makeText(this, player2, Toast.LENGTH_SHORT).show();
                break;
            case 2:
                txtCaptainNameText.setText(player3);
                Toast.makeText(this, player3, Toast.LENGTH_SHORT).show();
                break;
            case 3:
                txtCaptainNameText.setText(player4);
                Toast.makeText(this, player4, Toast.LENGTH_SHORT).show();
                break;
            case 4:
                txtCaptainNameText.setText(player5);
                Toast.makeText(this, player5, Toast.LENGTH_SHORT).show();
                break;
            case 5:
                txtCaptainNameText.setText(player6);
                Toast.makeText(this, player6, Toast.LENGTH_SHORT).show();
                break;
            case 6:
                txtCaptainNameText.setText(player7);
                Toast.makeText(this, player7, Toast.LENGTH_SHORT).show();
                break;
            case 7:
                txtCaptainNameText.setText(player8);
                Toast.makeText(this, player8, Toast.LENGTH_SHORT).show();
                break;
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void won() {
        bid = Integer.parseInt(String.valueOf(edtBidAmount.getText()));
        CaptainName = String.valueOf(txtCaptainNameText.getText());
        if (CaptainName.equals(player1)) {
            points1 = points1 + (2 * bid);
            Log.e("TAG", String.valueOf(points1));
            txtPoints1.setText(String.valueOf(points1));

            if (partner1 != null) {
                if (partner1.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (partner1.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (partner1.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (partner1.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (partner1.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (partner1.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (partner1.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (partner1.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner2 != null) {
                if (partner2.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (partner2.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (partner2.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (partner2.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (partner2.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (partner2.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (partner2.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (partner2.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner3 != null) {
                if (partner3.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (partner3.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (partner3.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (partner3.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (partner3.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (partner3.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (partner3.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (partner3.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }

        } else if (CaptainName.equals(player2)) {
            points2 = points2 + (2 * bid);
            txtPoints2.setText(String.valueOf(points2));

            if (partner1 != null) {
                if (partner1.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (partner1.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (partner1.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (partner1.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (partner1.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (partner1.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (partner1.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (partner1.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner2 != null) {
                if (partner2.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (partner2.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (partner2.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (partner2.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (partner2.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (partner2.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (partner2.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (partner2.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner3 != null) {
                if (partner3.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (partner3.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (partner3.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (partner3.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (partner3.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (partner3.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (partner3.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (partner3.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
        } else if (CaptainName.equals(player3)) {
            points3 = points3 + (2 * bid);
            txtPoints3.setText(String.valueOf(points3));

            if (partner1 != null) {
                if (partner1.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (partner1.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (partner1.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (partner1.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (partner1.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (partner1.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (partner1.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (partner1.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner2 != null) {
                if (partner2.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (partner2.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (partner2.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (partner2.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (partner2.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (partner2.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (partner2.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (partner2.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner3 != null) {
                if (partner3.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (partner3.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (partner3.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (partner3.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (partner3.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (partner3.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (partner3.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (partner3.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
        } else if (CaptainName.equals(player4)) {
            points4 = points4 + (2 * bid);
            txtPoints4.setText(String.valueOf(points4));

            if (partner1 != null) {
                if (partner1.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (partner1.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (partner1.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (partner1.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (partner1.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (partner1.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (partner1.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (partner1.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner2 != null) {
                if (partner2.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (partner2.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (partner2.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (partner2.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (partner2.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (partner2.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (partner2.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (partner2.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner3 != null) {
                if (partner3.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (partner3.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (partner3.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (partner3.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (partner3.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (partner3.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (partner3.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (partner3.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
        } else if (CaptainName.equals(player5)) {
            points5 = points5 + (2 * bid);
            txtPoints5.setText(String.valueOf(points5));

            if (partner1 != null) {
                if (partner1.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (partner1.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (partner1.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (partner1.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (partner1.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (partner1.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (partner1.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (partner1.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner2 != null) {
                if (partner2.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (partner2.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (partner2.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (partner2.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (partner2.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (partner2.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (partner2.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (partner2.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner3 != null) {
                if (partner3.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (partner3.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (partner3.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (partner3.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (partner3.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (partner3.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (partner3.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (partner3.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
        } else if (CaptainName.equals(player6)) {
            points6 = points6 + (2 * bid);
            txtPoints6.setText(String.valueOf(points6));

            if (partner1 != null) {
                if (partner1.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (partner1.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (partner1.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (partner1.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (partner1.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (partner1.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (partner1.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (partner1.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner2 != null) {
                if (partner2.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (partner2.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (partner2.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (partner2.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (partner2.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (partner2.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (partner2.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (partner2.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner3 != null) {
                if (partner3.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (partner3.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (partner3.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (partner3.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (partner3.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (partner3.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (partner3.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (partner3.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
        } else if (CaptainName.equals(player7)) {
            points7 = points7 + (2 * bid);
            txtPoints7.setText(String.valueOf(points7));

            if (partner1 != null) {
                if (partner1.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (partner1.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (partner1.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (partner1.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (partner1.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (partner1.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (partner1.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (partner1.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner2 != null) {
                if (partner2.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (partner2.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (partner2.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (partner2.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (partner2.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (partner2.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (partner2.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (partner2.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner3 != null) {
                if (partner3.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (partner3.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (partner3.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (partner3.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (partner3.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (partner3.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (partner3.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (partner3.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
        } else if (CaptainName.equals(player8)) {
            points8 = points8 + (2 * bid);
            txtPoints8.setText(String.valueOf(points8));

            if (partner1 != null) {
                if (partner1.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (partner1.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (partner1.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (partner1.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (partner1.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (partner1.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (partner1.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (partner1.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner2 != null) {
                if (partner2.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (partner2.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (partner2.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (partner2.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (partner2.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (partner2.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (partner2.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (partner2.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner3 != null) {
                if (partner3.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (partner3.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (partner3.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (partner3.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (partner3.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (partner3.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (partner3.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (partner3.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
        }
    }

    public void loss() {
        bid = Integer.parseInt(String.valueOf(edtBidAmount.getText()));
        CaptainName = String.valueOf(txtCaptainNameText.getText());
        if (CaptainName.equals(player1)) {
            points1 = points1 - bid;
            txtPoints1.setText(String.valueOf(points1));

            if (partner1 != null) {
                if (!partner1.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (!partner1.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (!partner1.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (!partner1.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (!partner1.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (!partner1.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (!partner1.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner2 != null) {
                if (!partner2.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (!partner2.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (!partner2.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (!partner2.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (!partner2.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (!partner2.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (!partner2.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner3 != null) {
                if (!partner3.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (!partner3.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (!partner3.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (!partner3.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (!partner3.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (!partner3.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (!partner3.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
        } else if (CaptainName.equals(player2)) {
            points2 = points2 - bid;
            //txtPoints2.setText(String.valueOf(points2));

            if (partner1 != null) {
                if (!partner1.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (!partner1.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (!partner1.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (!partner1.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (!partner1.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (!partner1.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (!partner1.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner2 != null) {
               /* if (!partner2.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else*/
                if (!partner2.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (!partner2.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (!partner2.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (!partner2.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (!partner2.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (!partner2.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner3 != null) {
                /*if (!partner3.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.value Of(points1));
                } else if (!partner3.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else */
                if (!partner3.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (!partner3.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (!partner3.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (!partner3.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (!partner3.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }

         /*   if (partner1 != null) {
                if (partner1.equals(player1)) {
                    if (partner1.equals(player3)) {
                        if (partner1.equals(player4)) {
                            if (partner1.equals(player5)) {
                                if (partner1.equals(player6)) {
                                    if (partner1.equals(player7)) {
                                        if (partner1.equals(player8)) {
                                            points1 = points1;
                                            points3 = points3;
                                            points4 = points4;
                                            points5 = points5;
                                            points6 = points6;
                                            points7 = points7;
                                            points8 = points8;
                                        } else points8 = points8 + bid;
                                    } else points7 = points7 + bid;
                                } else points6 = points6 + bid;
                            } else points5 = points5 + bid;
                        } else points4 = points4 + bid;
                    } else points3 = points3 + bid;
                } else points1 = points1 + bid;
            }
            if (partner2 != null) {
                if (partner2.equals(player1)) {
                    if (partner2.equals(player3)) {
                        if (partner2.equals(player4)) {
                            if (partner2.equals(player5)) {
                                if (partner2.equals(player6)) {
                                    if (partner2.equals(player7)) {
                                        if (partner2.equals(player8)) {
                                            points1 = points1;
                                            points3 = points3;
                                            points4 = points4;
                                            points5 = points5;
                                            points6 = points6;
                                            points7 = points7;
                                            points8 = points8;
                                        } else points8 = points8 + bid;
                                    } else points7 = points7 + bid;
                                } else points6 = points6 + bid;
                            } else points5 = points5 + bid;
                        } else points4 = points4 + bid;
                    } else points3 = points3 + bid;
                } else points1 = points1 + bid;
            }
            if (partner3 != null) {
                if (partner3.equals(player1)) {
                    if (partner3.equals(player3)) {
                        if (partner3.equals(player4)) {
                            if (partner3.equals(player5)) {
                                if (partner3.equals(player6)) {
                                    if (partner3.equals(player7)) {
                                        if (partner3.equals(player8)) {
                                            points1 = points1;
                                            points3 = points3;
                                            points4 = points4;
                                            points5 = points5;
                                            points6 = points6;
                                            points7 = points7;
                                            points8 = points8;
                                        } else points8 = points8 + bid;
                                    } else points7 = points7 + bid;
                                } else points6 = points6 + bid;
                            } else points5 = points5 + bid;
                        } else points4 = points4 + bid;
                    } else points3 = points3 + bid;
                } else points1 = points1 + bid;
            } */

            txtPoints1.setText(String.valueOf(points1));
            txtPoints2.setText(String.valueOf(points2));
            txtPoints3.setText(String.valueOf(points3));
            txtPoints4.setText(String.valueOf(points4));
            txtPoints5.setText(String.valueOf(points5));
            txtPoints6.setText(String.valueOf(points6));
            txtPoints7.setText(String.valueOf(points7));
            txtPoints8.setText(String.valueOf(points8));

        } else if (CaptainName.equals(player3)) {
            points3 = points3 - bid;
            txtPoints3.setText(String.valueOf(points3));

            if (partner1 != null) {
                if (!partner1.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (!partner1.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (!partner1.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (!partner1.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (!partner1.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (!partner1.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (!partner1.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner2 != null) {
                if (!partner2.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (!partner2.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (!partner2.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (!partner2.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (!partner2.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (!partner2.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (!partner2.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner3 != null) {
                if (!partner3.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (!partner3.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (!partner3.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (!partner3.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (!partner3.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (!partner3.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (!partner3.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
        } else if (CaptainName.equals(player4)) {
            points4 = points4 - bid;
            txtPoints4.setText(String.valueOf(points4));

            if (partner1 != null) {
                if (!partner1.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (!partner1.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (!partner1.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (!partner1.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (!partner1.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (!partner1.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (!partner1.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner2 != null) {
                if (!partner2.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (!partner2.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (!partner2.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (!partner2.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (!partner2.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (!partner2.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (!partner2.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner3 != null) {
                if (!partner3.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (!partner3.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (!partner3.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (!partner3.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (!partner3.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (!partner3.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (!partner3.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
        } else if (CaptainName.equals(player5)) {
            points5 = points5 - bid;
            txtPoints5.setText(String.valueOf(points5));

            if (partner1 != null) {
                if (!partner1.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (!partner1.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (!partner1.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (!partner1.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (!partner1.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (!partner1.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (!partner1.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner2 != null) {
                if (!partner2.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (!partner2.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (!partner2.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (!partner2.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (!partner2.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (!partner2.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (!partner2.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner3 != null) {
                if (!partner3.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (!partner3.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (!partner3.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (!partner3.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints5.setText(String.valueOf(points2));
                } else if (!partner3.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (!partner3.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (!partner3.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
        } else if (CaptainName.equals(player6)) {
            points6 = points6 - bid;
            txtPoints6.setText(String.valueOf(points6));

            if (partner1 != null) {
                if (!partner1.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (!partner1.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (!partner1.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (!partner1.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (!partner1.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (!partner1.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (!partner1.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner2 != null) {
                if (!partner2.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (!partner2.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (!partner2.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (!partner2.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (!partner2.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (!partner2.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (!partner2.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner3 != null) {
                if (!partner3.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (!partner3.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (!partner3.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (!partner3.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (!partner3.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (!partner3.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (!partner3.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
        } else if (CaptainName.equals(player7)) {
            points7 = points7 - bid;
            txtPoints7.setText(String.valueOf(points7));

            if (partner1 != null) {
                if (!partner1.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (!partner1.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (!partner1.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (!partner1.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (!partner1.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (!partner1.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (!partner1.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner2 != null) {
                if (!partner2.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (!partner2.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (!partner2.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (!partner2.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (!partner2.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (!partner2.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (!partner2.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
            if (partner3 != null) {
                if (!partner3.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (!partner3.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (!partner3.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (!partner3.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (!partner3.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (!partner3.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                } else if (!partner3.equals(player8)) {
                    points8 = points8 + bid;
                    txtPoints8.setText(String.valueOf(points8));
                }
            }
        } else if (CaptainName.equals(player8)) {
            points8 = points8 - bid;
            txtPoints8.setText(String.valueOf(points8));

            if (partner1 != null) {
                if (!partner1.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (!partner1.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (!partner1.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (!partner1.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (!partner1.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (!partner1.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (!partner1.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                }
            }
            if (partner2 != null) {
                if (!partner2.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (!partner2.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (!partner2.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (!partner2.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (!partner2.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (!partner2.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (!partner2.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                }
            }
            if (partner3 != null) {
                if (!partner3.equals(player1)) {
                    points1 = points1 + bid;
                    txtPoints1.setText(String.valueOf(points1));
                } else if (!partner3.equals(player3)) {
                    points3 = points3 + bid;
                    txtPoints3.setText(String.valueOf(points3));
                } else if (!partner3.equals(player4)) {
                    points4 = points4 + bid;
                    txtPoints4.setText(String.valueOf(points4));
                } else if (!partner3.equals(player5)) {
                    points5 = points5 + bid;
                    txtPoints5.setText(String.valueOf(points5));
                } else if (!partner3.equals(player6)) {
                    points6 = points6 + bid;
                    txtPoints6.setText(String.valueOf(points6));
                } else if (!partner3.equals(player7)) {
                    points7 = points7 + bid;
                    txtPoints7.setText(String.valueOf(points7));
                } else if (!partner3.equals(player2)) {
                    points2 = points2 + bid;
                    txtPoints2.setText(String.valueOf(points2));
                }
            }
        }
    }


    public class dbHelper extends SQLiteOpenHelper {
        public static final String Databasename = "kali2.db";

        public dbHelper(Context context) {
            super(context, Databasename, null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }


        public Cursor selectData() {

            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c = db.rawQuery("select id as _id, PlayerName, Points from player", null);
            //c.moveToFirst();
            Log.e("TAG2", String.valueOf(c));
            return c;
        }

        public Cursor selectPlayerData() {
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor c1 = db.rawQuery("select * from player where id = '" + iid + "'", null);
            return c1;
        }

        public boolean updatePlayerData(String Id, String playerName, int points) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues cv = new ContentValues();
            String updateQ = "update player set Points='" + points + "' where PlayerName='" + playerName + "' and id = '" + Id + "'";
            db.execSQL(updateQ);
            return true;
        }


    }
}