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

    private CheckBox checkboxNamo, checkboxParduotuve, checkboxParkas, checkboxEzero,
            checkboxMiesta, checkboxFontano, checkboxMociuteMeile, checkboxMociuteVale,
            checkboxMiska, checkboxPasivaikscioti, checkboxGamintiMaista, checkboxSriuba,
            checkboxMesa, checkboxDesreles, checkboxBulvytes, checkboxLedu, checkboxGuminuku,
            checkboxPica, checkboxMakaronu, checkboxKiausiniu, checkboxSalotu, checkboxCipsu,
            checkboxTroskini, checkboxTrapuciu, checkboxSokineti, checkboxCiuozineti,
            checkboxLaistyti, checkboxMaudytis, checkboxPersirengti, checkboxKartuPagulei,
            checkboxDusas, checkboxTualetas, checkboxMiegoti, checkboxGalva, checkboxDantuka,
            checkboxAkyte, checkboxPilvuka, checkboxRanka, checkboxKoja, checkboxPavarges, checkboxPiktas, checkboxBijau,
            checkboxLiudnas, checkboxDziaugiuosi, checkboxKiema, checkboxAlkanas, checkboxGerkle,
            checkboxSultys, checkboxCitrinuVanduo, checkboxVanduo, checkboxGira, checkboxLimonadas;

    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Initialize checkboxes
        checkboxNamo = findViewById(R.id.checkbox_namo);
        checkboxParduotuve = findViewById(R.id.checkbox_parduotuve);
        checkboxParkas = findViewById(R.id.checkbox_parkas);
        checkboxEzero = findViewById(R.id.checkbox_ezero);
        checkboxMiesta = findViewById(R.id.checkbox_miesta);
        checkboxFontano = findViewById(R.id.checkbox_fontano);
        checkboxMociuteMeile = findViewById(R.id.checkbox_mociute_meile);
        checkboxMociuteVale = findViewById(R.id.checkbox_mociute_vale);
        checkboxMiska = findViewById(R.id.checkbox_miska);
        checkboxPasivaikscioti = findViewById(R.id.checkbox_pasivaikscioti);
        checkboxKiema = findViewById(R.id.checkbox_kiema);
        checkboxGamintiMaista = findViewById(R.id.checkbox_gaminti_maista);
        checkboxSriuba = findViewById(R.id.checkbox_sriuba);
        checkboxMesa = findViewById(R.id.checkbox_mesa);
        checkboxDesreles = findViewById(R.id.checkbox_desreles);
        checkboxBulvytes = findViewById(R.id.checkbox_bulvytes);
        checkboxLedu = findViewById(R.id.checkbox_ledu);
        checkboxGuminuku = findViewById(R.id.checkbox_guminuku);
        checkboxPica = findViewById(R.id.checkbox_pica);
        checkboxMakaronu = findViewById(R.id.checkbox_makaronu);
        checkboxKiausiniu = findViewById(R.id.checkbox_kiausiniu);
        checkboxSalotu = findViewById(R.id.checkbox_salotu);
        checkboxCipsu = findViewById(R.id.checkbox_cipsu);
        checkboxTroskini = findViewById(R.id.checkbox_troskini);
        checkboxTrapuciu = findViewById(R.id.checkbox_trapuciu);
        checkboxSokineti = findViewById(R.id.checkbox_sokineti);
        checkboxCiuozineti = findViewById(R.id.checkbox_ciuozineti);
        checkboxLaistyti = findViewById(R.id.checkbox_laistyti);
        checkboxMaudytis = findViewById(R.id.checkbox_maudytis);
        checkboxPersirengti = findViewById(R.id.checkbox_persirengti);
        checkboxKartuPagulei = findViewById(R.id.checkbox_kartu_paguleti);
        checkboxDusas = findViewById(R.id.checkbox_dusas);
        checkboxTualetas = findViewById(R.id.checkbox_tualetas);
        checkboxMiegoti = findViewById(R.id.checkbox_miegoti);
        checkboxGalva = findViewById(R.id.checkbox_galva);
        checkboxDantuka = findViewById(R.id.checkbox_dantuka);
        checkboxAkyte = findViewById(R.id.checkbox_akyte);
        checkboxGerkle = findViewById(R.id.checkbox_gerkle);
        checkboxPilvuka = findViewById(R.id.checkbox_pilvuka);
        checkboxRanka = findViewById(R.id.checkbox_ranka);
        checkboxKoja = findViewById(R.id.checkbox_koja);
        checkboxSultys = findViewById(R.id.checkbox_sultciu);
        checkboxCitrinuVanduo = findViewById(R.id.checkbox_citrinu_vandens);
        checkboxVanduo = findViewById(R.id.checkbox_vandens);
        checkboxGira = findViewById(R.id.checkbox_giros);
        checkboxLimonadas = findViewById(R.id.checkbox_limonado);
        checkboxPavarges = findViewById(R.id.checkbox_pavarges);
        checkboxPiktas = findViewById(R.id.checkbox_piktas);
        checkboxBijau = findViewById(R.id.checkbox_bijau);
        checkboxLiudnas = findViewById(R.id.checkbox_liudnas);
        checkboxDziaugiuosi = findViewById(R.id.checkbox_dziaugiuosi);
        checkboxAlkanas = findViewById(R.id.checkbox_alkanas);

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

        checkboxNamo.setChecked(sharedPreferences.getBoolean("namo_enabled", true));
        checkboxParduotuve.setChecked(sharedPreferences.getBoolean("parduotuve_enabled", true));
        checkboxParkas.setChecked(sharedPreferences.getBoolean("parkas_enabled", true));
        checkboxEzero.setChecked(sharedPreferences.getBoolean("ezero_enabled", true));
        checkboxMiesta.setChecked(sharedPreferences.getBoolean("miesta_enabled", true));
        checkboxFontano.setChecked(sharedPreferences.getBoolean("fontano_enabled", true));
        checkboxMociuteMeile.setChecked(sharedPreferences.getBoolean("mociute_meile_enabled", true));
        checkboxMociuteVale.setChecked(sharedPreferences.getBoolean("mociute_vale_enabled", true));
        checkboxMiska.setChecked(sharedPreferences.getBoolean("miska_enabled", true));
        checkboxPasivaikscioti.setChecked(sharedPreferences.getBoolean("pasivaikscioti_enabled", true));
        checkboxKiema.setChecked(sharedPreferences.getBoolean("kiema_enabled", true));
        checkboxGamintiMaista.setChecked(sharedPreferences.getBoolean("gaminti_maista_enabled", true));
        checkboxSriuba.setChecked(sharedPreferences.getBoolean("sriuba_enabled", true));
        checkboxMesa.setChecked(sharedPreferences.getBoolean("mesa_enabled", true));
        checkboxDesreles.setChecked(sharedPreferences.getBoolean("desreles_enabled", true));
        checkboxBulvytes.setChecked(sharedPreferences.getBoolean("bulvytes_enabled", true));
        checkboxLedu.setChecked(sharedPreferences.getBoolean("ledu_enabled", true));
        checkboxGuminuku.setChecked(sharedPreferences.getBoolean("guminuku_enabled", true));
        checkboxPica.setChecked(sharedPreferences.getBoolean("pica_enabled", true));
        checkboxMakaronu.setChecked(sharedPreferences.getBoolean("makaronu_enabled", true));
        checkboxKiausiniu.setChecked(sharedPreferences.getBoolean("kiausiniu_enabled", true));
        checkboxSalotu.setChecked(sharedPreferences.getBoolean("salotu_enabled", true));
        checkboxCipsu.setChecked(sharedPreferences.getBoolean("cipsu_enabled", true));
        checkboxTroskini.setChecked(sharedPreferences.getBoolean("troskini_enabled", true));
        checkboxTrapuciu.setChecked(sharedPreferences.getBoolean("trapuciu_enabled", true));
        checkboxSokineti.setChecked(sharedPreferences.getBoolean("sokineti_enabled", true));
        checkboxCiuozineti.setChecked(sharedPreferences.getBoolean("ciuozineti_enabled", true));
        checkboxLaistyti.setChecked(sharedPreferences.getBoolean("laistyti_enabled", true));
        checkboxMaudytis.setChecked(sharedPreferences.getBoolean("maudytis_enabled", true));
        checkboxPersirengti.setChecked(sharedPreferences.getBoolean("persirengti_enabled", true));
        checkboxKartuPagulei.setChecked(sharedPreferences.getBoolean("kartu_paguleti_enabled", true));
        checkboxDusas.setChecked(sharedPreferences.getBoolean("dusas_enabled", true));
        checkboxTualetas.setChecked(sharedPreferences.getBoolean("tualetas_enabled", true));
        checkboxMiegoti.setChecked(sharedPreferences.getBoolean("miegoti_enabled", true));
        checkboxGalva.setChecked(sharedPreferences.getBoolean("galva_enabled", true));
        checkboxDantuka.setChecked(sharedPreferences.getBoolean("dantuka_enabled", true));
        checkboxAkyte.setChecked(sharedPreferences.getBoolean("akyte_enabled", true));
        checkboxGerkle.setChecked(sharedPreferences.getBoolean("gerkle_enabled", true));
        checkboxPilvuka.setChecked(sharedPreferences.getBoolean("pilvuka_enabled", true));
        checkboxRanka.setChecked(sharedPreferences.getBoolean("ranka_enabled", true));
        checkboxKoja.setChecked(sharedPreferences.getBoolean("koja_enabled", true));
        checkboxSultys.setChecked(sharedPreferences.getBoolean("sultciu_enabled", true));
        checkboxCitrinuVanduo.setChecked(sharedPreferences.getBoolean("citrinu_vandens_enabled", true));
        checkboxVanduo.setChecked(sharedPreferences.getBoolean("vanduo_enabled", true));
        checkboxGira.setChecked(sharedPreferences.getBoolean("gira_enabled", true));
        checkboxLimonadas.setChecked(sharedPreferences.getBoolean("limonado_enabled", true));
        checkboxPavarges.setChecked(sharedPreferences.getBoolean("pavarges_enabled", true));
        checkboxPiktas.setChecked(sharedPreferences.getBoolean("piktas_enabled", true));
        checkboxBijau.setChecked(sharedPreferences.getBoolean("bijau_enabled", true));
        checkboxLiudnas.setChecked(sharedPreferences.getBoolean("liudnas_enabled", true));
        checkboxDziaugiuosi.setChecked(sharedPreferences.getBoolean("dziaugiuosi_enabled", true));
        checkboxAlkanas.setChecked(sharedPreferences.getBoolean("alkanas_enabled", true));
    }


    private void savePreferences() {
        SharedPreferences sharedPreferences = getSharedPreferences("app_settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean("namo_enabled", checkboxNamo.isChecked());
        editor.putBoolean("parduotuve_enabled", checkboxParduotuve.isChecked());
        editor.putBoolean("parkas_enabled", checkboxParkas.isChecked());
        editor.putBoolean("ezero_enabled", checkboxEzero.isChecked());
        editor.putBoolean("miesta_enabled", checkboxMiesta.isChecked());
        editor.putBoolean("fontano_enabled", checkboxFontano.isChecked());
        editor.putBoolean("mociuteMeile_enabled", checkboxMociuteMeile.isChecked());
        editor.putBoolean("mociuteVale_enabled", checkboxMociuteVale.isChecked());
        editor.putBoolean("miska_enabled", checkboxMiska.isChecked());
        editor.putBoolean("pasivaikscioti_enabled", checkboxPasivaikscioti.isChecked());
        editor.putBoolean("kiemą_enabled", checkboxKiema.isChecked());
        editor.putBoolean("gaminti_maistą_enabled", checkboxGamintiMaista.isChecked());
        editor.putBoolean("sriuba_enabled", checkboxSriuba.isChecked());
        editor.putBoolean("mesa_enabled", checkboxMesa.isChecked());
        editor.putBoolean("desreles_enabled", checkboxDesreles.isChecked());
        editor.putBoolean("bulvytes_enabled", checkboxBulvytes.isChecked());
        editor.putBoolean("ledu_enabled", checkboxLedu.isChecked());
        editor.putBoolean("guminukai_enabled", checkboxGuminuku.isChecked());
        editor.putBoolean("pica_enabled", checkboxPica.isChecked());
        editor.putBoolean("makaronai_enabled", checkboxMakaronu.isChecked());
        editor.putBoolean("kiausiniai_enabled", checkboxKiausiniu.isChecked());
        editor.putBoolean("salotos_enabled", checkboxSalotu.isChecked());
        editor.putBoolean("chipsai_enabled", checkboxCipsu.isChecked());
        editor.putBoolean("troskinys_enabled", checkboxTroskini.isChecked());
        editor.putBoolean("trapuciai_enabled", checkboxTrapuciu.isChecked());
        editor.putBoolean("sokineti_enabled", checkboxSokineti.isChecked());
        editor.putBoolean("ciuoztinėti_enabled", checkboxCiuozineti.isChecked());
        editor.putBoolean("laistyti_enabled", checkboxLaistyti.isChecked());
        editor.putBoolean("maudytis_enabled", checkboxMaudytis.isChecked());
        editor.putBoolean("persirengti_enabled", checkboxPersirengti.isChecked());
        editor.putBoolean("kartu_paguleti_enabled", checkboxKartuPagulei.isChecked());
        editor.putBoolean("i_dusa_enabled", checkboxDusas.isChecked());
        editor.putBoolean("i_tualeta_enabled", checkboxTualetas.isChecked());
        editor.putBoolean("miegoti_enabled", checkboxMiegoti.isChecked());
        editor.putBoolean("galva_enabled", checkboxGalva.isChecked());
        editor.putBoolean("dantuka_enabled", checkboxDantuka.isChecked());
        editor.putBoolean("akyte_enabled", checkboxAkyte.isChecked());
        editor.putBoolean("gerkle_enabled", checkboxGerkle.isChecked());
        editor.putBoolean("pilvuka_enabled", checkboxPilvuka.isChecked());
        editor.putBoolean("ranka_enabled", checkboxRanka.isChecked());
        editor.putBoolean("koja_enabled", checkboxKoja.isChecked());
        editor.putBoolean("siulciu_enabled", checkboxSultys.isChecked());
        editor.putBoolean("citrinu_vandens_enabled", checkboxCitrinuVanduo.isChecked());
        editor.putBoolean("vanduo_enabled", checkboxVanduo.isChecked());
        editor.putBoolean("giros_enabled", checkboxGira.isChecked());
        editor.putBoolean("limonadas_enabled", checkboxLimonadas.isChecked());
        editor.putBoolean("pavarges_enabled", checkboxPavarges.isChecked());
        editor.putBoolean("piktas_enabled", checkboxPiktas.isChecked());
        editor.putBoolean("bijau_enabled", checkboxBijau.isChecked());
        editor.putBoolean("liudnas_enabled", checkboxLiudnas.isChecked());
        editor.putBoolean("dziaugiuosi_enabled", checkboxDziaugiuosi.isChecked());
        editor.putBoolean("alkanas_enabled", checkboxAlkanas.isChecked());


        editor.apply();
    }

    private void saveSelectedOptions() {
        SharedPreferences prefs = getSharedPreferences("PARENT_SETTINGS", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        Set<String> valgytiOptions = new HashSet<>();
        if (checkboxSriuba.isChecked()) {
            valgytiOptions.add("Sriuba");
        }
        if (checkboxMakaronu.isChecked()) {
            valgytiOptions.add("Makaronų");
        }
        if (checkboxMesa.isChecked()) {
            valgytiOptions.add("Mėsą");
        }
        if (checkboxDesreles.isChecked()) {
            valgytiOptions.add("Dešrelių");
        }
        if (checkboxBulvytes.isChecked()) {
            valgytiOptions.add("Bulvyčių");
        }
        if (checkboxLedu.isChecked()) {
            valgytiOptions.add("Ledų");
        }
        if (checkboxGuminuku.isChecked()) {
            valgytiOptions.add("Guminukų");
        }
        if (checkboxPica.isChecked()) {
            valgytiOptions.add("Picą");
        }
        if (checkboxKiausiniu.isChecked()) {
            valgytiOptions.add("Kiaušinių");
        }
        if (checkboxSalotu.isChecked()) {
            valgytiOptions.add("Salotų");
        }
        if (checkboxCipsu.isChecked()) {
            valgytiOptions.add("Čipsų");
        }
        if (checkboxTroskini.isChecked()) {
            valgytiOptions.add("Troškinį");
        }
        if (checkboxTrapuciu.isChecked()) {
            valgytiOptions.add("Trapučių");
        }
        editor.putStringSet("Valgyti", valgytiOptions);

        // Save selected options for "Važiuoti"
        Set<String> vaziuotiOptions = new HashSet<>();
        if (checkboxNamo.isChecked()) {
            vaziuotiOptions.add("Namo");
        }
        if (checkboxParduotuve.isChecked()) {
            vaziuotiOptions.add("Į Parduotuvę");
        }
        if (checkboxParkas.isChecked()) {
            vaziuotiOptions.add("Į parką");
        }
        if (checkboxEzero.isChecked()) {
            vaziuotiOptions.add("Prie ežero");
        }
        if (checkboxMiesta.isChecked()) {
            vaziuotiOptions.add("Į miestą");
        }
        if (checkboxFontano.isChecked()) {
            vaziuotiOptions.add("Prie fontano");
        }
        if (checkboxMociuteMeile.isChecked()) {
            vaziuotiOptions.add("Pas močiutę Meilę");
        }
        if (checkboxMociuteVale.isChecked()) {
            vaziuotiOptions.add("Pas močiutę Valę");
        }
        editor.putStringSet("Važiuoti", vaziuotiOptions);


        // Save selected options for "Eiti"
        Set<String> eitiOptions = new HashSet<>();
        if (checkboxMiska.isChecked()) {
            eitiOptions.add("Į mišką");
        }
        if (checkboxPasivaikscioti.isChecked()) {
            eitiOptions.add("Pasivaikščioti");
        }
        if (checkboxKiema.isChecked()) {
            eitiOptions.add("Į Kiemą");
        }
        if (checkboxGamintiMaista.isChecked()) {
            eitiOptions.add("Gaminti maistą");
        }
        editor.putStringSet("Eiti", eitiOptions);

        // Save selected options for "Noriu"
        Set<String> noriuOptions = new HashSet<>();
        if (checkboxSokineti.isChecked()) {
            noriuOptions.add("Šokinėti");
        }
        if (checkboxCiuozineti.isChecked()) {
            noriuOptions.add("Čiuožinėti");
        }
        if (checkboxLaistyti.isChecked()) {
            noriuOptions.add("Laistyti");
        }
        if (checkboxMaudytis.isChecked()) {
            noriuOptions.add("Maudytis");
        }
        if (checkboxPersirengti.isChecked()) {
            noriuOptions.add("Persirengti");
        }
        if (checkboxKartuPagulei.isChecked()) {
            noriuOptions.add("Kartu pagulėti");
        }
        if (checkboxDusas.isChecked()) {
            noriuOptions.add("Į dušą");
        }
        if (checkboxTualetas.isChecked()) {
            noriuOptions.add("Į tualetą");
        }
        if (checkboxMiegoti.isChecked()) {
            noriuOptions.add("Miegoti");
        }
        editor.putStringSet("Noriu", noriuOptions);

        // Save other categories
        Set<String> skaudaOptions = new HashSet<>();
        if (checkboxGalva.isChecked()) {
            skaudaOptions.add("Galvą");
        }
        if (checkboxDantuka.isChecked()) {
            skaudaOptions.add("Dantuką");
        }
        if (checkboxAkyte.isChecked()) {
            skaudaOptions.add("Akytę");
        }
        if (checkboxGerkle.isChecked()) {
            skaudaOptions.add("Gerklę");
        }
        if (checkboxPilvuka.isChecked()) {
            skaudaOptions.add("Pilvuką");
        }
        if (checkboxRanka.isChecked()) {
            skaudaOptions.add("Ranką");
        }
        if (checkboxKoja.isChecked()) {
            skaudaOptions.add("Koją");
        }
        editor.putStringSet("Skauda", skaudaOptions);

        // Save drink options
        Set<String> gertiOptions = new HashSet<>();
        if (checkboxSultys.isChecked()) {
            gertiOptions.add("Siulčių");
        }
        if (checkboxCitrinuVanduo.isChecked()) {
            gertiOptions.add("Citrinų vandens");
        }
        if (checkboxVanduo.isChecked()) {
            gertiOptions.add("Vandens");
        }
        if (checkboxGira.isChecked()) {
            gertiOptions.add("Giros");
        }
        if (checkboxLimonadas.isChecked()) {
            gertiOptions.add("Limonado");
        }
        editor.putStringSet("Gėrimai", gertiOptions);

        // Save emotional state options
        Set<String> asOptions = new HashSet<>();
        if (checkboxPavarges.isChecked()) {
            asOptions.add("Pavargęs");
        }
        if (checkboxPiktas.isChecked()) {
            asOptions.add("Piktas");
        }
        if (checkboxBijau.isChecked()) {
            asOptions.add("Bijau");
        }
        if (checkboxLiudnas.isChecked()) {
            asOptions.add("Liūdnas");
        }
        if (checkboxDziaugiuosi.isChecked()) {
            asOptions.add("Džiaugiuosi");
        }
        if (checkboxAlkanas.isChecked()) {
            asOptions.add("Alkanas");
        }
        editor.putStringSet("Emocijos", asOptions);

        editor.apply();  // Save changes
    }
}
