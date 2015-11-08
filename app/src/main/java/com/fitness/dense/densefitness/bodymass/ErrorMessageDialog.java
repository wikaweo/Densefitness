package com.fitness.dense.densefitness.bodymass;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.widget.Toast;

/**
 * Created by Fredrik on 2015-10-18.
 */
public class ErrorMessageDialog extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState){
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Empty values");
        builder.setMessage("You need to put values to all properties to add a new row.");
        builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        });
        Dialog dialog = builder.create();

        return dialog;
    }
}
