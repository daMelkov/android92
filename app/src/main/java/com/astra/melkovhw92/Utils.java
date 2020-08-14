package com.astra.melkovhw92;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Build;

import java.util.Locale;

public class Utils
{
    public static int getTheme() {
        return sTheme;
    }
    private static int sTheme = R.style.Normal;

    public static Locale getLocale() {
        return sLocale;
    }
    private static Locale sLocale = new Locale("en");

    public final static int SIZE_SMALL = 0;
    public final static int SIZE_NORMAL = 1;
    public final static int SIZE_LARGE = 2;


    /** Set the theme of the Activity, and restart it by creating a new Activity of the same type. */
    public static void changeUI(Activity activity, int theme, Locale locale)
    {
        sTheme = theme;
        sLocale = locale;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            Configuration config = new Configuration();
            config.setLocale(locale);

            activity.getResources().updateConfiguration(config, activity.getBaseContext().getResources().getDisplayMetrics());
        }

        activity.finish();
        activity.startActivity(new Intent(activity, activity.getClass()));
    }

    /** Set the theme of the activity, according to the configuration. */
    public static void onActivityCreateSetTheme(Activity activity)
    {
        switch (sTheme)
        {
            default:
            case SIZE_SMALL:
                activity.setTheme(R.style.Small);
                break;
            case SIZE_NORMAL:
                activity.setTheme(R.style.Normal);
                break;
            case SIZE_LARGE:
                activity.setTheme(R.style.Large);
                break;
        }
    }
}
