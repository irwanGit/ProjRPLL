package com.studio.ir.cikarangsmartangkotbeta;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
//import android.view.View.OnClickListener;
import android.widget.*;

public class Menu_RuteAngkot extends Activity
        implements AdapterView.OnItemSelectedListener {
    String[] items={"Cikarang","Tambun","Sukatani","Babelan","Jababeka","Lippo","Muara Gembong", "Pebayuran"};
    String[] items1={"Cibitung","Tambun","Sukatani","Babelan","Jababeka","Lippo","Muara Gembong", "Pebayuran"};


    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.menu_ruteangkot);

        final Spinner spin=(Spinner)findViewById(R.id.Spinner01);
        spin.setOnItemSelectedListener(this);
        final Spinner spin2=(Spinner)findViewById(R.id.spinner);
        spin2.setOnItemSelectedListener(this);

        ArrayAdapter<String>aa=new ArrayAdapter<String> (this,android.R.layout.simple_spinner_item,items);
        aa.setDropDownViewResource(
                android.R.layout.simple_dropdown_item_1line);
        spin.setAdapter(aa);


        ArrayAdapter<String>bb=new ArrayAdapter<String> (this,android.R.layout.simple_spinner_item,items1);
        bb.setDropDownViewResource(
                android.R.layout.simple_dropdown_item_1line);
        spin2.setAdapter(bb);


        final Button button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (spin.getSelectedItem().toString() == "Cikarang" && spin2.getSelectedItem().toString() == "Sukatani"){

                    Intent i = null;
                    i = new Intent(Menu_RuteAngkot.this, TAB_ciksuk.class);
                    startActivity(i);     }

                else if (spin.getSelectedItem().toString() == "Cikarang" && spin2.getSelectedItem().toString() == "Lippo"){

                    Intent i = null;
                    i = new Intent(Menu_RuteAngkot.this, TAB_ciklip.class);
                    startActivity(i);     }

                else Toast.makeText(getBaseContext(), "Data Sedang Dalam Penginputan, Tunggu Minggu Depan!", Toast.LENGTH_SHORT).show();

            }
        });


    }


    public void onItemSelected(AdapterView<?> parent,
                               View v, int position, long id) {

        // String selected = parent.getItemAtPosition(position).toString();

    }


    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

}
