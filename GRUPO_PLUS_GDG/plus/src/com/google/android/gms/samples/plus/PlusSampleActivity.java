
package com.google.android.gms.samples.plus;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Displays examples of integrating with the Google+ Platform for Android.
 */
public class PlusSampleActivity extends ListActivity {
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ///GDG
        Intent login_plus = new Intent(this,SignInActivity.class);
        startActivity(login_plus);
    }
}
