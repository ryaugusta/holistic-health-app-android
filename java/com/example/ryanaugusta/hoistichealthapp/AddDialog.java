// Project:     Holistic Health App
// Author:      Augusta
// Date:        2018
// Desc:        This is an add dialog that can be utilized to populate the supplement ListView
//              I may change this to populate the 'MyStack' ListView instead.

package com.example.ryanaugusta.hoistichealthapp;

import android.app.Dialog;
//import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.support.v4.app.DialogFragment;
/**
 * Created by ryanaugusta on 2/4/18.
 */

// THIS CLASS MAY NOT BE NEEDED.
public class AddDialog extends DialogFragment {

    // create a reference for the inflator
    private LayoutInflater inflater;

    private EditText etName;
   // private EditText etInfo;

//    private EditText etName;
//    private EditText etInfo;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        // create the dialog builder
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        //builder.setMessage("Add" + name + "to My Stack?");
        // set the title
        builder.setTitle("Add to 'My Stack'");

        // Get the layout inflater object
        inflater = getActivity().getLayoutInflater();

        // Inflate and set the layout for the dialog
        // Pass null as the parent view because its going in the dialog layout
        View dialogLayout = inflater.inflate(R.layout.add_dialog, null);
        builder.setView(dialogLayout);

        // create the Java objects and tie to the dialog GUI
        etName = (EditText)dialogLayout.findViewById(R.id.etName);
       // etInfo = (EditText)dialogLayout.findViewById(R.id.etInfo);
        // add a positive button
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                System.out.println("Add dialog onClick called");
                try
                {
                    MyStackInfo myStackInfo = new MyStackInfo(etName.getText().toString());

                    // debug
                    System.out.println(myStackInfo.toString());
                    addListener.addDialogPositiveClick(myStackInfo);

                }
                catch(Exception e)
                {
                    System.out.println("Catch: " + e.getMessage());
                }
            }
        });

        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });

        return builder.create();
    }

    // public interface used to define callback methods
    // that will be called in the MainActivity
    public interface AddDialogListener
    {
        // these method will be implemented in the MainActivity
        public void addDialogPositiveClick(MyStackInfo myStackInfo);
        public void addDialogNegativeClick();
    }

    // Use this reference of the interface to deliver action events
    private AddDialogListener addListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try
        {
            // Instantiate the addListener so we can send events to the host
//            addListener = (AddDialogListener) context;

            // set the fragment as the listener for the dialog
            addListener = (AddDialogListener)getTargetFragment();
        }
        catch (ClassCastException e)
        {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement AddDialogListener");
        }
    }
}


