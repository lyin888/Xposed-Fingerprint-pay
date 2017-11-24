package com.yyxx.wechatfp.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceManager;
import android.preference.PreferenceScreen;

import com.lb.material_preferences_library.AppCompatPreferenceActivity;
import com.yyxx.wechatfp.R;
import com.yyxx.wechatfp.util.log.L;

/**
 * Created by Jason on 2017/11/24.
 */

public class SettingsActivity extends AppCompatPreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    public static void open(Context context) {
        try {
            Intent intent = new Intent(context, SettingsActivity.class);
            context.startActivity(intent);
        } catch (Exception e) {
            L.e(e);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.perference);
        PreferenceManager.getDefaultSharedPreferences(this).registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {

        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        switch (key) {
            case "show_icon" :
                boolean showIcon = sharedPreferences.getBoolean("show_icon", true);
                onShowIconChange(showIcon);
                break;
        }
    }

    private void onShowIconChange(boolean showIcon) {
        int state = showIcon ? PackageManager.COMPONENT_ENABLED_STATE_DEFAULT : PackageManager.COMPONENT_ENABLED_STATE_DISABLED;
        ComponentName aliasName = new ComponentName(this, getPackageName() + ".Main");
        getPackageManager().setComponentEnabledSetting(aliasName, state, PackageManager.DONT_KILL_APP);
    }
}
