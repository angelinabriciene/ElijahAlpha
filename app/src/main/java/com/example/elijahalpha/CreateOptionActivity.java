package com.example.elijahalpha;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CreateOptionActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private Uri imageUri;

    private EditText optionNameEditText;
    private Spinner primaryCategorySpinner;
    private Button uploadImageButton;
    private Button submitOptionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_option);

        // Initialize views
        optionNameEditText = findViewById(R.id.optionNameEditText);
        primaryCategorySpinner = findViewById(R.id.primaryCategorySpinner);
        uploadImageButton = findViewById(R.id.uploadImageButton);
        submitOptionButton = findViewById(R.id.submitOptionButton);

        // Set up spinner with primary categories
        setupPrimaryCategorySpinner();

        // Handle image upload button click
        uploadImageButton.setOnClickListener(v -> openGallery());

        // Handle submit option button click
        submitOptionButton.setOnClickListener(v -> submitOption());
    }

    private void setupPrimaryCategorySpinner() {
        // Sample primary categories
        String[] categories = {"Valgyti", "Eiti", "Šokinėti", "Važiuoti"};

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, categories);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        primaryCategorySpinner.setAdapter(adapter);
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            imageUri = data.getData();
            Toast.makeText(this, "Image selected: " + imageUri.toString(), Toast.LENGTH_SHORT).show();
        }
    }

    private void submitOption() {
        // Get option name and selected category
        String optionName = optionNameEditText.getText().toString();
        String primaryCategory = primaryCategorySpinner.getSelectedItem().toString();

        if (optionName.isEmpty() || imageUri == null) {
            Toast.makeText(this, "Please enter a name and upload an image", Toast.LENGTH_SHORT).show();
            return;
        }

        // Send the data to the backend (we'll implement this part next)
        Toast.makeText(this, "Option submitted: " + optionName, Toast.LENGTH_SHORT).show();
    }
}
