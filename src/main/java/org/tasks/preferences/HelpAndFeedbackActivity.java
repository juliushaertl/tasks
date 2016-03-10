package org.tasks.preferences;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import org.tasks.R;
import org.tasks.injection.ActivityComponent;
import org.tasks.injection.InjectingPreferenceActivity;

import javax.inject.Inject;

public class HelpAndFeedbackActivity extends InjectingPreferenceActivity {

    @Inject Device device;
    @Inject Preferences preferences;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.preferences_help);

        findPreference(getString(R.string.contact_developer)).setIntent(
                new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "Tasks Support <support@tasks.org>", null)) {{
                    putExtra(Intent.EXTRA_SUBJECT, "Tasks Feedback");
                    putExtra(Intent.EXTRA_TEXT, device.getDebugInfo());
                }});
        if (!getResources().getBoolean(R.bool.google_play_store_available)) {
            remove(R.string.rate_tasks);
        }
    }

    private void remove(int resId) {
        getPreferenceScreen().removePreference(findPreference(getString(resId)));
    }

    @Override
    public void inject(ActivityComponent component) {
        component.inject(this);
    }
}