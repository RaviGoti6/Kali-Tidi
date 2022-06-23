package com.example.kalitidi;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PlayerAdapter extends ArrayAdapter<Player> {

    private Context _context;
    LinearLayout lnly;


    public PlayerAdapter(@NonNull Context context, ArrayList<Player> Player) {
        super(context, 0, Player);
        this._context = context;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Player player = (Player) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listitem, parent, false);

        }

        lnly = (LinearLayout) convertView.findViewById(R.id.lnly);
        TextView txtId = (TextView) convertView.findViewById(R.id.txtId);
        TextView txtPlayer = (TextView) convertView.findViewById(R.id.txtplayer);
        TextView txtxPoints = (TextView) convertView.findViewById(R.id.txtPoints);

        txtId.setText(player.Id);
        txtPlayer.setText(player.PlayerName);
        txtxPoints.setText(String.valueOf(player.Points));

        lnly.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(_context, "Selected:" + player.PlayerName, Toast.LENGTH_LONG).show();
                //     Intent ii = new Intent(_context, profile.class);
                //    ii.putExtra("Id", user.Id);
                //   _context.startActivity(ii);
            }
        });


        return convertView;
    }


}
