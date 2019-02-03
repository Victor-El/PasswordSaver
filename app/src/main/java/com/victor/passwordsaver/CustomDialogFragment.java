package com.victor.passwordsaver;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.content.Context.MODE_PRIVATE;

public class CustomDialogFragment extends AppCompatDialogFragment {

    private EditText mIdentifier;
    private EditText mPassword;
    private Button mSubmit;

    View view;

    private String mIdentifierValue;
    private String mPasswordValue;
    public final String SHARED_PREFS = "passSave";

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        view = layoutInflater.inflate(R.layout.custom_dialog_fragment_view, null);
        builder.setView(view);
        View newView = layoutInflater.inflate(R.layout.title_view, null);
        mSubmit = (Button) view.findViewById(R.id.frag_btn);
        mSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                positiveBtnClick();
            }
        });
        builder.setCustomTitle(newView);
        builder.setTitle(R.string.dialog_title).setPositiveButton("Add", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                positiveBtnClick();
            }
        }).setNegativeButton(" Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        return builder.create();
    }

    public void positiveBtnClick() {
        Toast.makeText(getActivity(), "Saved", Toast.LENGTH_SHORT).show();
        mIdentifier = (EditText) view.findViewById(R.id.identifier_frag_edit);
        mPassword = (EditText) view.findViewById(R.id.password_frag_edit);

        mIdentifierValue = mIdentifier.getText().toString().trim();
        mPasswordValue = mPassword.getText().toString();

        SharedPreferences sharedPreferences = getContext().getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(mIdentifierValue, mIdentifierValue + " : " + mPasswordValue);
        editor.apply();
        Toast.makeText(getContext(), "Done", Toast.LENGTH_SHORT).show();
        getActivity().finish();
        startActivity(getActivity().getIntent());
    }

    public String getmIdentifierValue() {
        return mIdentifierValue;
    }

    public String getmPasswordValue() {
        return mPasswordValue;
    }
}
