package com.tolaotesanya.kindeed.activities.auth;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.auth0.android.Auth0;
import com.auth0.android.Auth0Exception;
import com.auth0.android.authentication.AuthenticationException;
import com.auth0.android.authentication.storage.SecureCredentialsManager;
import com.auth0.android.provider.AuthCallback;
import com.auth0.android.provider.VoidCallback;
import com.auth0.android.provider.WebAuthProvider;
import com.auth0.android.result.Credentials;
import com.tolaotesanya.kindeed.R;
import com.tolaotesanya.kindeed.coordinator.IntentPresenter;
import com.tolaotesanya.kindeed.dependencies.DependencyRegistry;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class AuthActivity extends AppCompatActivity {
    private Button loginButton;

    private IntentPresenter intentPresenter;
    private Auth0 auth0;

    public static final String EXTRA_CLEAR_CREDENTIALS = "com.auth0.CLEAR_CREDENTIALS";
    public static final String EXTRA_ACCESS_TOKEN = "com.auth0.ACCESS_TOKEN";
    public static final String EXTRA_ID_TOKEN = "com.auth0.ID_TOKEN";
    private SecureCredentialsManager credentialsManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_auth);
        loginButton = findViewById(R.id.login_btn);
        setupUI();
        DependencyRegistry.shared.inject(this);
    }

    public void configureWith(Auth0 auth0, SecureCredentialsManager credentialsManager, IntentPresenter intentPresenter) {
        this.auth0 = auth0;
        this.credentialsManager = credentialsManager;
        this.intentPresenter = intentPresenter;

        //Check if the activity was launched to log the user out
        if (getIntent().getBooleanExtra(EXTRA_CLEAR_CREDENTIALS, false)) {
            logout();
            return;
        }
        if (credentialsManager.hasValidCredentials()) {
            // Obtain the existing credentials and move to the next activity
            intentPresenter.showNextActivity(AuthActivity.this, credentialsManager);
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(credentialsManager.checkAuthenticationResult(requestCode, resultCode)){
            return;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setupUI() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               login();
                }
        });
    }

    private void login() {
        /*AuthenticationAPIClient authAPIClient = new AuthenticationAPIClient(auth0);
        SharedPreferencesStorage sharedPrefStorage = new SharedPreferencesStorage(this);
        final CredentialsManager credentialsManager = new CredentialsManager(authAPIClient, sharedPrefStorage);*/

        WebAuthProvider.login(auth0)
                .withScheme("https")
                .withScope("openid offline_access")
                .withAudience(String.format("https://%s/userinfo", getString(R.string.com_auth0_domain)))
                .start(AuthActivity.this, new AuthCallback() {
                            @Override
                            public void onFailure(@NonNull final Dialog dialog) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        dialog.setTitle("Error");
                                        dialog.show();
                                    }
                                });
                            }

                            @Override
                            public void onFailure(final AuthenticationException exception) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(AuthActivity.this, "Error: " + exception.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                            @Override
                            public void onSuccess(@NonNull final Credentials credentials) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        credentialsManager.saveCredentials(credentials);
                                        intentPresenter.showNextActivity(AuthActivity.this, credentialsManager);
                                    }
                                });
                            }
                        }

                );
    }

    private void logout() {
        WebAuthProvider.logout(auth0)
                .withScheme("https")
                .start(AuthActivity.this, logoutCallback);
    }

    private VoidCallback logoutCallback = new VoidCallback() {
        @Override
        public void onSuccess(Void payload) {
            credentialsManager.clearCredentials();
        }

        @Override
        public void onFailure(Auth0Exception error) {
            // Log out canceled, keep the user logged in
            intentPresenter.showNextActivity(AuthActivity.this, credentialsManager);
        }
    };

/*    When calling the v2/logout you have to set federated, redirectTo and client_id and it has to be opened via CustomTabsIntent.

    Once the call is made, a redirect is called to your specified url which in turn should be handled by the android.app.Activity that started the call through the <intent-filter>
    similar to the way the RedirectActivity works within the current auth0 SDK. Then you can proceed with your normal logout flow,
    if you check the logs on Auth0 you should see a Successful Logout call.

    An additional note if the Activity that starts your logout call is not the same as your login, you will need a unique scheme/host specified in the
    Allowed Logout URLs and you will have to account for these changes in your <intent-filter> that way the app will be able to re-direct appropriately.*/

}
