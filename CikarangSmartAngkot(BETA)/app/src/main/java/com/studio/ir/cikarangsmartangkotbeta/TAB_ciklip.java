package com.studio.ir.cikarangsmartangkotbeta;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class TAB_ciklip extends TabActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tab);

        
        TabHost tabHost = getTabHost();
        
        TabSpec rute = tabHost.newTabSpec("RUTE");
        rute.setIndicator("RUTE", getResources().getDrawable(R.drawable.style_arch));
        Intent getrute = new Intent(this, DATA_ciklip.class);
        rute.setContent(getrute);
        
        
        TabSpec peta = tabHost.newTabSpec("PETA");
        peta.setIndicator("PETA", getResources().getDrawable(R.drawable.style_mandriva));
        Intent getpeta = new Intent(this, Maps_Path_Ciklip.class);
        peta.setContent(getpeta);
        

        tabHost.addTab(rute);
        tabHost.addTab(peta);

        for (int i=0;i<tabHost.getTabWidget().getChildCount(); i++){
            TextView tv = (TextView)
                    tabHost.getTabWidget().getChildAt(i).findViewById(android.R.id.title);
            tv.setTextColor(Color.parseColor("#FFFFFF"));

        }


    }
}