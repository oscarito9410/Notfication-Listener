package com.oscar.notficacionlistener.UI;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.oscar.notficacionlistener.R;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    private BottomNavigationView mNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mNavigationView = (BottomNavigationView) findViewById(R.id.navigation);
        mNavigationView.setOnNavigationItemSelectedListener(this);
        pushFragment(FragmentHome.newInstance(), true);
    }


    private void pushFragment(Fragment ft, boolean addBackStack) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.ft, ft, ft.getClass().getName());
        if (addBackStack)
            transaction.addToBackStack(ft.getClass().getName());
        transaction.commitAllowingStateLoss();
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                pushFragment(FragmentHome.newInstance(), true);
                break;
            case R.id.navigation_dashboard:
                pushFragment(FragmentNews.newInstance(), true);
                break;
        }
        return true;
    }
}
