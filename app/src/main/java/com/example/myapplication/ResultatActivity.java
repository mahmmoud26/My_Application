
package com.example.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ResultatActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1 - استقبال اسم المسافر من النشاط الثاني
        String nom = getIntent().getStringExtra("nom");

        // 2 - استقبال القرار النهائي
        boolean verdict = getIntent()
                .getBooleanExtra("verdict", false);

        // 3 - إنشاء نص لعرض النتيجة
        TextView resultat = new TextView(this);

        // 4 - عرض الموافقة أو الرفض
        if (verdict) {
            resultat.setText(
                    "Voyageur : " + nom +
                            "\nSAUT TEMPOREL APPROUVÉ"
            );
        } else {
            resultat.setText(
                    "Voyageur : " + nom +
                            "\nSAUT TEMPOREL REFUSÉ"
            );
        }

        // 5 - إعداد شكل النص
        resultat.setTextSize(24);
        resultat.setPadding(30, 80, 30, 30);

        // 6 - عرض النتيجة على الشاشة
        setContentView(resultat);
    }
}
