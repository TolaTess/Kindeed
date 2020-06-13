package com.tolaotesanya.kindeed.dependencies;

import com.auth0.android.Auth0;
import com.auth0.android.authentication.AuthenticationAPIClient;
import com.auth0.android.authentication.storage.SecureCredentialsManager;
import com.auth0.android.authentication.storage.SharedPreferencesStorage;
import com.tolaotesanya.kindeed.activities.auth.AuthActivity;
import com.tolaotesanya.kindeed.coordinator.IntentPresenter;

public class DependencyRegistry {

    public static DependencyRegistry shared = new DependencyRegistry();

    private IntentPresenter intentPresenter = new IntentPresenter();

    public void inject(AuthActivity activity){
        Auth0 auth0 = new Auth0(activity);
        SecureCredentialsManager credentialsManager = new
                SecureCredentialsManager(activity,
                new AuthenticationAPIClient(auth0),
                new SharedPreferencesStorage(activity));
        activity.configureWith(auth0, credentialsManager, intentPresenter);
    }

}
