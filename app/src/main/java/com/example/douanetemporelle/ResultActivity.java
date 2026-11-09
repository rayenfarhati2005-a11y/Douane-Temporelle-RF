package com.example.douanetemporelle;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // bch norbtou les elements mte3 l fichier xml lel java
        LinearLayout layout = findViewById(R.id.layout_result_rf);
        TextView txtResultat = findViewById(R.id.txt_resultat_rf);
        Button btnNouveau = findViewById(R.id.btn_nouveau_rf);

        // bch na3mlou récupération l nom w résultat
        boolean verdict = getIntent().getBooleanExtra(
                MainActivity.creerCle("3565", "VERDICT_FINAL"),
                false
        );

        String nom = getIntent().getStringExtra(
                MainActivity.creerCle("3565", "NOM_VOYAGEUR")
        );

        // bch nchoufo resultat w nbadlou loun l background
        if (verdict) {

            layout.setBackgroundColor(Color.GREEN);

            txtResultat.setText("Bon voyage " + nom + " !");

        } else {

            layout.setBackgroundColor(Color.RED);

            txtResultat.setText(
                    nom + " est en état d'arrestation temporelle !"
            );
        }

        // bch narj3ou l act1
        btnNouveau.setOnClickListener(v -> {

            Intent intent = new Intent(
                    ResultActivity.this,
                    MainActivity.class
            );

            intent.setFlags(
                    Intent.FLAG_ACTIVITY_CLEAR_TOP |
                            Intent.FLAG_ACTIVITY_SINGLE_TOP
            );

            startActivity(intent);

            finish();
        });
    }
}