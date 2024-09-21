package com.example.elijahalpha;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class MainActivity extends AppCompatActivity implements CustomAdapter.OnItemClickListener {

    private static final int PICK_IMAGE_REQUEST = 1;
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

        // Initialize RecyclerView and layout manager
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2)); // 2 columns for grid layout

        Button openSettingsButton = findViewById(R.id.open_settings_button);
        openSettingsButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                // After long press, open settings
                Intent intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                return true;  // Indicate the long press was handled
            }
        });

        // Optional: Toast message if user presses, but doesn't hold long enough
        openSettingsButton.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Hold to open settings", Toast.LENGTH_SHORT).show()
        );

        // Create button list for the RecyclerView
        itemList = new ArrayList<>();
        itemList.add(new Item(R.drawable.vaziuoti, "Važiuoti"));
        itemList.add(new Item(R.drawable.eiti, "Eiti"));
        itemList.add(new Item(R.drawable.valgyti, "Valgyti"));
        itemList.add(new Item(R.drawable.drugelis, "Gerti"));
        itemList.add(new Item(R.drawable.drugelis, "Noriu"));
        itemList.add(new Item(R.drawable.drugelis, "Skauda"));
        itemList.add(new Item(R.drawable.drugelis, "Aš"));

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

    @Override
    public void onItemClick(Item item) {
        optionLayout.removeAllViews();

        List<Item> secondaryOptions = new ArrayList<>();

        switch (item.getText()) {
            case "Važiuoti":
                handleOption("Važiuoti");
                secondaryOptions = getSelectedOptions("Važiuoti");
                break;
            case "Eiti":
                handleOption("Eiti");
                secondaryOptions = getSelectedOptions("Eiti");
                break;
            case "Valgyti":
                handleOption("Valgyti");
                secondaryOptions = getSelectedOptions("Valgyti");
                break;
            case "Gerti":
                handleOption("Gerti");
                secondaryOptions = getSelectedOptions("Gerti");
                break;
            case "Noriu":
                handleOption("Noriu");
                secondaryOptions = getSelectedOptions("Noriu");
                break;
            case "Skauda":
                handleOption("Skauda");
                secondaryOptions = getSelectedOptions("Skauda");
                break;
            case "Aš":
                handleOption("Aš");
                secondaryOptions = getSelectedOptions("Aš");
                break;
            default:
                break;
        }

        // Show dynamically selected options
        if (!secondaryOptions.isEmpty()) {
            showSecondaryOptions(secondaryOptions);
        }
    }

    // Helper method to fetch selected options
    private List<Item> getSelectedOptions(String category) {
        List<Item> selectedOptions = new ArrayList<>();

        // Retrieve selected options from shared preferences
        SharedPreferences prefs = getSharedPreferences("PARENT_SETTINGS", MODE_PRIVATE);
        Set<String> selectedOptionSet = prefs.getStringSet(category, new HashSet<>());
        if (selectedOptionSet == null) {
            selectedOptionSet = new HashSet<>();  // Initialize with an empty set if no options are found
        }

        // Convert saved options into Item objects
        for (String option : selectedOptionSet) {
            switch (option) {
                case "Namo":
                    selectedOptions.add(new Item(R.drawable.namo, "Namo"));
                    break;
                case "Į parduotuvę":
                    selectedOptions.add(new Item(R.drawable.parduotuve, "Į parduotuvę"));
                    break;
                case "Į parką":
                    selectedOptions.add(new Item(R.drawable.parkas, "Į parką"));
                    break;
                case "Prie ežero":
                    selectedOptions.add(new Item(R.drawable.ezero, "Prie ežero"));
                    break;
                case "Į miestą":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Į miestą"));
                    break;
                case "Prie fontano":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Prie fontano"));
                    break;
                case "Pas močiutę Meilę":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Pas močiutę Meilę"));
                    break;
                case "Pas močiutę Valę":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Pas močiutę Valę"));
                    break;
                case "Į mišką":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Į mišką"));
                    break;
                case "Pasivaikščioti":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Pasivaikščioti"));
                    break;
                case "Į kiemą":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Į kiemą"));
                    break;
                case "Gaminti maistą":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Gaminti maistą"));
                    break;
                case "Sriubą":
                    selectedOptions.add(new Item(R.drawable.sriuba, "Sriubą"));
                    break;
                case "Mėsą":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Mėsą"));
                    break;
                case "Dešrelių":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Dešrelių"));
                    break;
                case "Bulvyčių":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Bulvyčių"));
                    break;
                case "Ledų":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Ledų"));
                    break;
                case "Guminukų":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Guminukų"));
                    break;
                case "Picą":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Picą"));
                    break;
                case "Makaronų":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Makaronų"));
                    break;
                case "Kiaušinių":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Kiaušinių"));
                    break;
                case "Salotų":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Salotų"));
                    break;
                case "Čipsų":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Čipsų"));
                    break;
                case "Troškinį":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Troškinį"));
                    break;
                case "Trapučių":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Trapučių"));
                    break;
                case "Šokinėti":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Šokinėti"));
                    break;
                case "Čiuožinėti":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Čiuožinėti"));
                    break;
                case "Laistyti":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Laistyti"));
                    break;
                case "Maudytis":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Maudytis"));
                    break;
                case "Persirengti":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Persirengti"));
                    break;
                case "Kartu pagulėti":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Kartu pagulėti"));
                    break;
                case "Į dušą":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Į dušą"));
                    break;
                case "Į tualetą":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Į tualetą"));
                    break;
                case "Miegoti":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Miegoti"));
                    break;
                case "Galvą":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Galvą"));
                    break;
                case "Dantuką":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Dantuką"));
                    break;
                case "Akytę":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Akytę"));
                    break;
                case "Gerklę":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Gerklę"));
                    break;
                case "Pilvuką":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Pilvuką"));
                    break;
                case "Ranką":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Ranką"));
                    break;
                case "Koją":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Koją"));
                    break;
                case "Siulčių":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Siulčių"));
                    break;
                case "Citrinų vandens":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Citrinų vandens"));
                    break;
                case "Vandens":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Vandens"));
                    break;
                case "Giros":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Giros"));
                    break;
                case "Limonado":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Limonado"));
                    break;
                case "Pavargęs":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Pavargęs"));
                    break;
                case "Piktas":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Piktas"));
                    break;
                case "Bijau":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Bijau"));
                    break;
                case "Liūdnas":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Liūdnas"));
                    break;
                case "Džiaugiuosi":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Džiaugiuosi"));
                    break;
                case "Alkanas":
                    selectedOptions.add(new Item(R.drawable.drugelis, "Alkanas"));
                    break;
            }
        }

        return selectedOptions;
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

    // Handle clicks for dynamically created secondary buttons
    private void handleSecondaryButtonClick(Item selectedItem) {
        // Clear the layout
        optionLayout.removeAllViews();

        // Display only the selected option
        List<Item> selectedItems = new ArrayList<>();
        selectedItems.add(selectedItem); // Only add the selected item to be shown

        // Call the method to show just the selected option
        showSelectedOption(selectedItems);

        // Voice the selected option
        speakText(selectedItem.getText());
    }

    private void showSelectedOption(List<Item> selectedItems) {
        optionLayout.removeAllViews(); // Clear previous options

        // Create a new RecyclerView for displaying the selected option
        RecyclerView selectedRecyclerView = new RecyclerView(this);
        selectedRecyclerView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        // Set up the layout manager for grid display (just 1 column since we are displaying only 1 item)
        selectedRecyclerView.setLayoutManager(new GridLayoutManager(this, 1));

        // Create and set the adapter for the selected option
        CustomAdapter selectedAdapter = new CustomAdapter(selectedItems, this, new CustomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Item item) {
                // Repeatedly speak the selected option when clicked
                speakText(item.getText());
            }
        });
        selectedRecyclerView.setAdapter(selectedAdapter);

        // Add the new RecyclerView to the option layout
        optionLayout.addView(selectedRecyclerView);
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
        CustomAdapter secondaryAdapter = new CustomAdapter(items, this, new CustomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Item item) {
                // When a secondary option is clicked, clear the other options and show only the selected one
                handleSecondaryButtonClick(item);
            }
        });
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