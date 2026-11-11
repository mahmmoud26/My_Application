
package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class VerificationActivity extends Activity {

    TextView tvNom, tvNaissance, tvDestination;
    TextView tvAgeTemporel, tvMotif;
    Button btnApprouver, btnRefuser;

    String nomVoyageur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1 - عرض واجهة جواز السفر الزمني
        setContentView(R.layout.activity_verification);

        // 2 - ربط العناصر الموجودة في الواجهة
        tvNom = findViewById(R.id.tvNom);
        tvNaissance = findViewById(R.id.tvNaissance);
        tvDestination = findViewById(R.id.tvDestination);
        tvAgeTemporel = findViewById(R.id.tvAgeTemporel);
        tvMotif = findViewById(R.id.tvMotif);
        btnApprouver = findViewById(R.id.btnApprouver);
        btnRefuser = findViewById(R.id.btnRefuser);

        // 3 - استقبال معلومات المسافر من النشاط الأول
        Intent donnees = getIntent();

        nomVoyageur = donnees.getStringExtra("nom");
        String naissance = donnees.getStringExtra("naissance");
        String destination = donnees.getStringExtra("destination");
        String motif = donnees.getStringExtra("motif");

        // 4 - التحقق من وجود المعلومات
        if (nomVoyageur == null || naissance == null
                || destination == null || motif == null) {
            finish();
            return;
        }

        // 5 - حساب العمر الزمني للمسافر
        int ageTemporel;

        try {
            int anneeNaissance = Integer.parseInt(naissance);
            int anneeDestination = Integer.parseInt(destination);

            ageTemporel = anneeDestination - anneeNaissance;

        } catch (NumberFormatException e) {
            finish();
            return;
        }

        // 6 - عرض معلومات المسافر على جواز السفر
        tvNom.setText(nomVoyageur);

        tvNaissance.setText(
                "Année de naissance : " + naissance
        );

        tvDestination.setText(
                "Année de destination : " + destination
        );

        tvAgeTemporel.setText(
                "Âge temporel : " + ageTemporel + " ans"
        );

        tvMotif.setText(motif);

        // 7 - عند الموافقة على السفر الزمني
        btnApprouver.setOnClickListener(v -> {
            envoyerVerdict(true);
        });

        // 8 - عند رفض السفر بسبب خطر المفارقة
        btnRefuser.setOnClickListener(v -> {
            envoyerVerdict(false);
        });
    }

    // 9 - إرسال اسم المسافر والقرار للنشاط الثالث
    private void envoyerVerdict(boolean verdict) {

        Intent intent = new Intent(
                VerificationActivity.this,
                ResultatActivity.class
        );

        intent.putExtra("nom", nomVoyageur);
        intent.putExtra("verdict", verdict);

        startActivity(intent);
    }
}
