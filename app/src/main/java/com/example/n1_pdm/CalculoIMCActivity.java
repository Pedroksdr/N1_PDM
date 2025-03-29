package com.example.n1_pdm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class CalculoIMCActivity extends AppCompatActivity {

    private EditText pesoEditText;
    private EditText alturaEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calculo_imcactivity);

        pesoEditText = findViewById(R.id.pesoEditText);
        alturaEditText = findViewById(R.id.alturaEditText);
        Button calcularButton = findViewById(R.id.calcularButton);
        Button limparButton = findViewById(R.id.limparButton);
        Button fecharButton = findViewById(R.id.fecharButton);

        calcularButton.setOnClickListener(v -> calcularIMC());
        limparButton.setOnClickListener(v -> limparCampos());
        fecharButton.setOnClickListener(v -> finish());

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void calcularIMC() {
        String pesoStr = pesoEditText.getText().toString();
        String alturaStr = alturaEditText.getText().toString();

        if (pesoStr.isEmpty() || alturaStr.isEmpty()) {
            Toast.makeText(this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        double peso = Double.parseDouble(pesoStr);
        double altura = Double.parseDouble(alturaStr);
        double imc = peso / (altura * altura);

        Intent intent;
        if (imc < 18.5) {
            intent = new Intent(this, AbaixoDoPesoActivity.class);
        } else if (imc >= 18.5 && imc < 25) {
            intent = new Intent(this, PesoNormalActivity.class);
        } else if (imc >= 25 && imc < 30) {
            intent = new Intent(this, SobrepesoActivity.class);
        } else if (imc >= 30 && imc < 35) {
            intent = new Intent(this, Obesidade1Activity.class);
        } else if (imc >= 35 && imc < 40) {
            intent = new Intent(this, Obesidade2Activity.class);
        } else {
            intent = new Intent(this, Obesidade3Activity.class);
        }

        intent.putExtra("PESO", peso);
        intent.putExtra("ALTURA", altura);
        intent.putExtra("IMC", imc);
        startActivity(intent);
    }

    private void limparCampos() {
        // Limpar apenas os campos de entrada de texto
        if (pesoEditText != null) {
            pesoEditText.setText("");
        }
        if (alturaEditText != null) {
            alturaEditText.setText("");
        }

    }
    }
