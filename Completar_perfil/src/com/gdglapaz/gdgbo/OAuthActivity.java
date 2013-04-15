/* 
 * Copyright (C) 2011 iMellon
 * 
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package com.gdglapaz.gdgbo;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.gdglapaz.gdgbo.store.SharedPreferencesCredentialStore;
import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAccessTokenRequest.GoogleAuthorizationCodeGrant;
import com.google.api.client.googleapis.auth.oauth2.draft10.GoogleAuthorizationRequestUrl;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;

/**
 * 
 * This class starts a webview to let the user either insert credentials 
 * and accept/deny the application or accept/deny directly the application.
 * The procedure is based on the OAuth 2.0 dance which is handled by the 
 * Google libraries.
 * 
 */

public class OAuthActivity extends Activity {

	private SharedPreferences prefs;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.prefs = PreferenceManager.getDefaultSharedPreferences(this);

		// new OAuthRequestTokenTask(this).execute();
	}

	@Override
	protected void onResume() {
		super.onResume();
		WebView webview = new WebView(this);
		webview.setVisibility(View.VISIBLE);
		webview.getSettings().setJavaScriptEnabled(true);
		setContentView(webview);
		
		String googleAuthorizationRequestUrl = new GoogleAuthorizationRequestUrl(
				SharedPreferencesCredentialStore.CLIENT_ID,
				SharedPreferencesCredentialStore.REDIRECT_URI,
				SharedPreferencesCredentialStore.SCOPE).build();

		webview.setWebViewClient(new WebViewClient() {
 
			@Override
			public void onPageFinished(WebView view, String url) {

				if (url.startsWith(SharedPreferencesCredentialStore.REDIRECT_URI)) {
					try {
						if (url.indexOf("code=") != -1) {
							// Url is like http://localhost/?code=4/Z5DgC1IxNL-muPsrE2Sjy9zQn2pF
							String code = url.substring(
									SharedPreferencesCredentialStore.REDIRECT_URI.length() + 7,
									url.length());

							AccessTokenResponse accessTokenResponse = new GoogleAuthorizationCodeGrant(
									new NetHttpTransport(),
									new JacksonFactory(),
									SharedPreferencesCredentialStore.CLIENT_ID,
									SharedPreferencesCredentialStore.CLIENT_SECRET,
									code, SharedPreferencesCredentialStore.REDIRECT_URI)
									.execute();

							SharedPreferencesCredentialStore credentialStore = SharedPreferencesCredentialStore
									.getInstance(prefs);
							credentialStore.write(accessTokenResponse);
							view.setVisibility(View.INVISIBLE);
							startActivity(new Intent(
									OAuthActivity.this,
									MainActivity.class));
							finish();
						} else if (url.indexOf("error=") != -1) {
							view.setVisibility(View.INVISIBLE);
							SharedPreferencesCredentialStore.getInstance(prefs)
									.clearCredentials();
							startActivity(new Intent(
									OAuthActivity.this,
									MainActivity.class));
							finish();
						}

					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});

		webview.loadUrl(googleAuthorizationRequestUrl);
	}
}
