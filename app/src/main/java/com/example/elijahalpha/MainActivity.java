package com.example.elijahalpha;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;

public class MainActivity extends AppCompatActivity implements CustomAdapter.OnItemClickListener {

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
    }

    // Handle RecyclerView item clicks
    @Override
    public void onItemClick(Item item) {
        optionLayout.removeAllViews();

        switch (item.getText()) {
            case "Valgyti":
                // Show secondary options for "Valgyti"
                optionLayout.addView(createButton("Sriuba"));
                optionLayout.addView(createButton("Makaronai"));
                optionLayout.addView(createButton("Atgal")); // Back button
                break;
            case "Eiti":
                // Show secondary options for "Eiti"
                optionLayout.addView(createButton("Į lauką"));
                optionLayout.addView(createButton("Namo"));
                optionLayout.addView(createButton("Atgal")); // Back button
                break;
            case "Šokinėti":
                // If No secondary options for this example, just showing a message
                optionLayout.addView(createButton("Šokinėju!"));
                optionLayout.addView(createButton("Atgal")); // Back button
                break;
            case "Važiuoti":
                // Show secondary options for "Važiuoti"
                optionLayout.addView(createButton("Į mokyklą"));
                optionLayout.addView(createButton("Į parką"));
                optionLayout.addView(createButton("Atgal")); // Back button
                break;
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
}