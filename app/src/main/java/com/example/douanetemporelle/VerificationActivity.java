package com.example.douanetemporelle;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class VerificationActivity extends AppCompatActivity {

    //  bch na3mlou test lel nombre premier
    public static boolean estPremier(int nombre) {

        if (nombre < 2) {
            return false;
        }

        for (int i = 2; i <= Math.sqrt(nombre); i++) {
            if (nombre % i == 0) {
                return false;
            }
        }

        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verification);

        //  bch norbtou les elements mte3 l fichier xml lel java
        TextView txtNom = findViewById(R.id.txt_nom_verif_rf);
        TextView txtAge = findViewById(R.id.txt_age_verif_rf);
        TextView txtMotif = findViewById(R.id.txt_motif_verif_rf);

        Button btnApprouver = findViewById(R.id.btn_approuver_rf);
        Button btnRefuser = findViewById(R.id.btn_refuser_rf);

        // bch nekhdhou les info mel MainActivity
        String nom = getIntent().getStringExtra(
                MainActivity.creerCle("3565", "NOM_VOYAGEUR"));

        int anneeNaissance = getIntent().getIntExtra(
                MainActivity.creerCle("3565", "ANNEE_NAISSANCE"), 0);

        int anneeDestination = getIntent().getIntExtra(
                MainActivity.creerCle("3565", "ANNEE_DESTINATION"), 0);

        String motif = getIntent().getStringExtra(
                MainActivity.creerCle("3565", "MOTIF_VOYAGE"));

        // bch na3mlou calculer l'âge
        int ageTemporel = anneeDestination - anneeNaissance;

        // bch na3mlou affiche l les info
        txtNom.setText("Nom : " + nom);
        txtAge.setText("Age temporel : " + ageTemporel + " ans");
        txtMotif.setText("Motif : " + motif);

        // bch na3mlou check lel age temporel
        if ("Modification du passé".equals(motif)
                && estPremier(ageTemporel)) {

            btnApprouver.setEnabled(false);

            Toast.makeText(this,
                    "Risque de paradoxe temporel !",
                    Toast.LENGTH_LONG).show();
        }

        // bch nconfirmiw l voyage
        btnApprouver.setOnClickListener(v -> {
            envoyerVerdict(true, nom);
        });

        // bch norfdhou l voyage
        btnRefuser.setOnClickListener(v -> {
            envoyerVerdict(false, nom);
        });
    }

    // bch naba3thou les resulta lel activity 3
    private void envoyerVerdict(boolean verdict, String nom) {

        Intent intent = new Intent(
                VerificationActivity.this,
                ResultActivity.class
        );

        intent.putExtra(
                MainActivity.creerCle("3565", "VERDICT_FINAL"),
                verdict
        );

        intent.putExtra(
                MainActivity.creerCle("3565", "NOM_VOYAGEUR"),
                nom
        );

        startActivity(intent);
    }
}
