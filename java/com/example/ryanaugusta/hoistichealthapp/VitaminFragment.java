// Project:     Holistic Health App
// Author:      Ryan Augusta
// Date:        2018
// Desc:        This class handles the VitaminFragment
package com.example.ryanaugusta.hoistichealthapp;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/**
 * Created by ryanaugusta on 2/1/18.
 */

public class VitaminFragment extends Fragment implements SaveDialog.SaveConfirmDialogListener {

    private TextView msgTextView;
    private ListView lvVitamins;
    private DatabaseHandler dbh;
    private MyCursorAdapter adapter;

    private EditText etSearch;
    String keywords;

    private VitaminFragment frag;

    public static VitaminFragment newInstance() {
        VitaminFragment fragment = new VitaminFragment();
        return fragment;
    }

    // default constructor
    public VitaminFragment(){}

    // Called to create the view hierarchy associated with the fragment.
    // container from Activity holding the fragment.
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // inflate the xml layout file into the fragment, save the rootview
        View rootView = inflater.inflate(R.layout.vitamin_frag, container, false);
        lvVitamins = (ListView)rootView.findViewById(R.id.lvVitamins);
        etSearch = (EditText)rootView.findViewById(R.id.etSearch);

        // populate vitamin table
        DatabaseTest.populateVitaminTable(this.getContext());

        // create the database handler object
        dbh = new DatabaseHandler(this.getContext());

        // use the rootView and findViewById to create Java object tied to the resource
        msgTextView = (TextView)rootView.findViewById(R.id.msgTextView);
        msgTextView.setText("Vitamins");
        lvVitamins = (ListView)rootView.findViewById(R.id.lvVitamins);
        lvVitamins.setTextFilterEnabled(true);

        // use getActivity to get reference to the Activity if needed
        MainActivity ma = (MainActivity)getActivity();

        // create and set the cursorAdapter to the business listView
        adapter = createMyCursorAdapter();
        lvVitamins.setAdapter(adapter);

        frag = this;

        // search functionality in edit text.
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                keywords = etSearch.getText().toString();
                etSearch.setCompoundDrawables(null, null, getResources().getDrawable(R.drawable.ic_search_black_24dp), null);
                adapter.swapCursor(dbh.retrieveV(keywords));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        lvVitamins.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                TextView tvItemName = (TextView)view.findViewById(R.id.tvItemName);

                VitaminInfo vitaminInfo = dbh.getVitaminObjectByName(tvItemName.getText().toString());

                MyStackFragment myStackFragment = new MyStackFragment();
                Bundle bundle = new Bundle();
                bundle.putString("name", tvItemName.getText().toString());
                bundle.putString("info", vitaminInfo.getName());
                myStackFragment.setArguments(bundle);

                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                fragmentTransaction.show(myStackFragment);
                fragmentTransaction.add(R.id.content, myStackFragment, "myStackFrag");
                fragmentTransaction.commit();

                try {
                    //TextView tvItemName = view.findViewById(R.id.tvItemName);
                    // create a bundle object
                    Bundle args = new Bundle();

                    // add the name selected to the bundle with key name
                    args.putString("name", tvItemName.getText().toString());


                    DialogFragment newFragment = new SaveDialog();
                    newFragment.setTargetFragment(frag, 0);
                    // add the args bundle to the dialog fragment
                    newFragment.setArguments(args);

                    newFragment.show(getFragmentManager(), "save");
                } catch (Exception e) {

                }
            }
        });

        Log.i("Fragment", "Vitamin onCreateView");

        return rootView;
    }

    // create a cursorAdapter
    protected MyCursorAdapter createMyCursorAdapter()
    {
        // get new cursor from the database
        Cursor cursor = dbh.getAllVitaminObjects();

        // create the cursorAdapter and return it
        return new MyCursorAdapter(this.getContext(), cursor);
    }
    // lifecycle methods

    // Called when the fragment has been associated with the activity (the Activity is passed in here).
    @Override
    public void onAttach(Context context)
    {

        Log.i("Fragment", "Vitamin onAttach");
        super.onAttach(context);
    }

    // Called when the activity's onCreate() method has returned.
    @Override
    public void onActivityCreated (Bundle savedInstanceState)
    {
        Log.i("FragmentEx", "Vitamin onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    // Called when the view previously created by onCreateView(LayoutInflater,
    // ViewGroup, Bundle) has been detached from the fragment.
    @Override
    public void onDestroyView ()
    {
        Log.i("FragmentEx", "Vitamin onDestroyView");
        super.onDestroyView();
    }

    // Called when the fragment is no longer attached to its activity. This is called after onDestroy()
    @Override
    public void onDetach()
    {
        Log.i("Fragment", "Supplement onDetach");
        super.onDetach();
    }

    // Called when the Fragment is no longer resumed.
    // This is generally tied to Activity.onPause of the containing Activity's lifecycle.
    @Override
    public void onPause ()
    {
        Log.i("Fragment", "Supplement onPause");
        super.onPause();
    }

    // Called when the fragment is visible to the user and actively running.
    // This is generally tied to Activity.onResume of the containing Activity's lifecycle.
    @Override
    public void onResume ()
    {
        Log.i("Fragment", "Vitamin onResume");
        super.onResume();
    }

    // Called when the Fragment is visible to the user.
    // This is generally tied to Activity.onStart of the containing Activity's lifecycle.
    @Override
    public void onStart ()
    {
        Log.i("Fragment", "Supplement onStart");
        super.onStart();
    }

    // Called when the Fragment is no longer started.
    // This is generally tied to Activity.onStop of the containing Activity's lifecycle.
    @Override
    public void onStop ()
    {
        Log.i("Fragment", "Vitamin onStop");
        super.onStop();
    }

    @Override
    public void onDialogSaveNegativeClick()
    {

    }

    @Override
    public void onDialogSave(String name)
    {
        dbh.testMyStackAdd(name);
        adapter.swapCursor(dbh.getAllMyStackObjects());
        adapter.notifyDataSetChanged();
        Log.i("Saved", "instance Added from to fragment");
    }
}
