package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // bch na3mlou définition mte3 l clé
    public static String creerCle(String matricule, String nomCle) {
        return matricule + "_" + nomCle;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // bch norbtou les elements mte3 l fichier xml lel java
        EditText edtNom = findViewById(R.id.edt_nom_rf);
        EditText edtNaissance = findViewById(R.id.edt_naissance_rf);
        EditText edtDestination = findViewById(R.id.edt_destination_rf);
        Spinner spinnerMotif = findViewById(R.id.spinner_motif_rf);
        Button btnInspecter = findViewById(R.id.btn_inspecter_rf);

        // bch nzidou les option mte3 l voyage
        String[] motifs = {
                "Tourisme",
                "Modification du passé",
                "Fuite"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                motifs
        );

        adapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );

        spinnerMotif.setAdapter(adapter);


        btnInspecter.setOnClickListener(v -> {

            String nom = edtNom.getText().toString().trim();
            String naissance = edtNaissance.getText().toString().trim();
            String destination = edtDestination.getText().toString().trim();
            String motif = spinnerMotif.getSelectedItem().toString();

            // bch na3mlou test lel champs vide b Toast
            if (nom.isEmpty() || naissance.isEmpty() || destination.isEmpty()) {
                Toast.makeText(this,
                        "Veuillez remplir tous les champs",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            int anneeNaissance;
            int anneeDestination;

            try {
                anneeNaissance = Integer.parseInt(naissance);
                anneeDestination = Integer.parseInt(destination);
            } catch (NumberFormatException e) {
                Toast.makeText(this, "Année invalidee", Toast.LENGTH_SHORT).show();
                return;
            }

            if (anneeNaissance > anneeDestination) {
                Toast.makeText(this,
                        "Année de destination invalide",
                        Toast.LENGTH_SHORT).show();
                return;
            }

            // bch na3mlou Intent elli bch ta3mel l communication bin act1 w act2
            Intent intent = new Intent(MainActivity.this, VerificationActivity.class);

            // bch naba3thou les info lel activity thenya
            intent.putExtra(creerCle("3565", "NOM_VOYAGEUR"), nom);
            intent.putExtra(creerCle("3565", "ANNEE_NAISSANCE"), anneeNaissance);
            intent.putExtra(creerCle("3565", "ANNEE_DESTINATION"), anneeDestination);
            intent.putExtra(creerCle("3565", "MOTIF_VOYAGE"), motif);

            startActivity(intent);
        });
    }
}