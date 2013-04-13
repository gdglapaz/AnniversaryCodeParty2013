/*
 * Copyright (C) 2012 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.google.android.gms.samples.plus;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.plus.PlusClient;
import com.google.android.gms.plus.model.people.Person;
import com.google.android.gms.samples.plus.PlusClientFragment.OnSignedInListener;
import com.google.android.gms.samples.plus.db.PomodoroDatabaseAdapter;

/**
 * Example of signing in a user with Google+, and how to make a call to a Google+ API endpoint.
 */
public class SignInActivity extends FragmentActivity
        implements View.OnClickListener, OnSignedInListener {

    public static final int REQUEST_CODE_PLUS_CLIENT_FRAGMENT = 0;

    private TextView mSignInStatus;
    private PlusClientFragment mSignInFragment;
    
    //BASE DE DATOS
    private PomodoroDatabaseAdapter db;
    @Override
    protected void onStart() {
    	// TODO Auto-generated method stub
    	super.onStart();
    	db.abrir();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in_activity);
        
        db = new PomodoroDatabaseAdapter(this);

        mSignInFragment =
                PlusClientFragment.getPlusClientFragment(this, MomentUtil.VISIBLE_ACTIVITIES);

        findViewById(R.id.sign_in_button).setOnClickListener(this);
        findViewById(R.id.sign_out_button).setOnClickListener(this);
        findViewById(R.id.revoke_access_button).setOnClickListener(this);
        mSignInStatus = (TextView) findViewById(R.id.sign_in_status);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.sign_out_button:
                resetAccountState();
                mSignInFragment.signOut();
                break;
            case R.id.sign_in_button:
                mSignInFragment.signIn(REQUEST_CODE_PLUS_CLIENT_FRAGMENT);
                break;
            case R.id.revoke_access_button:
                resetAccountState();
                mSignInFragment.revokeAccessAndDisconnect();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int responseCode, Intent intent) {
        mSignInFragment.handleOnActivityResult(requestCode, responseCode, intent);
    }

    @Override
    public void onSignedIn(PlusClient plusClient) {
        mSignInStatus.setText(getString(R.string.signed_in_status));

        // We can now obtain the signed-in user's profile information.
        Person currentPerson = plusClient.getCurrentPerson();
        
        
        if (currentPerson != null) {
            String greeting = getString(R.string.greeting_status, currentPerson.getDisplayName());
            mSignInStatus.setText(greeting+' '+currentPerson.getId());
            //
            
            //INSERTAMOS
            
            db.insertPlus(currentPerson.getDisplayName());

            Cursor cursor_show = db.getAllPomodoros();
            if (cursor_show.moveToLast()) {				
            	String name = cursor_show.getString(1);
            	
            	Toast.makeText(SignInActivity.this, " "+name, Toast.LENGTH_SHORT).show();
        	}
             
        } else {
            resetAccountState();
        }
    }

    private void resetAccountState() {
        mSignInStatus.setText(getString(R.string.signed_out_status));
    }
}
