package com.studio.ir.cikarangsmartangkotbeta;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Nav_Bantuan extends Fragment {
    private DrawerLayout mDrawerLayout;

    public Nav_Bantuan() {
    }

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View rootView = inflater.inflate(R.layout.nav_bantuan, container, false);

        return rootView;

    }

}
