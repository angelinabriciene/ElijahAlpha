package com.example.elijahalpha;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button backButton;
//    private Button buttonAsNoriu;
    private ImageButton imageButtonAsNoriu;
//    private Button buttonMan;
    private ImageButton imageButtonMan;
    private LinearLayout optionLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageButtonAsNoriu = findViewById(R.id.imageButtonAsNoriu);
        imageButtonMan = findViewById(R.id.imageButtonMan);
        optionLayout = findViewById(R.id.option_layout);

        backButton = new Button(MainActivity.this);

        // Show the home screen initially
        showHomeScreen();

        // Create primary option buttons
//        Button valgytiButton = createButton("Valgyti...");
        ImageButton valgytiImageButton = createImageButton("Valgyti...", R.drawable.valgyti);
        Button eitiButton = createButton("Eiti...");
        Button sokinetiButton = createButton("Šokinėti...");
        Button vaziuotiButton = createButton("Važiuoti...");

        // Create primary option buttons for "MAN" -----------------------------------------------------------+++++++
        Button skaudaButton = createButton("Skauda...");
        Button saltaButton = createButton("Šalta");
        Button karstaButton = createButton("Karšta");
        Button nepatoguButton = createButton("Nepatogu");

        // Secondary option buttons for "Valgyti"
        Button sriubaButton = createButton("Sriubą");
        Button makaronaiButton = createButton("Makaronus");

        // Secondary option buttons for "Eiti"
        Button laukaButton = createButton("Į lauką");
        Button namoButton = createButton("Namo");

        // Secondary option buttons for "Šokinėti" ---------------------------------------------------------
        Button batutoButton = createButton("Ant batuto");
        Button lovosButton = createButton("Ant lovos");

        // Secondary option buttons for "Važiuoti" -----------------------------------------------------------
        Button mociuteButton = createButton("Pas močiutę");
        Button parduotuveButton = createButton("Į parduotuvę");

        // Secondary option buttons for "Skauda" -----------------------------------------------------------+++++++
        Button galvaButton = createButton("Galvą");
        Button rankaButton = createButton("Ranką");
        Button pilvasButton = createButton("Pilvą");

        // Create a dynamic "Back" button
        Button backButton = createButton("Grįžti į pradinį meniu");

        // Show primary options when "AŠ NORIU" is clicked
        imageButtonAsNoriu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "AŠ NORIU clicked!", Toast.LENGTH_SHORT).show();

                // Hide both "AŠ NORIU..." and "MAN..." buttons
                imageButtonAsNoriu.setVisibility(View.GONE);
                imageButtonMan.setVisibility(View.GONE);

                // Show primary options
                showPrimaryOptions(optionLayout, eitiButton, sokinetiButton, vaziuotiButton);
                valgytiImageButton.setVisibility(View.VISIBLE);
                optionLayout.addView(valgytiImageButton);

                optionLayout.addView(backButton);
            }
        });

        // Show primary options when "MAN" is clicked ------------------------------------------------------+++
        imageButtonMan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "MAN clicked!", Toast.LENGTH_SHORT).show();

                // Hide both "AŠ NORIU..." and "MAN..." buttons
                imageButtonAsNoriu.setVisibility(View.GONE);
                imageButtonMan.setVisibility(View.GONE);

                // Show primary options
                showPrimaryOptions(optionLayout, skaudaButton, saltaButton, karstaButton, nepatoguButton);
                optionLayout.addView(backButton);
            }
        });

        // Click listener for "Valgyti"
        valgytiImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Valgyti clicked!", Toast.LENGTH_SHORT).show();
                optionLayout.removeAllViews(); // Clear current screen

                // Show secondary options
                optionLayout.addView(sriubaButton);
                optionLayout.addView(makaronaiButton);
                optionLayout.addView(backButton);
            }
        });

        // Click listener for "Eiti"
        eitiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Eiti clicked!", Toast.LENGTH_SHORT).show();
                optionLayout.removeAllViews(); // Clear current screen

                // Show secondary options
                optionLayout.addView(laukaButton);
                optionLayout.addView(namoButton);
                optionLayout.addView(backButton);
            }
        });

        // Click listener for "Šokinėti" ------------------------------------------------------------------
        sokinetiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Šokinėti clicked!", Toast.LENGTH_SHORT).show();
                optionLayout.removeAllViews(); // Clear current screen


                // Show secondary options
                optionLayout.addView(batutoButton);
                optionLayout.addView(lovosButton);
                optionLayout.addView(backButton);
            }
        });

        // Click listener for "Važiuoti" -----------------------------------------------------------------
        vaziuotiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Važiuoti clicked!", Toast.LENGTH_SHORT).show();
                optionLayout.removeAllViews(); // Clear current screen

                // Show secondary options
                optionLayout.addView(mociuteButton);
                optionLayout.addView(parduotuveButton);
                optionLayout.addView(backButton);
            }
        });

        // Click listener for "Skauda" -------------------------------------------------------------------- ++++
        skaudaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Skauda clicked!", Toast.LENGTH_SHORT).show();
                optionLayout.removeAllViews(); // Clear current screen

                // Show secondary options
                optionLayout.addView(galvaButton);
                optionLayout.addView(rankaButton);
                optionLayout.addView(pilvasButton);
                optionLayout.addView(backButton);  // Add back button at the end
            }
        });

        // Click listeners for secondary options (example for "Sriubą")
        sriubaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Sriubą clicked!", Toast.LENGTH_SHORT).show();
                optionLayout.removeAllViews(); // Clear current screen

                // Show the Back button (but it goes back to the home screen)
                optionLayout.addView(backButton);
            }
        });

        makaronaiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Makaronus clicked!", Toast.LENGTH_SHORT).show();
                optionLayout.removeAllViews(); // Clear current screen

                // Show the Back button (but it goes back to the home screen)
                optionLayout.addView(backButton);
            }
        });

        laukaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Į lauką clicked!", Toast.LENGTH_SHORT).show();
                optionLayout.removeAllViews(); // Clear current screen

                // Show the Back button (but it goes back to the home screen)
                optionLayout.addView(backButton);
            }
        });

        namoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Namo clicked!", Toast.LENGTH_SHORT).show();
                optionLayout.removeAllViews(); // Clear current screen

                // Show the Back button (but it goes back to the home screen)
                optionLayout.addView(backButton);            }
        });

        batutoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Ant batuto clicked!", Toast.LENGTH_SHORT).show();
                optionLayout.removeAllViews(); // Clear current screen

                // Show the Back button (but it goes back to the home screen)
                optionLayout.addView(backButton);            }
        });

        lovosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Ant lovos clicked!", Toast.LENGTH_SHORT).show();
                optionLayout.removeAllViews(); // Clear current screen

                // Show the Back button (but it goes back to the home screen)
                optionLayout.addView(backButton);            }
        });

        mociuteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Pas močiutę clicked!", Toast.LENGTH_SHORT).show();
                optionLayout.removeAllViews(); // Clear current screen

                // Show the Back button (but it goes back to the home screen)
                optionLayout.addView(backButton);            }
        });

        parduotuveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Į parduotuvę clicked!", Toast.LENGTH_SHORT).show();
                optionLayout.removeAllViews(); // Clear current screen

                // Show the Back button (but it goes back to the home screen)
                optionLayout.addView(backButton);            }
        });

        saltaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Šalta clicked!", Toast.LENGTH_SHORT).show();
                optionLayout.removeAllViews(); // Clear current screen

                // Show the Back button (but it goes back to the home screen)
                optionLayout.addView(backButton);            }
        });

        karstaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Karšta clicked!", Toast.LENGTH_SHORT).show();
                optionLayout.removeAllViews(); // Clear current screen

                // Show the Back button (but it goes back to the home screen)
                optionLayout.addView(backButton);            }
        });

        nepatoguButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Nepatogu clicked!", Toast.LENGTH_SHORT).show();
                optionLayout.removeAllViews(); // Clear current screen

                // Show the Back button (but it goes back to the home screen)
                optionLayout.addView(backButton);            }
        });

        galvaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Galvą clicked!", Toast.LENGTH_SHORT).show();
                optionLayout.removeAllViews(); // Clear current screen

                // Show the Back button (but it goes back to the home screen)
                optionLayout.addView(backButton);            }
        });

        rankaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Ranką clicked!", Toast.LENGTH_SHORT).show();
                optionLayout.removeAllViews(); // Clear current screen

                // Show the Back button (but it goes back to the home screen)
                optionLayout.addView(backButton);            }
        });

        pilvasButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Pilvą clicked!", Toast.LENGTH_SHORT).show();
                optionLayout.removeAllViews(); // Clear current screen

                // Show the Back button (but it goes back to the home screen)
                optionLayout.addView(backButton);            }
        });


        // Back button functionality
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHomeScreen();  // Go back to the home screen when Back is clicked
            }
        });
    }

    // Helper method to show the home screen with "Aš Noriu" and "Man"
    private void showHomeScreen() {
        // Hide all buttons and layout
        optionLayout.removeAllViews();
        imageButtonAsNoriu.setVisibility(View.VISIBLE);
        imageButtonMan.setVisibility(View.VISIBLE);
        backButton.setVisibility(View.GONE);
    }

    // Helper function to create buttons dynamically
    private Button createButton(String text) {
        Button button = new Button(MainActivity.this);
        button.setText(text);
        button.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        return button;
    }

    private ImageButton createImageButton(String contentDescription, int imageResource) {
        ImageButton imageButton = new ImageButton(MainActivity.this);
        imageButton.setContentDescription(contentDescription);
        imageButton.setImageResource(imageResource); // Set drawable resource
        imageButton.setScaleType(ImageView.ScaleType.CENTER_CROP); // Adjust as needed
        return imageButton;
    }

    // Helper function to hide all primary options
    private void hidePrimaryOptions(LinearLayout optionLayout, Button... buttons) {
        optionLayout.removeAllViews(); // Clear all views first
        for (Button button : buttons) {
            button.setVisibility(View.GONE);
        }
    }

    // Helper function to show primary options
    private void showPrimaryOptions(LinearLayout layout, Button... buttons) {
        optionLayout.removeAllViews(); // Clear all views first
        for (Button button : buttons) {
            optionLayout.addView(button);
        }
    }
}