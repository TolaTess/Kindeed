package com.tolaotesanya.kindeed.activities.auth;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tolaotesanya.kindeed.ActivityClassName;
import com.tolaotesanya.kindeed.coordinator.IntentPresenter;
import com.tolaotesanya.kindeed.dependencies.DependencyRegistry;
import com.tolaotesanya.kindeed.R;

public class AuthActivity extends AppCompatActivity {
    private Button createButton;
    private Button loginButton;

    private IntentPresenter intentPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        attachUI();
        DependencyRegistry.shared.inject(this);
    }

    public void configureWith(IntentPresenter intentPresenter) {
        this.intentPresenter = intentPresenter;
    }

    private void setupUI() {
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentPresenter.presentIntent(AuthActivity.this, ActivityClassName.register);
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intentPresenter.presentIntent(AuthActivity.this, ActivityClassName.login);
            }
        });
    }

    private void attachUI() {
        createButton = findViewById(R.id.reg_btn);
        loginButton = findViewById(R.id.login_btn);
    }
}
