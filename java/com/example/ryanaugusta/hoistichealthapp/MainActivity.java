// Project:     Holistic Health App
// Author:      Ryan Augusta
// Date:        2018
// Desc:        This is the main activity, handles home page and navigation objects.

package com.example.ryanaugusta.hoistichealthapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SimpleCursorAdapter;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FilterQueryProvider;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Context context = this;
    private TextView mTextMessage;

    // set up the bottom navigation to switch fragments
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Log.i("MyStackFragment", "MyStackFragment Selected");

            //define fragment reference to hole selected fragment
            Fragment selectedFragment = null;
            String fragmentTag = null;

            switch (item.getItemId()) {
                case R.id.navigation_mystack:
                    selectedFragment = MyStackFragment.newInstance();
                    fragmentTag = "homeFrag";
                    break;
                case R.id.navigation_vitamin:
                    selectedFragment = VitaminFragment.newInstance();
                    fragmentTag = "vitaminFrag";
                    break;
                case R.id.navigation_supplement:
                    selectedFragment = SupplementFragment.newInstance();
                    fragmentTag = "supplementFrag";
                    break;
            }

            // replace the current fragment with the selected fragment
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.content, selectedFragment, fragmentTag);

            transaction.commit();
            return true;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = findViewById(R.id.message);
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        mTextMessage.setText("Welcome To the Holistic Health App!");
        mTextMessage.setTextSize(20);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }
}
