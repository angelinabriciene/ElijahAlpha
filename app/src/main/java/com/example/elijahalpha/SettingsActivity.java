package com.example.elijahalpha;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.HashSet;
import java.util.Set;

public class SettingsActivity extends AppCompatActivity {

    private CheckBox checkboxSriuba, checkboxMakaronai, checkboxNamo, checkboxParduotuve;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Initialize checkboxes
        checkboxSriuba = findViewById(R.id.checkbox_sriuba);
        checkboxMakaronai = findViewById(R.id.checkbox_makaronai);
        checkboxNamo = findViewById(R.id.checkbox_namo);
        checkboxParduotuve = findViewById(R.id.checkbox_parduotuve);
        saveButton = findViewById(R.id.save_button);

        // Load saved preferences
        loadPreferences();

        // Save button click listener
        saveButton.setOnClickListener(v -> {
            // Save selected options to shared preferences
            saveSelectedOptions();

            // Save preferences for checkboxes
            savePreferences();

            Toast.makeText(SettingsActivity.this, "Changes saved", Toast.LENGTH_SHORT).show();

            // Return to the main activity
            Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });
    }


    private void loadPreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("app_settings", MODE_PRIVATE);

        checkboxSriuba.setChecked(sharedPreferences.getBoolean("sriuba_enabled", true));
        checkboxMakaronai.setChecked(sharedPreferences.getBoolean("makaronai_enabled", true));
        checkboxNamo.setChecked(sharedPreferences.getBoolean("namo_enabled", true));
        checkboxParduotuve.setChecked(sharedPreferences.getBoolean("parduotuve_enabled", true));
    }

    private void savePreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("app_settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean("sriuba_enabled", checkboxSriuba.isChecked());
        editor.putBoolean("makaronai_enabled", checkboxMakaronai.isChecked());
        editor.putBoolean("namo_enabled", checkboxNamo.isChecked());
        editor.putBoolean("parduotuve_enabled", checkboxParduotuve.isChecked());

        editor.apply();
    }

    private void saveSelectedOptions() {
        SharedPreferences prefs = getSharedPreferences("PARENT_SETTINGS", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        // Save selected options for "Valgyti"
        Set<String> valgytiOptions = new HashSet<>();
        if (checkboxSriuba.isChecked()) {
            valgytiOptions.add("Sriuba");
        }
        if (checkboxMakaronai.isChecked()) {
            valgytiOptions.add("Makaronai");
        }
        editor.putStringSet("Valgyti", valgytiOptions);

        // Save selected options for "Eiti"
        Set<String> eitiOptions = new HashSet<>();
        if (checkboxNamo.isChecked()) {
            eitiOptions.add("Namo");
        }
        if (checkboxParduotuve.isChecked()) {
            eitiOptions.add("Į Parduotuvę");
        }
        editor.putStringSet("Eiti", eitiOptions);

        editor.apply();  // Save changes
    }
}
