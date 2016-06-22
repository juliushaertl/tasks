package org.tasks.preferences;

import android.app.Activity;
import android.graphics.PixelFormat;

import javax.inject.Inject;

import static com.todoroo.andlib.utility.AndroidUtilities.atLeastLollipop;

public class ThemeApplicator {
    private final Activity activity;
    private final ThemeManager themeManager;

    @Inject
    public ThemeApplicator(Activity activity, ThemeManager themeManager) {
        this.activity = activity;
        this.themeManager = themeManager;
    }

    public void applyThemeAndStatusBarColor() {
        applyTheme();
        applyStatusBarColor();
    }

    public void applyTheme() {
        applyTheme(
                themeManager.getAppTheme().getResId(),
                themeManager.getAccentColor().getResId());
    }

    private void applyTheme(int themeResId, int accentResId) {
        activity.setTheme(themeResId);
        activity.getTheme().applyStyle(accentResId, true);
        activity.getWindow().setFormat(PixelFormat.RGBA_8888);
    }

    private void applyStatusBarColor() {
        if (atLeastLollipop()) {
            Theme appTheme = themeManager.getAppTheme();
            activity.getWindow().setStatusBarColor(appTheme.getPrimaryDarkColor());
        }
    }
}
