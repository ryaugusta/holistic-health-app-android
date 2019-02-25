package com.example.ryanaugusta.hoistichealthapp;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by ryanaugusta on 3/2/18.
 */

public class SaveDialog extends DialogFragment
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
        builder.setMessage("Would you like to add " + "'" + name + "'" + " to the list?");


        // set the title
        builder.setTitle("Add to your personal 'My Stack' list!");

        // set the positive button
        builder.setPositiveButton("Add", new DialogInterface.OnClickListener()
        {
            public void onClick(DialogInterface dialog, int id)
            {
                // get the main activity (not needed, but good to have)
                MainActivity ma = (MainActivity)getActivity();
                // Note: once you have reference to the MainActivity,
                //      you can get anything you want from that class.

                // call the delete listener method in the main activity
                // and pass the name to delete
                saveListener.onDialogSave(name); // MainActivity listens for this
                Toast.makeText(ma, "Success!", Toast.LENGTH_SHORT).show();

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
    public interface SaveConfirmDialogListener
    {
        // these method will be implemented in the MainActivity

        public void onDialogSaveNegativeClick();
        void onDialogSave(String name);
    }

    // Use this instance of the interface to deliver action events
    private SaveDialog.SaveConfirmDialogListener saveListener;

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
            saveListener = (SaveDialog.SaveConfirmDialogListener)getTargetFragment();
        }
        catch (ClassCastException e)
        {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(context.toString()
                    + " must implement DeleteConfirmDialogListener");
        }
    }
}
