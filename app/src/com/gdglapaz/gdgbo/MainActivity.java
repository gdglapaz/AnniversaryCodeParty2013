package com.gdglapaz.gdgbo;



import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import com.gdglapaz.gdgbo.store.SharedPreferencesCredentialStore;
import com.gdglapaz.gdgbo.OAuthActivity;

public class MainActivity extends Activity {
	
	private SharedPreferences preferences;

	private Button launchOauth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setup();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void setup() {
		setContentView(R.layout.activity_main);

		this.preferences = PreferenceManager.getDefaultSharedPreferences(this);

		launchOauth = (Button) findViewById(R.id.btn_launch_oauth);
	
		launchOauth.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				startActivity(new Intent().setClass(v.getContext(),
						OAuthActivity.class));
			}
		});

			
	
	}

}
