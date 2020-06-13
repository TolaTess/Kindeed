package com.tolaotesanya.kindeed.coordinator;

import android.content.Context;
import android.content.Intent;

import com.tolaotesanya.kindeed.ActivityClassName;
import com.tolaotesanya.kindeed.activities.auth.LoginActivity;
import com.tolaotesanya.kindeed.activities.auth.RegisterActivity;

public class IntentPresenter {


    public void presentIntent(Context context, ActivityClassName activity) {
        switch (activity) {
            case register:
                Intent registerIntent = new Intent(context, RegisterActivity.class);
                context.startActivity(registerIntent);
                break;
            case login:
                Intent loginIntent = new Intent(context, LoginActivity.class);
                context.startActivity(loginIntent);
                break;
        }

    }
}
