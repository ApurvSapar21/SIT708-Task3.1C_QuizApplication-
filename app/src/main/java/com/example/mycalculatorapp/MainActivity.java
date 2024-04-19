package com.example.mycalculatorapp;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText operan1EditText;
    EditText operan2EditText;

    TextView resultTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        operan1EditText = findViewById(R.id.operand1EditText);
        operan2EditText = findViewById(R.id.operand2EditText);
        resultTextView = findViewById(R.id.resultTextView);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void addClick(View view) {
        double result = Double.parseDouble(operan1EditText.getText().toString()) + Double.parseDouble(operan2EditText.getText().toString());
        resultTextView.setText(Double.toString(result));
    }

    public void subClick(View view) {
        double result = Double.parseDouble(operan1EditText.getText().toString()) - Double.parseDouble(operan2EditText.getText().toString());
        resultTextView.setText(Double.toString(result));
    }
}