package com.mindtheapps.sampleprefs;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;

public class MySettingsFragment extends PreferenceFragmentCompat {

    /**
     * A preference value change listener that updates the preference's summary
     * to reflect its new value.
     */
    private static Preference.OnPreferenceChangeListener sPreferenceChangeListener = new Preference.OnPreferenceChangeListener() {
        @Override
        public boolean onPreferenceChange(Preference preference, Object value) {
            String stringValue = value.toString();

            // assuming we have only EditText fields.
            // see the AndroidWorkshop project for more examples.
            preference.setSummary(stringValue);
            return true;
        }

    };

    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.my_preferences);
        initPref("display_name");
    }

    private void initPref(String prefKey) {
        Preference preference = findPreference(prefKey);
        preference.setOnPreferenceChangeListener(sPreferenceChangeListener);

        // call like it was changed, to show the initial value.
        // tip ctrl-alt click the onPreferenceChange will go to the implementation
        sPreferenceChangeListener.onPreferenceChange(preference,
                PreferenceManager.getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), "(not set yet)"));
    }
}