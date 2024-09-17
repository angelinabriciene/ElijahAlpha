package com.example.elijahalpha;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements CustomAdapter.OnItemClickListener {

    private static final int PICK_IMAGE_REQUEST = 1;  // Add this to define the request code
    private List<Option> optionList;
    private TextToSpeech tts;
    private RecyclerView recyclerView;
    private CustomAdapter adapter;
    private List<Item> itemList;
    private LinearLayout optionLayout; // Layout to dynamically show secondary options

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Button to open gallery
        findViewById(R.id.uploadImageButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        // Initialize RecyclerView and layout manager
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns for grid layout

        // Create button list for the RecyclerView
        itemList = new ArrayList<>();
        itemList.add(new Item(R.drawable.valgyti, "Valgyti"));
        itemList.add(new Item(R.drawable.eiti, "Eiti"));
        itemList.add(new Item(R.drawable.sokineti, "Šokinėti"));
        itemList.add(new Item(R.drawable.vaziuoti, "Važiuoti"));

        // Initialize adapter and set it to the RecyclerView
        adapter = new CustomAdapter(itemList, this, this);
        recyclerView.setAdapter(adapter);

        // Initialize the layout for dynamically showing secondary options
        optionLayout = findViewById(R.id.optionLayout);

        // Initialize TTS
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if (status == TextToSpeech.SUCCESS) {
                    // Set the language to Lithuanian (or any desired language)
                    int result = tts.setLanguage(new Locale("lt", "LT"));

                    if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                        Log.e("TTS", "Lithuanian language is not supported!");
                    } else {
                        Log.d("TTS", "TTS is initialized successfully!");
                    }
                } else {
                    Log.e("TTS", "TTS initialization failed!");
                }
            }
        });
    }

    // Open gallery to select an image
    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    // Handle selected image
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri imageUri = data.getData();
            // Now you can upload the image
            uploadImage(imageUri);
        }
    }

    private void uploadImage(Uri imageUri) {
        File destinationDirectory = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "MyAppImages");
        if (!destinationDirectory.exists()) {
            destinationDirectory.mkdirs(); // Create the directory if it doesn't exist
        }

        try {
            // Create a file in the destination directory
            File destinationFile = new File(destinationDirectory, "uploaded_image.jpg");
            InputStream inputStream = getContentResolver().openInputStream(imageUri);
            OutputStream outputStream = new FileOutputStream(destinationFile);

            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            outputStream.close();
            inputStream.close();

            Toast.makeText(this, "Image saved to " + destinationFile.getAbsolutePath(), Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
            Toast.makeText(this, "Failed to save image", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemClick(Item item) {
        optionLayout.removeAllViews();
        optionLayout.removeAllViews();

        switch (item.getText()) {
            case "Valgyti":
                handleOption("Valgyti");
                List<Item> valgytiItems = new ArrayList<>();
                valgytiItems.add(new Item(R.drawable.sriuba, "Sriuba"));
                valgytiItems.add(new Item(R.drawable.makaronai, "Makaronai"));
                showSecondaryOptions(valgytiItems);
                optionLayout.addView(createButton("Atgal"));
                break;
            case "Eiti":
                handleOption("Eiti");
                List<Item> eitiItems = new ArrayList<>();
                eitiItems.add(new Item(R.drawable.namo, "Namo"));
                eitiItems.add(new Item(R.drawable.parduotuve, "Į Parduotuvę"));
                showSecondaryOptions(eitiItems);
                optionLayout.addView(createButton("Atgal"));
                break;
            case "Šokinėti":
                handleOption("Šokinėti");
                List<Item> sokinetiItems = new ArrayList<>();
                sokinetiItems.add(new Item(R.drawable.lova, "And lovos"));
                sokinetiItems.add(new Item(R.drawable.batutas, "Ant batuto"));
                showSecondaryOptions(sokinetiItems);
                optionLayout.addView(createButton("Atgal"));
                break;
            case "Važiuoti":
                handleOption("Važiuoti");
                List<Item> vaziuotiItems = new ArrayList<>();
                vaziuotiItems.add(new Item(R.drawable.ezero, "Prie ežero"));
                vaziuotiItems.add(new Item(R.drawable.parkas, "Į parką"));
                showSecondaryOptions(vaziuotiItems);
                optionLayout.addView(createButton("Atgal"));
                break;
            case "Sriuba":
                handleOption("Sriubą");
                Item sriubaItem = new Item(R.drawable.sriuba, "Sriuba");
                List<Item> sriubaList = new ArrayList<>();
                sriubaList.add(sriubaItem);
                showSecondaryOptions(sriubaList);
                optionLayout.addView(createButton("Atgal"));
                break;
            case "Makaronai":
                handleOption("Makaronus");
                Item makaronaiItem = new Item(R.drawable.makaronai, "Makaronai");
                List<Item> makaronaiList = new ArrayList<>();
                makaronaiList.add(makaronaiItem);
                showSecondaryOptions(makaronaiList);
                optionLayout.addView(createButton("Atgal"));
                break;
            case "Į Parduotuvę":
                handleOption("Į Parduotuvę");
                Item parduotuveItem = new Item(R.drawable.parduotuve, "Į Parduotuvę");
                List<Item> parduotuveList = new ArrayList<>();
                parduotuveList.add(parduotuveItem);
                showSecondaryOptions(parduotuveList);
                optionLayout.addView(createButton("Atgal"));
                break;
            case "Namo":
                handleOption("Namo");
                Item namoItem = new Item(R.drawable.namo, "Namo");
                List<Item> namoList = new ArrayList<>();
                namoList.add(namoItem);
                showSecondaryOptions(namoList);
                optionLayout.addView(createButton("Atgal"));
                break;
            case "And lovos":
                handleOption("And lovos");
                Item lovaItem = new Item(R.drawable.lova, "And lovos");
                List<Item> lovaList = new ArrayList<>();
                lovaList.add(lovaItem);
                showSecondaryOptions(lovaList);
                optionLayout.addView(createButton("Atgal"));
                break;
            case "Ant batuto":
                handleOption("Ant batuto");
                Item batutasItem = new Item(R.drawable.batutas, "Ant batuto");
                List<Item> batutasList = new ArrayList<>();
                batutasList.add(batutasItem);
                showSecondaryOptions(batutasList);
                optionLayout.addView(createButton("Atgal"));
                break;
            case "Prie ežero":
                handleOption("Prie ežero");
                Item ezeroItem = new Item(R.drawable.ezero, "Prie ežero");
                List<Item> ezeroList = new ArrayList<>();
                ezeroList.add(ezeroItem);
                showSecondaryOptions(ezeroList);
                optionLayout.addView(createButton("Atgal"));
                break;
            case "Į parką":
                handleOption("Į parką");
                Item parkaItem = new Item(R.drawable.parkas, "Į parką");
                List<Item> parkaList = new ArrayList<>();
                parkaList.add(parkaItem);
                showSecondaryOptions(parkaList);
                optionLayout.addView(createButton("Atgal"));
                break;
            default:
                break;
        }
    }

    private void handleOption(String message) {
        // For example, you can show a message or play a sound here
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();

        // Optionally, you could add more actions, like playing a sound, changing images, etc.
        speakText(message);
    }

    private void speakText(String text) {
        if (tts != null) {
            tts.speak(text, TextToSpeech.QUEUE_FLUSH, null, null);
        }
    }

    // Helper method to create buttons dynamically
    private Button createButton(String text) {
        Button button = new Button(this);
        button.setText(text);
        button.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleSecondaryButtonClick(text);
            }
        });
        return button;
    }

    // Handle clicks for dynamically created secondary buttons
    private void handleSecondaryButtonClick(String text) {
        if (text.equals("Atgal")) {
            optionLayout.removeAllViews(); // Clear secondary options when "Atgal" (Back) is clicked
        } else {
            // For this example, just showing a message for secondary options
            optionLayout.removeAllViews();
            optionLayout.addView(createButton("Pasirinkote: " + text)); // Display the choice
            optionLayout.addView(createButton("Atgal")); // Add back button again
        }
    }

    private void showSecondaryOptions(List<Item> items) {
        optionLayout.removeAllViews(); // Clear previous options

        // Create a new RecyclerView for secondary options
        RecyclerView secondaryRecyclerView = new RecyclerView(this);
        secondaryRecyclerView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        // Set up the layout manager for grid display (adjust columns as needed)
        secondaryRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        // Create and set the adapter for secondary options
        CustomAdapter secondaryAdapter = new CustomAdapter(items, this, this);
        secondaryRecyclerView.setAdapter(secondaryAdapter);

        // Add the new RecyclerView to the option layout
        optionLayout.addView(secondaryRecyclerView);
    }

    @Override
    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
        super.onDestroy();
    }
}