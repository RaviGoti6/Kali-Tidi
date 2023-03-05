package com.example.kalitidi;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class LogAdapter extends ArrayAdapter<Logg> {
    private Context _context;

    public LogAdapter(@NonNull Context context, ArrayList<Logg> Logg) {
        super(context, 0, Logg);
        this._context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        final Logg logg = (Logg) getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.logitem, parent, false);

        }

        TextView txtId = (TextView) convertView.findViewById(R.id.txtLID);
        TextView txtLog = (TextView) convertView.findViewById(R.id.txtLplayer);


        txtId.setText(Logg.Id);
        txtLog.setText(Logg.Logg);


        return convertView;
    }
}
