package com.tolaotesanya.kindeed.coordinator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.auth0.android.authentication.storage.CredentialsManagerException;
import com.auth0.android.authentication.storage.SecureCredentialsManager;
import com.auth0.android.callback.BaseCallback;
import com.auth0.android.result.Credentials;
import com.tolaotesanya.kindeed.MainActivity;
import com.tolaotesanya.kindeed.R;
import com.tolaotesanya.kindeed.activities.ItemActivity;
import com.tolaotesanya.kindeed.activities.ServiceActivity;
import com.tolaotesanya.kindeed.activities.auth.AuthActivity;
import com.tolaotesanya.kindeed.modellayer.enums.ActivityClassName;

public class IntentPresenter {

    public void presentIntent(Context context, ActivityClassName activity, String name) {
        switch (activity) {
            case item:
                Activity act = (Activity) context;
                Intent intent = new Intent(context, ItemActivity.class);
                intent.putExtra("itemName", name);
                context.startActivity(intent);
                act.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
            case service:
                Activity activity1 = (Activity) context;
                Intent intentService = new Intent(context, ServiceActivity.class);
                intentService.putExtra("serviceName", name);
                context.startActivity(intentService);
                activity1.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                break;
        }

    }

    public void showNextActivity(final Context context, SecureCredentialsManager credentialsManager) {
        final Activity activity = (Activity) context;
        credentialsManager.getCredentials(new BaseCallback<Credentials, CredentialsManagerException>() {
            @Override
            public void onSuccess(final Credentials credentials) {
                // Move to the next activity
                Intent intent = new Intent(context, MainActivity.class);
                intent.putExtra(AuthActivity.EXTRA_ACCESS_TOKEN, credentials.getAccessToken());
                intent.putExtra(AuthActivity.EXTRA_ID_TOKEN, credentials.getIdToken());
                context.startActivity(intent);
                activity.finish();
            }

            @Override
            public void onFailure(CredentialsManagerException error) {
                // Credentials could not be retrieved.
                activity.finish();
            }
        });
    }
}
