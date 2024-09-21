package com.example.elijahalpha;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SettingsActivity extends AppCompatActivity {

    private LinearLayout checkboxContainer;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        checkboxContainer = findViewById(R.id.checkbox_container);
        saveButton = findViewById(R.id.save_button);

        // Load saved preferences
        loadPreferences();

        // Save button click listener
        saveButton.setOnClickListener(v -> {
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

        for (int i = 0; i < checkboxContainer.getChildCount(); i++) {
            if (checkboxContainer.getChildAt(i) instanceof CheckBox) {
                CheckBox checkbox = (CheckBox) checkboxContainer.getChildAt(i);
                String key = (String) checkbox.getTag(); // Use tag as the key for preferences
                boolean isChecked = sharedPreferences.getBoolean(key, true);
                checkbox.setChecked(isChecked);
            }
        }
    }

    private void savePreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("app_settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        for (int i = 0; i < checkboxContainer.getChildCount(); i++) {
            if (checkboxContainer.getChildAt(i) instanceof CheckBox) {
                CheckBox checkbox = (CheckBox) checkboxContainer.getChildAt(i);
                String key = (String) checkbox.getTag(); // Use tag as the key for preferences
                editor.putBoolean(key, checkbox.isChecked());
            }
        }

        editor.apply();
    }
}
