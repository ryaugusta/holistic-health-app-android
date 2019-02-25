// Project:		Final
// Class Name:	DeleteDialogFragment
// Date:        12/8/2017
// Author:      Ryan Augusta
// Description
// Add AlertBuilder with positive and negative buttons
// Also implement call back listener in MainActivity
// this handles the delete action for the listView


package com.example.ryanaugusta.hoistichealthapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

// this class represents the ResetDialogFragment
public class DeleteConfirmDialog extends DialogFragment
{
    private String name;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        // Use the Builder class for convenient dialog construction
        final AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());


        // get arguments from the MainActivity long press
        name = getArguments().getString("name"); // "name" was the key from the long click

        // set the Message
        builder.setMessage("Would you like to remove " + "'" + name + "'" + " from the list?");


        // set the title
        builder.setTitle("ATTENTION:");

        // set the positive button
        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                // get the main activity (not needed, but good to have)
                MainActivity ma = (MainActivity)getActivity();
                // Note: once you have reference to the MainActivity,
                //      you can get anything you want from that class.


                // call the delete listener method in the main activity
                // and pass the name to delete
                dListener.onDialogDelete(name); // MainActivity listens for this

                // call the listener method in the main activity
               // dListener.onDialogPositiveClick();
            }
        });

        // set the negative button
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                // User cancelled the dialog
                // call the listener method in the main activity
                //dListener.onDialogDeleteNegativeClick();

            }
        });

        // Create the AlertDialog object and return it
        return builder.create();
    }



    // public interface used to define callback methods
    // that will be called in the MainActivity
    public interface DeleteConfirmDialogListener
    {
        // these method will be implemented in the MainActivity

        public void onDialogDeleteNegativeClick();
        void onDialogDelete(String name);
    }

    // Use this instance of the interface to deliver action events
    private DeleteConfirmDialogListener dListener;

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
//    public void onAttach(Activity activity) // is deprecated
    public void onAttach(Context context)
    {
        super.onAttach(context);
        // Verify that the host activity implements the callback interface
        try
        {
            // Instantiate the Listener so we can send events to the host
            dListener = (DeleteConfirmDialogListener)getTargetFragment();
        }
        catch (ClassCastException e)
        {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement DeleteConfirmDialogListener");
        }
    }
}
