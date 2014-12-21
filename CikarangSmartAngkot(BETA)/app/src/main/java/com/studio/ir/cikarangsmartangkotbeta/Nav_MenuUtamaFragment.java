package com.studio.ir.cikarangsmartangkotbeta;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Nav_MenuUtamaFragment extends Fragment {
    Context context; //Declare the variable context

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            //Pass your layout xml to the inflater and assign it to rootView.
            View rootView = inflater.inflate(R.layout.menu, container, false);
            context = rootView.getContext(); // Assign your rootView to context

            Button angkot = (Button) rootView.findViewById(R.id.button1);
            angkot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent
                    Intent intent = new Intent(context, Menu_RuteAngkot.class);
                    startActivity(intent);
                }
            });

            Button kode = (Button) rootView.findViewById(R.id.button2);
            kode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent
                    Intent intent = new Intent(context, ActBar_NoBoringActionBarActivity.class);
                    startActivity(intent);
                }
            });

            Button portal = (Button) rootView.findViewById(R.id.button3);
            portal.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Pass the context and the Activity class you need to open from the Fragment Class, to the Intent
                    Intent intent = new Intent(context, News_SplashActivity.class);
                    startActivity(intent);
                }
            });


            return rootView;
        }
    }