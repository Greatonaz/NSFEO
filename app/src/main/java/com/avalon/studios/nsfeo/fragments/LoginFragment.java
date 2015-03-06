package com.avalon.studios.nsfeo.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import old.nsfeo.R;

public class LoginFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

	    // Create initial reference values
	    final Context ctx = this.getActivity();
	    final FragmentManager manager = this.getActivity().getSupportFragmentManager();
	    final View v = inflater.inflate(R.layout.login_menu, container, false);

	    // Capture recurring references
		final EditText email = ((EditText) (v.findViewById(R.id.EmailTextField)));
	    final EditText password = ((EditText) (v.findViewById(R.id.PasswordTextField)));
	    final Button login = ((Button) (v.findViewById(R.id.LoginButton)));

	    // Create our typeface to set all texts (editable or not) to this
	    final Typeface shojumaru = Typeface.createFromAsset(this.getActivity().getAssets(), "fonts/shojumaru_regular.ttf");
	    assert (shojumaru != null);

	    email.setTypeface(shojumaru);
	    password.setTypeface(shojumaru);
	    ((TextView) (v.findViewById(R.id.EmailTag))).setTypeface(shojumaru);
	    ((TextView) (v.findViewById(R.id.PasswordTag))).setTypeface(shojumaru);
	    login.setTypeface(shojumaru);

	    // Set action callbacks for button: communicate with server in order to get login credentials, if valid
	    login.setOnClickListener(new View.OnClickListener() {

		    @Override
		    public void onClick(final View v) {

			    // Capture current state values
			    final String email_raw = email.getText().toString().trim();
			    final String password_raw = password.getText().toString().trim();

			    // Block screen interaction with a cancelable progress dialog
			    final ProgressDialog login_dialog = new ProgressDialog(ctx);
			    login_dialog.setIndeterminate(true);
			    login_dialog.setCancelable(true);
			    login_dialog.setMessage(ctx.getString(R.string.logging_in));
			    login_dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {

				    @Override
				    public void onCancel(final DialogInterface dialog) {
					    Toast.makeText(ctx, R.string.login_failure, Toast.LENGTH_LONG).show();
				    }

			    });
			    login_dialog.setOnShowListener(new DialogInterface.OnShowListener() {

				    @Override
				    public void onShow(final DialogInterface dialog) {

					    // TODO: Perform server communication here.
					    // TODO: If server responds with credentials, change fragment: use fragment manager for this.
				    }

			    });
			    login_dialog.show();
		    }

	    });

	    return v;
    }

}
