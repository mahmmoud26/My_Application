
package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivityJava extends Activity {

    EditText nom, naissance, destination;
    RadioGroup motif;
    Button inspecter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1 - ربط عناصر الواجهة
        nom = findViewById(R.id.etNom);
        naissance = findViewById(R.id.etNaissance);
        destination = findViewById(R.id.etDestination);
        motif = findViewById(R.id.rgMotif);
        inspecter = findViewById(R.id.btnInspecter);

        // 2 - عند الضغط على زر التفتيش
        inspecter.setOnClickListener(v -> {

            // 3 - قراءة بيانات المسافر
            String n = nom.getText().toString().trim();
            String a = naissance.getText().toString().trim();
            String d = destination.getText().toString().trim();

            // 4 - التحقق من الحقول الفارغة
            if (n.isEmpty() || a.isEmpty() || d.isEmpty()) {
                if (n.isEmpty()) nom.setError("Champ obligatoire");
                if (a.isEmpty()) naissance.setError("Champ obligatoire");
                if (d.isEmpty()) destination.setError("Champ obligatoire");
                return;
            }

            // 5 - تحديد سبب السفر
            int id = motif.getCheckedRadioButtonId();
            RadioButton choix = findViewById(id);
            String m = choix.getText().toString();

            // 6 - إنشاء الانتقال إلى الواجهة الثانية
            // 8 - الانتقال إلى واجهة فحص المفارقة الزمنية
            Intent intent = new Intent(
                    MainActivityJava.this,
                    VerificationActivity.class
            );

            // 7 - إرسال معلومات المسافر
            intent.putExtra("nom", n);
            intent.putExtra("naissance", a);
            intent.putExtra("destination", d);
            intent.putExtra("motif", m);

            startActivity(intent);
        });
    }
}
