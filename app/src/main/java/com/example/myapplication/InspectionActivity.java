
package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class InspectionActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1 - عرض اسم المسافر في الواجهة الثانية
        String nom = getIntent().getStringExtra("nom");

        TextView texte = new TextView(this);
        texte.setText("Inspection du voyageur : " + nom);
        texte.setTextSize(22);
        texte.setPadding(30, 50, 30, 30);

        setContentView(texte);
    }
}
