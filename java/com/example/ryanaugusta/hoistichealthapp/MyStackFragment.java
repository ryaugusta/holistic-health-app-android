// Project:     Holistic Health App
// Author:      Ryan Augusta
// Class:       MyStackFragment
// Description: This class handles the My Stack fragment.

package com.example.ryanaugusta.hoistichealthapp;

//import android.app.Fragment;
import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.IntDef;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

/**
 * Created by ryanaugusta on 2/1/18.
 */

public class MyStackFragment extends Fragment implements DeleteConfirmDialog.DeleteConfirmDialogListener,
        SaveDialog.SaveConfirmDialogListener
{
    // declare Java references
    private TextView msgTextView;
    private ListView lvMyStack;
    private DatabaseHandler dbh;
    private MyCursorAdapter addAdapter;
    private Button btnClear;


    // textView tied to the calendar
    private TextView tvStartDate;

    // floating action button for calendar
    private FloatingActionButton fab_calendar;

    private ArrayAdapter<String> adapter;

    private MyStackFragment frag;

    public static MyStackFragment newInstance()
    {
        MyStackFragment fragment = new MyStackFragment();
        return fragment;
    }

    // default constructor
    public MyStackFragment() {

    }

    // Called to create the view hierarchy associated with the fragment.
    // container from Activity holding the fragment.
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        // inflate the xml layout file into the fragment, save the rootview
        View rootView = inflater.inflate(R.layout.mystack_frag, container, false);

        // Get the main activity in case needed
        MainActivity ma = (MainActivity)getActivity();

        // use the rootView and findViewById to create Java object tied to the resource
        msgTextView = rootView.findViewById(R.id.msgTextView);
        msgTextView.setText("My Stack");
        fab_calendar = rootView.findViewById(R.id.fab_calendar);
        tvStartDate = rootView.findViewById(R.id.tvStartDate);
        btnClear = rootView.findViewById(R.id.btnClear);


        lvMyStack = rootView.findViewById(R.id.lvMystack);

        dbh = new DatabaseHandler(this.getContext());
        addAdapter = myCursorAdapter();
        lvMyStack.setAdapter(addAdapter);

        frag = this;

        // get bundle from the Supplement Fragment and display in the MyStack ListView
        Bundle bundle = this.getArguments();
        if (bundle != null)
        {
            adapter = stackAdapter(bundle.getString("name"));
            lvMyStack.setAdapter(adapter);
        }

        lvMyStack.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener()
        {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                // get the textViews inside the item in the listView
                TextView tvItemName = (TextView)view.findViewById(R.id.tvItemName);

                // create a bundle object
                Bundle args = new Bundle();

                // add the name selected to the bundle with key name
                args.putString("name", tvItemName.getText().toString());

                // create the DeleteDialog
                DialogFragment newFragment = new DeleteConfirmDialog();

                // set BusinessFragment as the target for the dialog
                newFragment.setTargetFragment(frag, 0);

                // add the args bundle to the dialog fragment
                newFragment.setArguments(args);

                // show it
                newFragment.show(getFragmentManager(), "delete");

                return true;
            }
        });


        // floating action button for calendar
        fab_calendar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                try
                {
                    // show the datePicker dialog
                    DialogFragment newFragment = new DatePickerFragment();
                    newFragment.setTargetFragment(frag, 0);
                    newFragment.show(getFragmentManager(), "datePicker");
                }
                catch (Exception e)
                {
                    System.out.println("Caught Exception For Calendar");
                }
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try
                {
                    clearAllEntries();
                }
                catch (Exception e) {
                    System.out.println("it didn't work");
                }
            }
        });

        Log.i("Fragment", "MyStack onCreateView");

        return rootView;
    }

    private ArrayAdapter<String> stackAdapter(String stack)
    {
        ArrayList<String> stackList = new ArrayList<>(Arrays.asList(stack));

        ArrayAdapter<String> stackAdapter = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_list_item_1, stackList);

        return stackAdapter;
    }

    protected MyCursorAdapter myCursorAdapter()
    {
        // get new cursor from the database
        Cursor cursor = dbh.getAllMyStackObjects();

        // create the cursorAdapter and return it
        return new MyCursorAdapter(this.getContext(), cursor);
    }

    // lifecycle methods
    // Called when the fragment has been associated with the activity (the Activity is passed in here).
    @Override
    public void onAttach(Context context)
    {
        Log.i("Fragment", "Home onAttach");
        super.onAttach(context);
    }

    // Called when the activity's onCreate() method has returned.
    @Override
    public void onActivityCreated (Bundle savedInstanceState)
    {
        Log.i("Fragment", "Home onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    // Called when the view previously created by onCreateView(LayoutInflater,
    // ViewGroup, Bundle) has been detached from the fragment.
    @Override
    public void onDestroyView ()
    {
        Log.i("FragmentEx", "Frag1 onDestroyView");
        super.onDestroyView();
    }

    // Called when the fragment is no longer attached to its activity. This is called after onDestroy()
    @Override
    public void onDetach()
    {
        Log.i("FragmentEx", "Frag1 onDetach");
        super.onDetach();
    }

    // Called when the Fragment is no longer resumed.
    // This is generally tied to Activity.onPause of the containing Activity's lifecycle.
    @Override
    public void onPause ()
    {
        Log.i("Fragment", "Home onPause");
        super.onPause();
    }

    // Called when the fragment is visible to the user and actively running.
    // This is generally tied to Activity.onResume of the containing Activity's lifecycle.
    @Override
    public void onResume ()
    {
        Log.i("Fragment", "Home onResume");
        super.onResume();
    }

    // Called when the Fragment is visible to the user.
    // This is generally tied to Activity.onStart of the containing Activity's lifecycle.
    @Override
    public void onStart ()
    {
        Log.i("Fragment", "Home onStart");
        super.onStart();
    }

    // Called when the Fragment is no longer started.
    // This is generally tied to Activity.onStop of the containing Activity's lifecycle.
    @Override
    public void onStop ()
    {
        Log.i("Fragment", "Home onStop");
        super.onStop();
    }

    @Override
    public void onDialogDeleteNegativeClick()
    {
        // do nothing
    }

    // delete dialogs
    @Override
    public void onDialogDelete(String name)
    {
    // get the data from database and get new cursor
        dbh.deleteMyStackByName(name);
        addAdapter.notifyDataSetChanged();

        // swap new cursor into cursorAdapter
        addAdapter.swapCursor(dbh.getAllMyStackObjects());
        System.out.println("delete selected");
    }

    @Override
    public void onDialogSaveNegativeClick()
    {

    }

    @Override
    public void onDialogSave(String name)
    {
        dbh.testMyStackAdd(name);
        addAdapter.swapCursor(dbh.getAllMyStackObjects());
        addAdapter.notifyDataSetChanged();
        Log.i("SaveDialog", "instance saved from fragment");
    }

    // method to clear all entries in the List
    public void clearAllEntries()
    {

        MainActivity ma = (MainActivity)getActivity();
        dbh.deleteMyStackObjects();
        addAdapter.swapCursor(dbh.getAllMyStackObjects());
        addAdapter.notifyDataSetChanged();
        Toast.makeText(ma, "List has been cleared", Toast.LENGTH_SHORT).show();

    }


    // Calendar Methods
    // set date using a calendar object to the textView
    // called from DatePickerFragment
    public void setDate(Calendar aDate)
    {
        //Examples for April 6, 1970 at 3:23am:
        //"MM/dd/yy h:mmaa" -> "04/06/70 3:23am"
        //"MMM dd, yyyy h:mmaa" -> "Apr 6, 1970 3:23am"
        //"MMMM dd, yyyy h:mmaa" -> "April 6, 1970 3:23am"
        //"E, MMMM dd, yyyy h:mmaa" -> "Mon, April 6, 1970 3:23am&
        //"EEEE, MMMM dd, yyyy h:mmaa" -> "Monday, April 6, 1970 3:23am"

        // display the date chosen and days from current
        tvStartDate.setText("Your cycle will begin on " + DateFormat.format("EEEE, MMMM dd, yyyy. ", aDate)); //+
                //daysBetween(Calendar.getInstance(), aDate)); //+ " days from today");
    }
}
