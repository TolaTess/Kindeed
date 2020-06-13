package com.tolaotesanya.kindeed.dependencies;

import com.tolaotesanya.kindeed.activities.auth.AuthActivity;
import com.tolaotesanya.kindeed.coordinator.IntentPresenter;

public class DependencyRegistry {

    public static DependencyRegistry shared = new DependencyRegistry();

    private IntentPresenter intentPresenter = new IntentPresenter();

    public void inject(AuthActivity activity){
        activity.configureWith(intentPresenter);
    }

}
